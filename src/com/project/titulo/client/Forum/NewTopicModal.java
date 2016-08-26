package com.project.titulo.client.Forum;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.project.titulo.client.GoToUrl;
import com.project.titulo.client.ServerService;
import com.project.titulo.client.ServerServiceAsync;
import com.project.titulo.shared.ErrorVerify;
import com.project.titulo.shared.model.Topic;

public class NewTopicModal extends Composite {

	private String IDUSER=null;
	
	//goto url
	public GoToUrl url = new GoToUrl();
	
	
	//elementos uibinder
	@UiField TextBox titleInput; 
	@UiField RichTextArea descriptionInput; 
	@UiField DialogBox dialogBox; 
	@UiField VerticalPanel dialogVPanel; 

	//RPC
	private final ServerServiceAsync serverService = GWT.create(ServerService.class);
	
	private static NewTopicModalUiBinder uiBinder = GWT
			.create(NewTopicModalUiBinder.class);

	interface NewTopicModalUiBinder extends UiBinder<Widget, NewTopicModal> {
	}

	public NewTopicModal(String iduser) {
		//save id user
		this.IDUSER = iduser;
		
		initWidget(uiBinder.createAndBindUi(this));
		LoadModal();
	}

	public void LoadModal(){
		//clear fields
		titleInput.setText("");
		descriptionInput.setText("");
		
		//dialogbox
		dialogBox.setText("Create new topic");
		dialogBox.center();
		dialogBox.setAnimationEnabled(true);
		dialogBox.setAutoHideEnabled(true);
		
		//VerticalPanel 
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		
		//set focus
		titleInput.setFocus(true);
	}
	
	//evento cambio valor  input
	@UiHandler("createBtn")
	void onCreateBtnClick(ClickEvent event) 
	{
		//validar algo escrito
		if(titleInput.getText().length()>3 && descriptionInput.getText().length()>20)
		{
			
				//create object
				Topic myTopic = new Topic(titleInput.getText(), descriptionInput.getText(), this.IDUSER);
				//call service
				serverService.addNewTopic(myTopic, new AsyncCallback<String>(){
		
					@Override
					public void onFailure(Throwable caught) 
					{
						ErrorVerify.getErrorAlert("offline");
					}
					
					@Override
					public void onSuccess(String result) 
					{
						ErrorVerify.getErrorAlert("successadd");
						dialogBox.setVisible(false);
					}
				});
			
		}
		else
		{
			ErrorVerify.getErrorAlert("empty");
		}
		
    }
	
	//evento cambio valor  input
	@UiHandler("cancelBtn")
	void onCancelBtnClick(ClickEvent event) 
	{
		dialogBox.hide();
    }
	
}
