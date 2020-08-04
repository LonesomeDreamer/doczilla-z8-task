package org.zenframework.z8.template.helloworld.model;

import org.zenframework.z8.server.runtime.*;
import org.zenframework.z8.server.base.table.Table;

@SuppressWarnings("all")
public class Document extends Table {
	public static class CLASS<T extends Document> extends Table.CLASS<T> {
		public CLASS(IObject container) {
			super(container);
			setJavaClass(Document.class);
			setAttribute("generatable", "");
			setAttribute("name", "Documents");
		}

		public Object newObject(IObject container) {
			return new Document(container);
		}
	}


	static {
		staticConstructor();
	}

	public static void staticConstructor() {
	}

	public Document(IObject container) {
		super(container);
	}

	public void constructor1() {
	}

	public void initMembers() {
		super.initMembers();
	}

	public void constructor2() {
		super.constructor2();
		shortName.setAttribute("displayName", "№ Документа");
		description.setAttribute("displayName", "Текст документа");
	}
}
