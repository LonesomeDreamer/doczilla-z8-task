package org.zenframework.z8.template.model;

import org.zenframework.z8.server.runtime.*;
import org.zenframework.z8.server.base.table.Table;
import org.zenframework.z8.server.types.guid;
import org.zenframework.z8.server.types.string;

@SuppressWarnings("all")
public class Status extends Table {
	public static class CLASS<T extends Status> extends Table.CLASS<T> {
		public CLASS(IObject container) {
			super(container);
			setJavaClass(Status.class);
			setAttribute("generatable", "");
			setAttribute("name", "Tmp Statuses");
		}

		public Object newObject(IObject container) {
			return new Status(container);
		}
	}

	public static guid New = new guid(/* ba82da79-e026-4de2-aa41-7c5e0a821320 */-5007199618696065566L, -6178520470430543072L);
	public static guid InWork = new guid(/* 6b54a703-c804-43eb-9e02-aaacd1290903 */7733990094784447467L, -7060893606543292157L);
	public static guid Done = new guid(/* 3347743e-11c0-4079-8cdd-f632a82de961 */3695049829215387769L, -8296204241116206751L);

	public void initStaticRecords()
	{
		super.initStaticRecords();
		{
			java.util.HashMap map = new java.util.HashMap();
			map.put(name.get(), new string("Новый"));
			internalAddRecord(new guid(/* ba82da79-e026-4de2-aa41-7c5e0a821320 */-5007199618696065566L, -6178520470430543072L), map);
		}
		{
			java.util.HashMap map = new java.util.HashMap();
			map.put(name.get(), new string("В работе"));
			internalAddRecord(new guid(/* 6b54a703-c804-43eb-9e02-aaacd1290903 */7733990094784447467L, -7060893606543292157L), map);
		}
		{
			java.util.HashMap map = new java.util.HashMap();
			map.put(name.get(), new string("Исполнен"));
			internalAddRecord(new guid(/* 3347743e-11c0-4079-8cdd-f632a82de961 */3695049829215387769L, -8296204241116206751L), map);
		}

	}
	static {
		staticConstructor();
	}

	public static void staticConstructor() {
	}

	public Status(IObject container) {
		super(container);
	}

	public void constructor1() {
	}

	public void initMembers() {
		super.initMembers();
	}

	public void constructor2() {
		super.constructor2();
		name.setAttribute("displayName", "Статус");
	}
}
