package com.project.titulo.client.Metric;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.project.titulo.client.GoToUrl;
import com.project.titulo.shared.CookieVerify;

public class MetricWidget extends Composite {

	//cookies
	private CookieVerify mycookie = new CookieVerify(false);
	//goto url
	public GoToUrl url = new GoToUrl();

	private static MetricWidgetUiBinder uiBinder = GWT
			.create(MetricWidgetUiBinder.class);

	interface MetricWidgetUiBinder extends UiBinder<Widget, MetricWidget> {
	}

	public MetricWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
