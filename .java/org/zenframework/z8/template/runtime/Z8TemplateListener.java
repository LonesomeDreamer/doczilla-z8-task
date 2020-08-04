package org.zenframework.z8.template.runtime;

import org.zenframework.z8.server.runtime.*;
import org.zenframework.z8.template.runtime.RuntimeListener;
import org.zenframework.z8.template.runtime.Setup;
import org.zenframework.z8.server.base.table.system.Roles;
import org.zenframework.z8.server.types.bool;
import org.zenframework.z8.server.base.table.system.Users;

@SuppressWarnings("all")
public class Z8TemplateListener extends RuntimeListener {
	public static class CLASS<T extends Z8TemplateListener> extends RuntimeListener.CLASS<T> {
		public CLASS(IObject container) {
			super(container);
			setJavaClass(Z8TemplateListener.class);
		}

		public Object newObject(IObject container) {
			return new Z8TemplateListener(container);
		}
	}


	static {
		staticConstructor();
	}

	public static void staticConstructor() {
	}

	public Z8TemplateListener(IObject container) {
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

	public void z8_beforeDBGenerate() {
	}

	public void z8_afterDBGenerate() {
		Setup.CLASS<? extends Setup> setup = new Setup.CLASS<Setup>(this);
		setup.get().z8_setTableAccess(setup.get().privateTables, new RCollection(new Object[]{Roles.User}), new bool(false), new bool(false), new bool(false), new bool(false), new bool(false));
		setup.get().z8_setTableAccess(setup.get().publicTables, new RCollection(new Object[]{Roles.Administrator, Roles.User}), new bool(true), new bool(true), new bool(true), new bool(true), new bool(true));
		setup.get().z8_setRequestAccess(setup.get().privateRequests, new RCollection(new Object[]{Roles.User}), new bool(false));
		setup.get().z8_setRequestAccess(setup.get().publicRequests, new RCollection(new Object[]{Roles.User}), new bool(true));
		setup.get().z8_addEntries(setup.get().privateEntries, Users.Administrator);
		setup.get().z8_addEntries(setup.get().publicEntries, Users.Administrator);
	}
}
