Z8.define('org.zenframework.z8.template.controls.YT', {
	extend: 'Z8.form.field.Text',
	
	validate: function() {
		let a = document.querySelector("#" + this.id + " div.control");
		let b = document.querySelector("#" + this.id + " input.control");
		let video = "";
		let YouTubeURL = this.getValue();
		if (YouTubeURL) {
			let regex = /(https:\/\/www\.youtube\.com\/)watch\?v=([^&]*)(\&.*)?/gi;
			let array = [...YouTubeURL.matchAll(regex)];
			if (array.length) {
				video = array[0][1] + "embed/"+ array[0][2];		
			}
		}
		if (a) {
			if (video) {
				a.innerHTML = `<iframe src="${video}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>`;
			} else {
				a.innerHTML = "no video found.";
			}
		}
	},
	
	controlMarkup: function() {
		var value = this.valueToRaw(this.getValue());
		var enabled = this.isEnabled();
		var readOnly = this.isReadOnly();
		var tag = 'input';
		var inputCls = this.getInputCls().join(' ');
		value = Format.htmlEncode(value);
		var input = { tag: tag, name: 'input', cls: inputCls, contenteditable: 'true',  title: this.tooltip || '', value: tag == 'input' ? value : null, html: tag != 'input' ? value : null };

		if(!enabled)
			input.disabled = null;

		if(readOnly)
			input.readOnly = null;

		if(length != 0)
			input.maxlength = length;
		var result = [input];
		
		tag = 'div';
		inputCls = this.getInputCls().join(' ');
		value = "asd";
		input = { tag: tag, name: 'input', cls: inputCls, contenteditable: 'true',  title: this.tooltip || '', value: tag == 'input' ? value : null, html: tag != 'input' ? value : null };
		
		result.push(input);

		var triggers = this.triggers;

		if(!Z8.isEmpty(triggers)) {
			triggers = Array.isArray(triggers) ? triggers : [triggers];
			this.triggers = [];

			for(var i = 0, length = triggers.length; i < length; i++) {
				var trigger = triggers[i];
				var cls = DOM.parseCls(trigger.cls).pushIf('trigger-' + (length - i));
				trigger = new Z8.button.Trigger({ tooltip: trigger.tooltip, icon: trigger.icon, handler: trigger.handler, scope: trigger.scope, cls: cls });
				result.push(trigger.htmlMarkup());

				this.triggers.push(trigger);
			}
		}
		return result;
	},

});