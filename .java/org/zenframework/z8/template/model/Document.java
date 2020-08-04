package org.zenframework.z8.template.model;

import org.zenframework.z8.server.runtime.*;
import org.zenframework.z8.server.base.table.Table;
import org.zenframework.z8.template.model.Status;
import org.zenframework.z8.server.base.table.value.Link;
import org.zenframework.z8.server.base.table.system.Users;
import org.zenframework.z8.server.base.table.value.FileField;
import org.zenframework.z8.server.base.table.value.StringField;
import org.zenframework.z8.server.base.table.value.TextField;
import org.zenframework.z8.server.base.table.value.StringExpression;
import org.zenframework.z8.server.types.string;
import org.zenframework.z8.server.types.integer;
import org.zenframework.z8.server.types.guid;
import org.zenframework.z8.server.types.bool;
import org.zenframework.z8.template.model.History;

@SuppressWarnings("all")
public class Document extends Table {
	public static class CLASS<T extends Document> extends Table.CLASS<T> {
		public CLASS(IObject container) {
			super(container);
			setJavaClass(Document.class);
			setAttribute("generatable", "");
			setAttribute("name", "Tmp Documents");
		}

		public Object newObject(IObject container) {
			return new Document(container);
		}
	}

	public Status.CLASS<? extends Status> status;
	public Link.CLASS<? extends Link> statusId;
	public Users.CLASS<? extends Users> user;
	public Link.CLASS<? extends Link> userId;
	public FileField.CLASS<? extends FileField> doc;
	public StringField.CLASS<? extends StringField> email;
	public TextField.CLASS<? extends TextField> xml;
	public StringField.CLASS<? extends StringField> youtube;
	public StringExpression.CLASS<? extends StringExpression> fullName;

	static {
		staticConstructor();
	}

	public static void staticConstructor() {
	}

	public Document(IObject container) {
		super(container);
		status = new Status.CLASS<Status>(this);
		statusId = new Link.CLASS<Link>(this);
		user = new Users.CLASS<Users>(this);
		userId = new Link.CLASS<Link>(this);
		doc = new FileField.CLASS<FileField>(this);
		email = new StringField.CLASS<StringField>(this);
		xml = new TextField.CLASS<TextField>(this);
		youtube = new StringField.CLASS<StringField>(this);
		fullName = new StringExpression.CLASS<StringExpression>(this);
	}

	public void constructor1() {
		fullName.get(IClass.Constructor).operatorAssign(name.get(IClass.Constructor1).sql_string().operatorAdd(new string(" - ").sql_string()).operatorAdd(status.get(IClass.Constructor1).name.get(IClass.Constructor1).sql_string()).operatorAdd(new string(" (").sql_string()).operatorAdd(user.get(IClass.Constructor1).name.get(IClass.Constructor1).sql_string()).operatorAdd(new string(")").sql_string()));
		statusId.get(IClass.Constructor).operatorAssign(status);
		userId.get(IClass.Constructor).operatorAssign(user);
	}

	public void initMembers() {
		super.initMembers();
		objects.add(status);
		objects.add(statusId);
		objects.add(user);
		objects.add(userId);
		objects.add(doc);
		objects.add(email);
		objects.add(xml);
		objects.add(youtube);
		objects.add(fullName);
	}

	public void constructor2() {
		super.constructor2();
		status.setIndex("status");
		statusId.setAttribute("name", "Status");
		statusId.setIndex("statusId");
		user.setIndex("user");
		userId.setAttribute("name", "User");
		userId.setIndex("userId");
		doc.setAttribute("name", "Doc");
		doc.setAttribute("displayName", "Файл");
		doc.setIndex("doc");
		email.setAttribute("name", "Email");
		email.setAttribute("displayName", "E-Mail");
		email.setIndex("email");
		xml.setAttribute("name", "xml");
		xml.setAttribute("displayName", "XML");
		xml.setIndex("xml");
		youtube.setAttribute("name", "youtube");
		youtube.setAttribute("displayName", "YouTube");
		youtube.setIndex("youtube");
		fullName.setAttribute("displayName", "Документ");
		fullName.setIndex("fullName");
		createdAt.setAttribute("displayName", "Создан");
		name.setAttribute("displayName", "Документ");
		user.get().name.setAttribute("displayName", "Ответственный");
		email.get().length = new integer(50L);
		youtube.get().length = new integer(100L);
	}

	public void z8_afterUpdate(guid recordId) {
		super.z8_afterUpdate(recordId);
		if((statusId.get().z8_isChanged().operatorNot().get() ? new bool(true).operatorAnd(userId.get().z8_isChanged().operatorNot()) : new bool(false)).get())
			return;
		History.CLASS<? extends History> history = new History.CLASS<History>(this);
		history.get().documentId.get().operatorAssign(recordId);
		history.get().statusId.get().operatorAssign(statusId.get().z8_get());
		history.get().userId.get().operatorAssign(userId.get().z8_get());
		history.get().z8_create();
	}
}
