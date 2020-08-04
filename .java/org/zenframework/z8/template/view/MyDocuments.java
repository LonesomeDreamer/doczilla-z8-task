package org.zenframework.z8.template.view;

import org.zenframework.z8.server.runtime.*;
import org.zenframework.z8.template.view.Documents;
import org.zenframework.z8.server.types.string;

@SuppressWarnings("all")
public class MyDocuments extends Documents {
	public static class CLASS<T extends MyDocuments> extends Documents.CLASS<T> {
		public CLASS(IObject container) {
			super(container);
			setJavaClass(MyDocuments.class);
			setAttribute("entry", "");
			setAttribute("request", "true");
			setAttribute("displayName", "Мои документы");
		}

		public Object newObject(IObject container) {
			return new MyDocuments(container);
		}
	}


	static {
		staticConstructor();
	}

	public static void staticConstructor() {
	}

	public MyDocuments(IObject container) {
		super(container);
	}

	public void constructor1() {
		fullName.get(IClass.Constructor).operatorAssign(name.get(IClass.Constructor1).sql_string().operatorAdd(new string(" - ").sql_string()).operatorAdd(status.get(IClass.Constructor1).name.get(IClass.Constructor1).sql_string()).operatorAdd(new string(" (").sql_string()).operatorAdd(user.get(IClass.Constructor1).name.get(IClass.Constructor1).sql_string()).operatorAdd(new string(")").sql_string()));
		historyList.get(IClass.Constructor).link = history.get(IClass.Constructor1).documentId;
		historyList.get(IClass.Constructor).query = history;
		statusId.get(IClass.Constructor).operatorAssign(status);
		userId.get(IClass.Constructor).operatorAssign(user);
	}

	public void initMembers() {
		super.initMembers();
	}

	public void constructor2() {
		super.constructor2();
	}

	public void z8_beforeRead() {
		super.z8_beforeRead();
		z8_addWhere(userId.get().sql_guid().operatorEqu(z8_user().get().id.sql_guid()));
	}
}
