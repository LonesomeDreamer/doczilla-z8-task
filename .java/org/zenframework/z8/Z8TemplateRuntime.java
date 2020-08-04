package org.zenframework.z8;

import org.zenframework.z8.server.runtime.*;
@SuppressWarnings("all")
public final class Z8TemplateRuntime extends org.zenframework.z8.server.runtime.AbstractRuntime {
	public Z8TemplateRuntime() {
		addTable(new org.zenframework.z8.template.model.Document.CLASS(null));
		addTable(new org.zenframework.z8.template.model.History.CLASS(null));
		addTable(new org.zenframework.z8.template.model.Status.CLASS(null));

		addEntry(new org.zenframework.z8.template.view.Documents.CLASS(null));
		addEntry(new org.zenframework.z8.template.view.MyDocuments.CLASS(null));


		addRequest(new org.zenframework.z8.template.view.Documents.CLASS(null));
		addRequest(new org.zenframework.z8.template.view.MyDocuments.CLASS(null));

	}
}