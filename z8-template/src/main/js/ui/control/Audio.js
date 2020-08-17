Z8.define('org.zenframework.z8.template.controls.Audio', {
	extend: 'Z8.form.field.File',
	
	validate: function() {
		let files = this.getValue();
		if (files) {
			if(files.length == 0) {
				return;
			}
			let file = files[0];
			let id = file.id;
			let path = file.path;
			let session = Application.session;
			let sound = document.querySelector("#" + this.id + " audio");
			sound.src = "/" + path + "?session=" + session + "&id=" + id;
  			sound.onend = function(e) {
	    		sound.src = "";
  			}
		}
	},
	
	controlMarkup: function() {
		var value = this.valueToRaw(this.getValue());
		var enabled = this.isEnabled();
		var readOnly = this.isReadOnly();
		var length = this.length;

		var tag = this.getInputTag();
		var inputCls = this.getInputCls().join(' ');
		value = Format.htmlEncode(value);
		var input = { tag: tag, name: 'input', cls: inputCls, tabIndex: this.getTabIndex(), spellcheck: false, type: this.password ? 'password' : 'text', title: this.tooltip || '', placeholder: this.placeholder, autocomplete: this.autocomplete, value: tag == 'input' ? value : null, html: tag != 'input' ? value : null };

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
		
		tag = 'audio';
		inputCls = this.getInputCls().join(' ');
		input = { tag: tag, controls: "" };
		
		result.push(input);

		return result;
	},
	
	upload: function(files) {
		if(files.length == 0)
			return;

		this.getUploadTrigger().setBusy(true);

		var callback = function(record, files, success) {
			this.getUploadTrigger().setBusy(false);
		};

		var record = this.getRecord();
		record.attach(this.name, files, { fn: callback, scope: this });
	},

});