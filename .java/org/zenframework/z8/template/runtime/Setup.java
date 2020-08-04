package org.zenframework.z8.template.runtime;

import org.zenframework.z8.server.runtime.*;
import org.zenframework.z8.server.base.table.system.RoleTableAccess;
import org.zenframework.z8.server.base.table.system.RoleFieldAccess;
import org.zenframework.z8.server.base.table.system.RoleRequestAccess;
import org.zenframework.z8.server.base.table.system.UserRoles;
import org.zenframework.z8.server.base.table.system.UserEntries;
import org.zenframework.z8.server.base.table.system.Entries;
import org.zenframework.z8.template.model.Document;
import org.zenframework.z8.template.model.Status;
import org.zenframework.z8.template.model.History;
import org.zenframework.z8.template.view.Documents;
import org.zenframework.z8.template.view.MyDocuments;
import org.zenframework.z8.server.base.table.Table;
import org.zenframework.z8.server.runtime.OBJECT;
import org.zenframework.z8.server.types.integer;
import org.zenframework.z8.server.types.guid;
import org.zenframework.z8.server.types.bool;
import org.zenframework.z8.server.db.sql.functions.Sql;
import org.zenframework.z8.server.base.table.value.Field;
import org.zenframework.z8.server.types.string;
import org.zenframework.z8.server.base.application.Application;

@SuppressWarnings("all")
public class Setup extends OBJECT {
	public static class CLASS<T extends Setup> extends OBJECT.CLASS<T> {
		public CLASS(IObject container) {
			super(container);
			setJavaClass(Setup.class);
		}

		public Object newObject(IObject container) {
			return new Setup(container);
		}
	}

	public RoleTableAccess.CLASS<? extends RoleTableAccess> tableAccess;
	public RoleFieldAccess.CLASS<? extends RoleFieldAccess> fieldAccess;
	public RoleRequestAccess.CLASS<? extends RoleRequestAccess> requestAccess;
	public UserRoles.CLASS<? extends UserRoles> userRoles;
	public UserEntries.CLASS<? extends UserEntries> userEntries;
	public Entries.CLASS<? extends Entries> entries;
	public Document.CLASS<? extends Document> document;
	public Status.CLASS<? extends Status> status;
	public History.CLASS<? extends History> history;
	public Documents.CLASS<? extends Documents> documents;
	public MyDocuments.CLASS<? extends MyDocuments> myDocuments;
	public RCollection privateTables;
	public RCollection publicTables;
	public RCollection readOnlyTables;
	public RCollection noDestoryTables;
	public RCollection privateRequests;
	public RCollection publicRequests;
	public RLinkedHashMap privateEntries;
	public RLinkedHashMap publicEntries;

	static {
		staticConstructor();
	}

	public static void staticConstructor() {
	}

	public Setup(IObject container) {
		super(container);
		tableAccess = new RoleTableAccess.CLASS<RoleTableAccess>(this);
		fieldAccess = new RoleFieldAccess.CLASS<RoleFieldAccess>(this);
		requestAccess = new RoleRequestAccess.CLASS<RoleRequestAccess>(this);
		userRoles = new UserRoles.CLASS<UserRoles>(this);
		userEntries = new UserEntries.CLASS<UserEntries>(this);
		entries = new Entries.CLASS<Entries>(this);
		document = new Document.CLASS<Document>(this);
		status = new Status.CLASS<Status>(this);
		history = new History.CLASS<History>(this);
		documents = new Documents.CLASS<Documents>(this);
		myDocuments = new MyDocuments.CLASS<MyDocuments>(this);
	}

	public void constructor1() {
	}

	public void initMembers() {
		super.initMembers();
	}

	public void constructor2() {
		super.constructor2();
		tableAccess.setIndex("tableAccess");
		fieldAccess.setIndex("fieldAccess");
		requestAccess.setIndex("requestAccess");
		userRoles.setIndex("userRoles");
		userEntries.setIndex("userEntries");
		entries.setIndex("entries");
		document.setIndex("document");
		status.setIndex("status");
		history.setIndex("history");
		documents.setIndex("documents");
		myDocuments.setIndex("myDocuments");
		privateTables = new RCollection(new Object[]{});
		publicTables = new RCollection(new Object[]{document});
		readOnlyTables = new RCollection(new Object[]{status});
		noDestoryTables = new RCollection(new Object[]{history});
		privateRequests = new RCollection(new Object[]{documents});
		publicRequests = new RCollection(new Object[]{myDocuments});
		privateEntries = new RLinkedHashMap(new Object[]{documents}, new Object[]{new integer(1L)});
		publicEntries = new RLinkedHashMap(new Object[]{myDocuments}, new Object[]{new integer(2L)});
	}

