package com.project.titulo.client.Plot;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class PlotWidget extends Composite {

	private static PlotWidgetUiBinder uiBinder = GWT
			.create(PlotWidgetUiBinder.class);

	interface PlotWidgetUiBinder extends UiBinder<Widget, PlotWidget> {
	}

	public PlotWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
