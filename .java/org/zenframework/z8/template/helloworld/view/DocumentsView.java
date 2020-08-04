package org.zenframework.z8.template.helloworld.view;

import org.zenframework.z8.server.runtime.*;
import org.zenframework.z8.template.helloworld.model.Document;

@SuppressWarnings("all")
public class DocumentsView extends Document {
	public static class CLASS<T extends DocumentsView> extends Document.CLASS<T> {
		public CLASS(IObject container) {
			super(container);
			setJavaClass(DocumentsView.class);
			setAttribute("entry", "");
			setAttribute("request", "true");
			setAttribute("displayName", "Документы");
		}

		public Object newObject(IObject container) {
			return new DocumentsView(container);
		}
	}


	static {
		staticConstructor();
	}

	public static void staticConstructor() {
	}

	public DocumentsView(IObject container) {
		super(container);
	}

	public void constructor1() {
	}

	public void initMembers() {
		super.initMembers();
	}

	public void constructor2() {
		super.constructor2();
	}
}
