package com.project.titulo.client.upload;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.Widget;

public class UploadFileWidget extends Composite {

	//RPC
	
	//uifields	
	@UiField FileUpload fileBTN;
	@UiField Button uploadBTN;
	
	private static UploadFileWidgetUiBinder uiBinder = GWT.create(UploadFileWidgetUiBinder.class);

	interface UploadFileWidgetUiBinder extends
			UiBinder<Widget, UploadFileWidget> {
	}
	public UploadFileWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	
	//handlers
	//click registro link
	@UiHandler("uploadBTN")
	void onRegisteLinkClick(ClickEvent event) 
	{
		
	}
}
