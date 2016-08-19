package com.project.titulo.client.recovery;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.project.titulo.client.login.LoginWidget;
import com.project.titulo.shared.FieldVerifier;


public class RecoveryWidget extends Composite {

	@UiField Hyperlink backLink;
	@UiField TextBox mailInput;
	@UiField Label labelError1;
	@UiField TextBox codeInput;
	@UiField Label labelError2;
	@UiField PasswordTextBox password1Input;
	@UiField Label labelError3;
	@UiField PasswordTextBox password2Input;
	@UiField Label labelError4;
	@UiField VerticalPanel panelRecovery;
	@UiField VerticalPanel panelNewCode;
	@UiField VerticalPanel panelNewPassword;
	
	private static recoveryWidgetUiBinder uiBinder = GWT
			.create(recoveryWidgetUiBinder.class);

	interface recoveryWidgetUiBinder extends UiBinder<Widget, RecoveryWidget> {
	}

	public RecoveryWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	//evento cambio valor  input mail
	@UiHandler("mailInput")
    void handleMailInputChange(ValueChangeEvent<String> event) 
	{
      if (event.getValue().length() < 6) {
    	  labelError1.setText("minimum lenght 6");
    	  labelError1.setVisible(true);
      } else {
          labelError1.setText("");
    	  labelError1.setVisible(false);
      }
    }
	//evento cambio valor  input code
	@UiHandler("codeInput")
    void handleCodeInputChange(ValueChangeEvent<String> event) 
	{
      if (event.getValue().length() != 6) {
    	  labelError2.setText("Enter 6 char code!");
    	  labelError2.setVisible(true);
      } else {
          labelError2.setText("");
    	  labelError2.setVisible(false);
      }
    }
	//evento cambio valor  input new password
	@UiHandler("password1Input")
    void handlePass1InputChange(ValueChangeEvent<String> event) 
	{
      if (event.getValue().length() < 6) {
    	  labelError3.setText("minimum lenght 6");
    	  labelError3.setVisible(true);
      } else {
          labelError3.setText("");
    	  labelError3.setVisible(false);
      }
    }
	//evento cambio valor  inputpassword repeat
	@UiHandler("password2Input")
    void handlePass2InputChange(ValueChangeEvent<String> event) 
	{
      if (event.getValue().length() < 6) {
    	  labelError4.setText("minimum lenght 6");
    	  labelError4.setVisible(true);
      } else {
          labelError4.setText("");
    	  labelError4.setVisible(false);
      }
    }
	
	//click registro link
	@UiHandler("backLink")
	void onRegisteLinkClick(ClickEvent event) 
	{
		RootPanel.get("GWTcontainer").clear();
		RootPanel.get("GWTmenu").clear();
		RootPanel.get("GWTmenu").add(new LoginWidget());
	}
	
	/*Evento click SEND CODE*/
	@UiHandler("submitBTN")
	void onSendCodeBTNClick(ClickEvent event) 
	{
		if(mailInput.getText().length()<6){

	    	  labelError1.setText("minimum lenght 6");
	    	  labelError1.setVisible(true);
		}
		else if(FieldVerifier.isValidMail(mailInput.getText()))
		{
			labelError1.setVisible(false);
			//create unique code
			//save in the user DB
			//send mail to user
			
			//clean container
			//if all Ok
				Window.alert("We send an e-mail to you with a code");
				panelRecovery.setVisible(false);
				panelNewCode.setVisible(true);
				
			//else
			//Window.alert("Sorry an error ocurred, please try later.");
			
		}else{
			
			Window.alert("Please enter a valid mail (example@gmail.com)");
		}
		
	}
	
	/*Evento click SUBMIT*/
	@UiHandler("codeBTN")
	void onEnterCodeBTNClick(ClickEvent event) 
	{
		if(codeInput.getText().length()!=6)
		{
			labelError2.setText("Enter 6 character code.");
			labelError2.setVisible(false);
			
		}
		else if(FieldVerifier.isValidMail(mailInput.getText()))
		{
			labelError2.setVisible(false);
			//verify code belongs to the user mail
			//if all Ok
				panelNewCode.setVisible(false);
				panelNewPassword.setVisible(true);
			//else
			//Window.alert("Sorry an error ocurred, please try later.");
			
		}else{
			Window.alert("Please enter a valid mail (example@gmail.com)");
		}
		
	}
	
	/*Evento click SUBMIT*/
	@UiHandler("passwordBTN")
	void onEnterPasswordBTNClick(ClickEvent event) 
	{
		if(password1Input.getText().length()<6){
			labelError3.setText("minimum lenght 6");
			labelError3.setVisible(true);
		}else if(password2Input.getText().length()<6){
			labelError4.setText("minimum lenght 6");
			labelError4.setVisible(true);
		}else if(!password1Input.getText().equals(password2Input.getText()))
		{
			//Window.alert("Passwords are not equals");
			labelError4.setText("Passwords are not equals");
			labelError4.setVisible(true);
		}else if(password1Input.getText().equals(password2Input.getText()) && password1Input.getText().length()>=6)
		{
			labelError3.setVisible(false);
			labelError4.setVisible(false);
			//if
				Window.alert("Password saved!");
			
			//else
			//Window.alert("Sorry an error ocurred, please try later.");
			
		}else{
			Window.alert("Please enter a valid mail (example@gmail.com)");
			
		}
		
	}
	
	
}
