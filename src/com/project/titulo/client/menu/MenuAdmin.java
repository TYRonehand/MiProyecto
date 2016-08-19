package com.project.titulo.client.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MenuAdmin extends Composite {

	private static MenuAdminUiBinder uiBinder = GWT
			.create(MenuAdminUiBinder.class);

	interface MenuAdminUiBinder extends UiBinder<Widget, MenuAdmin> {
	}

	public MenuAdmin() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
