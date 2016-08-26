package com.project.titulo.client.Files;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class HelpModal extends Composite {

	@UiField DialogBox dialogBox; 
	@UiField VerticalPanel dialogVPanel; 

	private static HelpModalUiBinder uiBinder = GWT.create(HelpModalUiBinder.class);

	interface HelpModalUiBinder extends UiBinder<Widget, HelpModal> {
	}

	public HelpModal() {
		initWidget(uiBinder.createAndBindUi(this));
		LoadModal();
	}

	public void LoadModal(){
		
		//dialogbox
		dialogBox.setText("File Format");
		dialogBox.center();
		dialogBox.setAnimationEnabled(true);
		dialogBox.setAutoHideEnabled(true);
		
		
		//VerticalPanel 
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
	}
}
