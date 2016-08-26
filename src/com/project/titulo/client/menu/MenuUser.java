package com.project.titulo.client.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;
import com.project.titulo.client.GoToUrl;
import com.project.titulo.shared.CookieVerify;

public class MenuUser extends Composite {


	//cookies
	private CookieVerify mycookie = new CookieVerify(false);
	//control url
	public GoToUrl url = new GoToUrl();
	
	//uibinder
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
	
	/*EVENT HANDLERS*/
	
	//click salir
	@UiHandler("exitLink")
	void onExitLinkClick(ClickEvent event) 
	{
		//del all cookies
		mycookie.delCookiesInfo();
		//load login
		url.GoTo("LOGIN");
	}
	
	//click profile
	@UiHandler("profileLink")
	void onProfileLinkClick(ClickEvent event) {
		
		//load profile
		url.GoTo("PROFILE"); 
	}

	//push files button 
	@UiHandler("filesBTN")
	void onButtonClick(ClickEvent event) {
		
		//load files
		url.GoTo("FILES"); 
	}
	
	//push home button
	@UiHandler("homeBTN")
	void onHomeBTNClick(ClickEvent event) {

		//load home
		url.GoTo("HOME"); 
	}
	
	//push plot button
	@UiHandler("plotBTN")
	void onPlotBTNClick(ClickEvent event) {
		
		//load plot
		url.GoTo("PLOT"); 
	}
	
	//push metrics button
	@UiHandler("metricBTN")
	void onMetricBTNClick(ClickEvent event) {
		
		//load files
		url.GoTo("METRIC"); 
	}
	
	//push community button
	@UiHandler("communityBTN")
	void onCommunityBTNClick(ClickEvent event) {
		
		//load files
		url.GoTo("FORUM"); 
		
	}
	
	//push FAQ button
	@UiHandler("faqBTN")
	void onFaqBTNClick(ClickEvent event) {
		
		//load files
		url.GoTo("FAQ"); 
	}
}
