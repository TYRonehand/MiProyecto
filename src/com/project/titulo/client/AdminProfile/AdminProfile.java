package com.project.titulo.client.AdminProfile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class AdminProfile extends Composite {


	@UiField Image imagebutton; 
	@UiField PushButton pushbutton; 
	
	private static AdminProfileUiBinder uiBinder = GWT
			.create(AdminProfileUiBinder.class);

	interface AdminProfileUiBinder extends UiBinder<Widget, AdminProfile> {
	}

	public AdminProfile() {
		initWidget(uiBinder.createAndBindUi(this));

		//pushbutton
		Image upImage = new Image();
		upImage.setUrl("assets/img/icons/user.png");
		Image downImage = new Image();
		downImage.setUrl("assets/img/icons/share.png");
		
		pushbutton = new PushButton(upImage, downImage);
	    pushbutton.addClickHandler(new ClickHandler() {
	         @Override
	         public void onClick(ClickEvent event) {

	         }
	      });
		
	}

	


}
