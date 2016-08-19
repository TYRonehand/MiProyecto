package com.project.titulo.client.Forum;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ForumWidget extends Composite {

	private static ForumWidgetUiBinder uiBinder = GWT
			.create(ForumWidgetUiBinder.class);

	interface ForumWidgetUiBinder extends UiBinder<Widget, ForumWidget> {
	}

	public ForumWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
