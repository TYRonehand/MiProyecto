package com.project.titulo.client.Forum;

import java.util.Calendar;

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

public class EditTopicModal extends Composite {

	//control url
	public GoToUrl url = new GoToUrl();
	
	//elementos uibinder
	@UiField TextBox titleInput; 
	@UiField RichTextArea descriptionInput; 
	@UiField DialogBox dialogBox; 
	@UiField VerticalPanel dialogVPanel; 

	//RPC
	private final ServerServiceAsync serverService = GWT.create(ServerService.class);
	
	//id from the topic to edit
	private String idTopic;
	//topic full
	private Topic updatedTopic;
	
	//widget
	private static EditTopicModalUiBinder uiBinder = GWT
			.create(EditTopicModalUiBinder.class);

	interface EditTopicModalUiBinder extends UiBinder<Widget, EditTopicModal> {
	}

	public EditTopicModal(String idtopic) 
	{
		//save id from topic to edit
		this.idTopic = idtopic;
		//init properties
		initWidget(uiBinder.createAndBindUi(this));
		LoadModal();
		LoadData();
	}
	
	//properties from modal
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
	
	//data 
	private void LoadData(){
		
		//get info from topic
		serverService.getTopic(idTopic, new AsyncCallback<Topic>(){

			@Override
			public void onFailure(Throwable caught) {

				ErrorVerify.getErrorAlert("offline");
				
			}

			@Override
			public void onSuccess(Topic result) {
				//receive data info
				if(result!=null)
				{
					updatedTopic = result;
					titleInput.setText(result.getTitle());
					descriptionInput.setText(result.getDescription());
				}else{
					ErrorVerify.getErrorAlert("fatal");
				}
				
			}});	
	}
	
	//click update
	@UiHandler("createBtn")
	void onCreateBtnClick(ClickEvent event) 
	{
		//validar algo escrito
		if(titleInput.getText().length()>3 && descriptionInput.getText().length()>20)
		{
			//modification date
			Calendar cal = Calendar.getInstance();
			
			//Edit object
			updatedTopic.setTitle(titleInput.getText());
			updatedTopic.setDescription(descriptionInput.getText());
			updatedTopic.setEdition(cal.getTime().toString());
			
			//call service update
			serverService.setTopic(updatedTopic, new AsyncCallback<Boolean>(){

				@Override
				public void onFailure(Throwable caught) 
				{
					ErrorVerify.getErrorAlert("offline");
				}
				
				@Override
				public void onSuccess(Boolean result) 
				{
					if(result){
						ErrorVerify.getErrorAlert("successupdate");
						dialogBox.setVisible(false);
					}else{
						ErrorVerify.getErrorAlert("fatal");
					}
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
