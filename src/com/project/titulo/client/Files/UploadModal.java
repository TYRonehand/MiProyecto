package com.project.titulo.client.Files;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class UploadModal extends Composite {
	

	@UiField DialogBox dialogBox; 
	@UiField VerticalPanel dialogVPanel; 
	@UiField FormPanel docForm; 
	@UiField FileUpload DocPath;
	@UiField TextBox titleInput; 
	@UiField RichTextArea descriptionInput;  
	
	private static UploadModalUiBinder uiBinder = GWT
			.create(UploadModalUiBinder.class);

	interface UploadModalUiBinder extends UiBinder<Widget, UploadModal> {
	}

	public UploadModal() {
		initWidget(uiBinder.createAndBindUi(this));
		LoadModal();
	}

	private void LoadModal(){
		
		//dialogbox
		dialogBox.setText("Create new topic");
		dialogBox.center();
		dialogBox.setAnimationEnabled(true);
		dialogBox.setAutoHideEnabled(true);
		
		//VerticalPanel 
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		
		//form panel
	    docForm.setMethod(FormPanel.METHOD_POST);
	    docForm.setEncoding(FormPanel.ENCODING_MULTIPART); //  multipart MIME encoding
	    docForm.setAction("/FileUploadByWaterTalks"); // The servlet FileUploadGreeting
	    
	    //file upload
	    DocPath.setTitle("Upload File");
	    DocPath.setName("uploader"); // Very important 
	    
	    //inputs
	    titleInput.setName("title");
	    TextBox resume = new TextBox();
	    resume.setVisible(false);
	    resume.setText(descriptionInput.getText());
	    resume.setName("resume");
	    docForm.add(resume);

	}
	
	
	//handler push upload
	@UiHandler("createBtn")
	void onCreateBtnClick(ClickEvent event) 
	{
		docForm.submit();
	}
	
	
}
