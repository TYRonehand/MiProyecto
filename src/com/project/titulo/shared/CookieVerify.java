package com.project.titulo.shared;

import java.util.Date;
import com.google.gwt.user.client.Cookies;

public class CookieVerify {
	//variable names
	private  Date time = new Date(new Date().getTime()+(1000*60*60*2));
	private  String iduser="MOPuserid";
	private  String idmail="MOPmail";
	private  String idname="MOPfullname";
	private  String idban="MOPban";
	
	//getters internal
	public  Date getTime() {return time;}
	public  String getIduser() {return iduser;}
	public  String getIdmail() {return idmail;}
	public  String getIdname() {return idname;}
	public  String getIdban() {return idban;}
	
	//cookie id
	public  void setCookieUser(String value)
	{
		Cookies.setCookie( getIduser(), value, getTime());
	}
	//cookie mail
	public  void setCookieMail(String value)
	{
		Cookies.setCookie(getIdmail(), value, getTime());
	}
	//cookie fullname
	public  void setCookieName(String value)
	{
		Cookies.setCookie(getIdname(), value, getTime());
	}
	//cookie state ban
	public  void setCookieBanned(String value)
	{
		Cookies.setCookie(getIdban(), value, getTime());
	}
	//exist cookie created
	public Boolean CookieExist()
	{
		//if all cookies are not empty -> true
		if(!Cookies.getCookie(getIduser()).isEmpty() && !Cookies.getCookie(getIdmail()).isEmpty() && !Cookies.getCookie(getIdname()).isEmpty() && !Cookies.getCookie(getIdban()).isEmpty())
		{
			return true;
		}
		else//if there is any one empty -> false
		{
			delCookiesInfo();
			return false;
		}
			
	}
	//delete cookie
	public  void delCookiesInfo()
	{
		Cookies.removeCookie(getIduser());
		Cookies.removeCookie(getIdmail());
		Cookies.removeCookie(getIdname());
		Cookies.removeCookie(getIdban());
	}
}
