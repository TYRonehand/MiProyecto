package com.project.titulo.client.Metric;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MetricWidget extends Composite {

	private static MetricWidgetUiBinder uiBinder = GWT
			.create(MetricWidgetUiBinder.class);

	interface MetricWidgetUiBinder extends UiBinder<Widget, MetricWidget> {
	}

	public MetricWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
