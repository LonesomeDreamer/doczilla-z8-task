function node_walk(node, func) {
	var result = func(node);
  	for(node = node.firstChild; result !== false && node; node = node.nextSibling)
    result = node_walk(node, func);
  	return result;
};
	
function getCaretPosition(elem) {
	var sel = window.getSelection();
  	var cum_length = [0, 0];
 	if(sel.anchorNode == elem)
   	cum_length = [sel.anchorOffset, sel.extentOffset];
 	else {
    	var nodes_to_find = [sel.anchorNode, sel.extentNode];
    	if(!elem.contains(sel.anchorNode) || !elem.contains(sel.extentNode))
      	return undefined;
    	else {
      		var found = [0,0];
      		var i;
      		node_walk(elem, function(node) {
        	for(i = 0; i < 2; i++) {
         	if(node == nodes_to_find[i]) {
           		found[i] = true;
           		if(found[i == 0 ? 1 : 0])
             		return false; // all done
          		}	
        		}
        if(node.textContent && !node.firstChild) {
          for(i = 0; i < 2; i++) {
            if(!found[i])
              cum_length[i] += node.textContent.length;
          }
        }
      });
      cum_length[0] += sel.anchorOffset;
      cum_length[1] += sel.extentOffset;
    }
  }
  if(cum_length[0] <= cum_length[1])
    return cum_length;
  return [cum_length[1], cum_length[0]];
};

function createRange(node, chars, range) {
    if (!range) {
        range = document.createRange()
        range.selectNode(node);
        range.setStart(node, 0);
    }

    if (chars.count === 0) {
        range.setEnd(node, chars.count);
    } else if (node && chars.count >0) {
        if (node.nodeType === Node.TEXT_NODE) {
            if (node.textContent.length < chars.count) {
                chars.count -= node.textContent.length;
            } else {
                 range.setEnd(node, chars.count);
                 chars.count = 0;
            }
        } else {
            for (var lp = 0; lp < node.childNodes.length; lp++) {
                range = createRange(node.childNodes[lp], chars, range);

                if (chars.count === 0) {
                   break;
                }
            }
        }
   } 

   return range;
};

function setCurrentCursorPosition(elem, chars) {
    if (chars >= 0) {
        var selection = window.getSelection();

        range = createRange(elem, { count: chars });

        if (range) {
            range.collapse(false);
            selection.removeAllRanges();
            selection.addRange(range);
        }
    }
}