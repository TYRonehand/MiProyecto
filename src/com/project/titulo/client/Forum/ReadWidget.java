package com.project.titulo.client.Forum;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ReadWidget extends Composite {

	private static ReadWidgetUiBinder uiBinder = GWT
			.create(ReadWidgetUiBinder.class);

	interface ReadWidgetUiBinder extends UiBinder<Widget, ReadWidget> {
	}

	public ReadWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
