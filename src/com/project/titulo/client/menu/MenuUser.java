package com.project.titulo.client.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.project.titulo.client.Forum.ForumWidget;
import com.project.titulo.client.Plot.PlotWidget;
import com.project.titulo.client.UserProfile.UserProfile;
import com.project.titulo.client.faq.FAQWidget;
import com.project.titulo.client.home.HomeWidget;
import com.project.titulo.client.login.LoginWidget;
import com.project.titulo.client.upload.UploadFileWidget;

public class MenuUser extends Composite {
	
	@UiField Hyperlink exitLink;
	@UiField Hyperlink profileLink;
	
	
	private static MenuUserUiBinder uiBinder = GWT
			.create(MenuUserUiBinder.class);

	interface MenuUserUiBinder extends UiBinder<Widget, MenuUser> {
	}

	public MenuUser(String username, boolean open) {
		initWidget(uiBinder.createAndBindUi(this));
		
		if(open)
			profileLink.setText("User Profile");
		else
			profileLink.setText("Log In");
		
	}
	
	//end session: clean widgets and cookies
	private void closeSession() {
		Cookies.removeCookie("MOPuser");
		Cookies.removeCookie("MOPmail");
		Cookies.removeCookie("MOPname");
		Cookies.removeCookie("MOPban");
		//load login
		RootPanel.get("GWTcontainer").clear();
		RootPanel.get("GWTmenu").clear();
		RootPanel.get("GWTmenu").add(new LoginWidget());
	}
	
	//link to user profile
	private void gotoProfile(){
		//goto profile
		RootPanel.get("GWTcontainer").clear();
		RootPanel.get("GWTcontainer").add(new UserProfile(Cookies.getCookie("MOPuser")));
	}
	
	/*EVENT HANDLERS*/
	
	//click salir
	@UiHandler("exitLink")
	void onExitLinkClick(ClickEvent event) 
	{
		closeSession();
	}
	//click profile
	@UiHandler("profileLink")
	void onProfileLinkClick(ClickEvent event) 
	{
		gotoProfile();  
	}

	//push files button 
	@UiHandler("filesBTN")
	void onButtonClick(ClickEvent event) {
		RootPanel.get("GWTcontainer").clear();
		RootPanel.get("GWTcontainer").add(new UploadFileWidget());
	}
	
	//push home button
	@UiHandler("homeBTN")
	void onHomeBTNClick(ClickEvent event) {

		//clean div
		RootPanel.get("GWTcontainer").clear();
		//home Widget
		RootPanel.get("GWTcontainer").add(new HomeWidget());
	}
	
	//push plot button
	@UiHandler("plotBTN")
	void onPlotBTNClick(ClickEvent event) {
		//clean div
		RootPanel.get("GWTcontainer").clear();
		//home Widget
		RootPanel.get("GWTcontainer").add(new PlotWidget());
	}
	
	//push metrics button
	@UiHandler("metricBTN")
	void onMetricBTNClick(ClickEvent event) {
	}
	
	//push community button
	@UiHandler("communityBTN")
	void onCommunityBTNClick(ClickEvent event) {
		//clean div
		RootPanel.get("GWTcontainer").clear();
		//home Widget
		RootPanel.get("GWTcontainer").add(new ForumWidget());
		
	}
	
	//push FAQ button
	@UiHandler("faqBTN")
	void onFaqBTNClick(ClickEvent event) {
		//clean div
		RootPanel.get("GWTcontainer").clear();
		//home Widget
		RootPanel.get("GWTcontainer").add(new FAQWidget());
	}
}
