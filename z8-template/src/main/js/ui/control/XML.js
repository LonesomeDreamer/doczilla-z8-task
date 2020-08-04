Z8.define('org.zenframework.z8.template.controls.XML', {
	extend: 'Z8.form.field.TextArea',
	
	tag: 'div',

	validate: function() {
		let a = document.querySelector("#" + this.id + " div.control");
		if (a && (a.innerHTML != "")) {
			const highlightedCode = hljs.highlight('xml', a.textContent).value;
			a.innerHTML = highlightedCode;
			this.value = a.innerHTML;
		}
		this.setValid(true);
	},
	
	
	
	setRawValue: function(value) {
		value = this.isEmptyValue(value) ? '' : value; 
		DOM.setProperty(this.input, 'innerHTML', value || '', null);
		DOM.setAttribute(this.input, 'title', value);
	},
	
	controlMarkup: function() {
		var value = this.valueToRaw(this.getValue());
		var enabled = this.isEnabled();
		var readOnly = this.isReadOnly();
		var tag = 'div';
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