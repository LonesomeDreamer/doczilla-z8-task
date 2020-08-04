package org.zenframework.z8.template.model;

import org.zenframework.z8.server.runtime.*;
import org.zenframework.z8.server.base.table.Table;
import org.zenframework.z8.template.model.Document;
import org.zenframework.z8.server.base.table.value.Link;
import org.zenframework.z8.template.model.Status;
import org.zenframework.z8.server.base.table.system.Users;

@SuppressWarnings("all")
public class History extends Table {
	public static class CLASS<T extends History> extends Table.CLASS<T> {
		public CLASS(IObject container) {
			super(container);
			setJavaClass(History.class);
			setAttribute("generatable", "");
			setAttribute("name", "Tmp History");
		}

		public Object newObject(IObject container) {
			return new History(container);
		}
	}

	public Document.CLASS<? extends Document> document;
	public Link.CLASS<? extends Link> documentId;
	public Status.CLASS<? extends Status> status;
	public Link.CLASS<? extends Link> statusId;
	public Users.CLASS<? extends Users> user;
	public Link.CLASS<? extends Link> userId;

	static {
		staticConstructor();
	}

	public static void staticConstructor() {
	}

	public History(IObject container) {
		super(container);
		document = new Document.CLASS<Document>(this);
		documentId = new Link.CLASS<Link>(this);
		status = new Status.CLASS<Status>(this);
		statusId = new Link.CLASS<Link>(this);
		user = new Users.CLASS<Users>(this);
		userId = new Link.CLASS<Link>(this);
	}

	public void constructor1() {
		documentId.get(IClass.Constructor).operatorAssign(document);
		statusId.get(IClass.Constructor).operatorAssign(status);
		userId.get(IClass.Constructor).operatorAssign(user);
	}

	public void initMembers() {
		super.initMembers();
		objects.add(document);
		objects.add(documentId);
		objects.add(status);
		objects.add(statusId);
		objects.add(user);
		objects.add(userId);
	}

	public void constructor2() {
		super.constructor2();
		document.setIndex("document");
		documentId.setAttribute("name", "Document");
		documentId.setIndex("documentId");
		status.setIndex("status");
		statusId.setAttribute("name", "Status");
		statusId.setIndex("statusId");
		user.setIndex("user");
		userId.setAttribute("name", "User");
		userId.setIndex("userId");
		createdAt.setAttribute("displayName", "Дата");
		description.setAttribute("displayName", "Примечание");
		user.get().name.setAttribute("displayName", "Ответствунный");
	}
}
