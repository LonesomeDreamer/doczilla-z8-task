package org.zenframework.z8.template.view;

import org.zenframework.z8.server.runtime.*;
import org.zenframework.z8.template.model.Document;
import org.zenframework.z8.server.base.form.action.Action;
import org.zenframework.z8.server.types.guid;
import org.zenframework.z8.server.base.query.Query;
import org.zenframework.z8.template.model.History;
import org.zenframework.z8.server.base.form.Listbox;
import org.zenframework.z8.server.types.string;
import org.zenframework.z8.server.types.bool;
import org.zenframework.z8.server.types.integer;

@SuppressWarnings("all")
public class Documents extends Document {
	public static class CLASS<T extends Documents> extends Document.CLASS<T> {
		public CLASS(IObject container) {
			super(container);
			setJavaClass(Documents.class);
			setAttribute("entry", "");
			setAttribute("request", "true");
			setAttribute("displayName", "Документы");
		}

		public Object newObject(IObject container) {
			return new Documents(container);
		}
	}

	public Action.CLASS<? extends Action> clearHistory;

	public static class __0 extends Action
	{
		public static class CLASS<T extends Documents.__0> extends Action.CLASS<T> {
			public CLASS(IObject container) {
				super(container);
				setJavaClass(Documents.__0.class);
				setAttribute("icon", "fa-trash");
				setAttribute("displayName", "Очистить историю");
			}

			public Object newObject(IObject container) {
				return new Documents.__0(container);
			}
		}

		public __0(IObject container) {
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

		public void z8_execute(guid recordId, Query.CLASS<? extends Query> context, RCollection selected, Query.CLASS<? extends Query> query) {
			History.CLASS<? extends History> history = new History.CLASS<History>(this);
			history.get().z8_destroy(history.get().documentId.get().sql_guid().operatorEqu(recordId.sql_guid()));
		}
	};
	public History.CLASS<? extends History> history;
	public Listbox.CLASS<? extends Listbox> historyList;

	static {
		staticConstructor();
	}

	public static void staticConstructor() {
	}

	public Documents(IObject container) {
		super(container);
		clearHistory = new __0.CLASS<__0>(this);
		history = new History.CLASS<History>(this);
		historyList = new Listbox.CLASS<Listbox>(this);
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
		objects.add(clearHistory);
		objects.add(history);
		objects.add(historyList);
	}

	public void constructor2() {
		super.constructor2();
		clearHistory.setIndex("clearHistory");
		history.setIndex("history");
		historyList.setAttribute("displayName", "История");
		historyList.setIndex("historyList");
		history.get().status.get().name.get().editable = new bool(true);
		history.get().user.get().name.get().editable = new bool(true);
		history.get().description.get().editable = new bool(true);
		status.get().name.get().dependencies = new RCollection(new Object[]{historyList});
		user.get().name.get().dependencies = new RCollection(new Object[]{historyList});
		historyList.get().columns = new RCollection(new Object[]{history.get(IClass.Constructor1).createdAt, history.get(IClass.Constructor1).status.get(IClass.Constructor1).name, history.get(IClass.Constructor1).user.get(IClass.Constructor1).name, history.get(IClass.Constructor1).description});
		historyList.get().colSpan = new integer(4L);
		historyList.get().flex = new integer(1L);
		email.setAttribute("ui", "org.zenframework.z8.template.controls.EMail");
		xml.setAttribute("ui", "org.zenframework.z8.template.controls.XML");
		youtube.setAttribute("ui", "org.zenframework.z8.template.controls.YT");
		doc.setAttribute("ui", "org.zenframework.z8.template.controls.Audio");
		names = new RCollection(new Object[]{createdAt, fullName});
		controls = new RCollection(new Object[]{createdAt, name, doc, status.get(IClass.Constructor1).name, user.get(IClass.Constructor1).name, email, xml, youtube, historyList});
		columns = new RCollection(new Object[]{createdAt, name, doc, status.get(IClass.Constructor1).name, user.get(IClass.Constructor1).name});
		actions = new RCollection(new Object[]{clearHistory});
	}
}