	public void z8_setTableAccess(RCollection tables, RCollection roles, bool read, bool write, bool create, bool copy, bool destroy) {
		tableAccess.get().read.get().operatorAssign(read);
		tableAccess.get().write.get().operatorAssign(write);
		tableAccess.get().create.get().operatorAssign(create);
		tableAccess.get().copy.get().operatorAssign(copy);
		tableAccess.get().destroy.get().operatorAssign(destroy);
		tableAccess.get().z8_update(Sql.z8_inVector(tableAccess.get().tables.get().classId.get().sql_string(), z8_classNames(tables)).operatorAnd(Sql.z8_inVector(tableAccess.get().role.get().sql_guid(), roles)));
		fieldAccess.get().read.get().operatorAssign(read);
		fieldAccess.get().write.get().operatorAssign(write);
		fieldAccess.get().z8_update(Sql.z8_inVector(fieldAccess.get().fields.get().tables.get().classId.get().sql_string(), z8_classNames(tables)).operatorAnd(Sql.z8_inVector(fieldAccess.get().role.get().sql_guid(), roles)));
	}

	public void z8_setRequestAccess(RCollection requests, RCollection roles, bool execute) {
		requestAccess.get().execute.get().operatorAssign(execute);
		requestAccess.get().z8_update(Sql.z8_inVector(requestAccess.get().requests.get().classId.get().sql_string(), z8_classNames(requests)).operatorAnd(Sql.z8_inVector(requestAccess.get().role.get().sql_guid(), roles)));
	}

	public void z8_addRoles(RCollection roles, guid user) {
		RLinkedHashMap existingRoles = new RLinkedHashMap();
		userRoles.get().z8_read(new RCollection(new Object[]{userRoles.get().role}), userRoles.get().user.get().sql_guid().operatorEqu(user.sql_guid()));
		while(userRoles.get().z8_next().get())
			existingRoles.z8_add(userRoles.get().role.get().z8_get(), userRoles.get().z8_recordId());
		for(guid role : (RCollection<guid>)roles)
		{
			if(((bool)existingRoles.z8_containsKey(role)).operatorNot().get())
			{
				userRoles.get().user.get().operatorAssign(user);
				userRoles.get().role.get().operatorAssign(role);
				userRoles.get().z8_create();
			}
		}
	}

	public void z8_addEntries(RLinkedHashMap entriesCls, guid user) {
		Object __0, __1;
		RLinkedHashMap entriesByClass = z8_idsByValue(entries, entries.get().classId);
		RLinkedHashMap existingEntries = new RLinkedHashMap();
		userEntries.get().z8_read(new RCollection(new Object[]{userEntries.get().entry}), userEntries.get().user.get().sql_guid().operatorEqu(user.sql_guid()));
		while(userEntries.get().z8_next().get())
			existingEntries.z8_add(userEntries.get().entry.get().z8_get(), userEntries.get().z8_recordId());
		for(OBJECT.CLASS<? extends OBJECT> entry : (RCollection<OBJECT.CLASS<? extends OBJECT>>)entriesCls.z8_keys())
		{
			guid entryId = ((guid)(((__0 = ((guid)entriesByClass.get(entry.get().z8_className()))) != null) ? __0 : guid.Null));
			guid existingEntry = ((guid)(((__1 = ((guid)existingEntries.get(entryId))) != null) ? __1 : guid.Null));
			userEntries.get().position.get().operatorAssign(((integer)entriesCls.get(entry)));
			if(existingEntry.operatorEqu(guid.Null).get())
			{
				userEntries.get().user.get().operatorAssign(user);
				userEntries.get().entry.get().operatorAssign(entryId);
				userEntries.get().z8_create();
			}
			else
			{
				userEntries.get().z8_update(existingEntry);
			}
		}
	}

	public void z8_removeEntries(RCollection entries, guid user) {
		userEntries.get().z8_destroy(Sql.z8_inVector(userEntries.get().entries.get().classId.get().sql_string(), z8_classNames(entries)).operatorAnd(userEntries.get().user.get().sql_guid().operatorEqu(user.sql_guid())));
	}

	static public RLinkedHashMap z8_idsByValue(Table.CLASS<? extends Table> table, Field.CLASS<? extends Field> field) {
		RLinkedHashMap idByValues = new RLinkedHashMap();
		table.get().z8_read(new RCollection(new Object[]{field}));
		while(table.get().z8_next().get())
		{
			string value = field.get().z8_primary().z8_toString();
			if(((bool)idByValues.z8_containsKey(value)).get())
				Application.z8_warning(table.get().z8_name().operatorAdd(new string(": Повторяющееся значение (")).operatorAdd(field.get().z8_name()).operatorAdd(new string("): ")).operatorAdd(value));
			idByValues.z8_add(value, table.get().z8_recordId());
		}
		return idByValues;
	}

	static public RCollection z8_classNames(RCollection objects) {
		RCollection objectsCls = new RCollection();
		for(OBJECT.CLASS<? extends OBJECT> o : (RCollection<OBJECT.CLASS<? extends OBJECT>>)objects)
			objectsCls.z8_add(o.get().z8_className());
		return objectsCls;
	}
}
