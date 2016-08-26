package com.project.titulo.client;

import com.google.gwt.user.client.ui.RootPanel;
import com.project.titulo.client.AdminProfile.AdminProfile;
import com.project.titulo.client.Files.FileWidget;
import com.project.titulo.client.Files.HelpModal;
import com.project.titulo.client.Files.UploadModal;
import com.project.titulo.client.Forum.ForumWidget;
import com.project.titulo.client.Metric.MetricWidget;
import com.project.titulo.client.Plot.PlotWidget;
import com.project.titulo.client.UserProfile.UserProfile;
import com.project.titulo.client.faq.FAQWidget;
import com.project.titulo.client.home.HomeWidget;
import com.project.titulo.client.login.LoginWidget;
import com.project.titulo.client.menu.MenuDropdown;
import com.project.titulo.client.recovery.RecoveryWidget;
import com.project.titulo.client.register.RegisterWidget;
import com.project.titulo.shared.CookieVerify;

public class GoToUrl {
	//cookies
	private CookieVerify mycookie = new CookieVerify(false);
	
	public GoToUrl(){}
	
	public void GoTo(String option)
	{
		
		//opciones url
		switch(option){

			case "MENU":
				// widget close session	
				RootPanel.get("GWTmenu").clear();
				//menu widget
				RootPanel.get("GWTmenu").add(new MenuDropdown());//add(new MenuUser(mycookie.getCookieUser(),true));
				break;
				
			case "LOGIN":
				//set cookie from
				mycookie.setCookieIdurl("LOGIN");
				// widget close session	
				RootPanel.get("GWTmenu").clear();
				RootPanel.get("GWTcontainer").clear();	
				//cualquier otro caso sera enviado al login
				RootPanel.get("GWTmenu").add(new LoginWidget());
				break;
				
			case "REGISTER":
				//set cookie from
				mycookie.setCookieIdurl("REGISTER");
				// widget close session	
				RootPanel.get("GWTmenu").clear();
				RootPanel.get("GWTcontainer").clear();	
				//cualquier otro caso sera enviado al login
				RootPanel.get("GWTmenu").add(new RegisterWidget());
				break;
				
			case "RECOVERY":
				//set cookie from
				mycookie.setCookieIdurl("RECOVERY");
				// widget close session	
				RootPanel.get("GWTmenu").clear();
				RootPanel.get("GWTcontainer").clear();	
				//cualquier otro caso sera enviado al login
				RootPanel.get("GWTmenu").add(new RecoveryWidget());
				break;
				
			case "ADMINISTRATION":
				//set cookie from
				mycookie.setCookieIdurl("PROFILE");
				//goto profile
				RootPanel.get("GWTcontainer").clear();
				RootPanel.get("GWTcontainer").add(new AdminProfile());
				break;
				
			case "PROFILE":
				//set cookie from
				mycookie.setCookieIdurl("PROFILE");
				//goto profile
				RootPanel.get("GWTcontainer").clear();
				RootPanel.get("GWTcontainer").add(new UserProfile(mycookie.getCookieUser()));
				break;

			case "HOME":
				//set cookie from
				mycookie.setCookieIdurl("HOME");
				// widget close session	
				RootPanel.get("GWTcontainer").clear();	
				//cualquier otro caso sera enviado al login
				RootPanel.get("GWTcontainer").add(new HomeWidget());
				break;
				
			case "FILES":
				//set cookie from
				mycookie.setCookieIdurl("FILES");
				// widget close session	
				RootPanel.get("GWTcontainer").clear();	
				//cualquier otro caso sera enviado al login
				RootPanel.get("GWTcontainer").add(new FileWidget());
				break;
				
			case "PLOT":
				//set cookie from
				mycookie.setCookieIdurl("PLOT");
				// widget close session	
				RootPanel.get("GWTcontainer").clear();	
				//cualquier otro caso sera enviado al login
				RootPanel.get("GWTcontainer").add(new PlotWidget(mycookie.getCookieUser()));
				break;
				
			case "METRIC":
				//set cookie from
				mycookie.setCookieIdurl("METRIC");
				// widget close session	
				RootPanel.get("GWTcontainer").clear();	
				//cualquier otro caso sera enviado al login
				RootPanel.get("GWTcontainer").add(new MetricWidget());
				break;
				
			case "FORUM":
				//set cookie from
				mycookie.setCookieIdurl("FORUM");
				// widget close session	
				RootPanel.get("GWTcontainer").clear();	
				//cualquier otro caso sera enviado al login
				RootPanel.get("GWTcontainer").add(new ForumWidget(mycookie.getCookieUser()));
				break;
				
			case "FAQ":
				mycookie.setCookieIdurl("FAQ");
				// widget close session	
				RootPanel.get("GWTcontainer").clear();	
				//cualquier otro caso sera enviado al login
				RootPanel.get("GWTcontainer").add(new FAQWidget());
				break;
				
			case "MODALUPLOAD":
				//cualquier otro caso sera enviado al login
				RootPanel.get().add(new UploadModal());
				break;
				
			case "MODALHELP":
				//cualquier otro caso sera enviado al login
				RootPanel.get().add(new HelpModal());
				break;
				
			default:break;
		}
	}
}
