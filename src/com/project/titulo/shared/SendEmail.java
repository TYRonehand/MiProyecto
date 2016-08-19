package com.project.titulo.shared;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.mail.*;
import javax.mail.internet.*;
 
@SuppressWarnings("serial")
public class SendEmail extends HttpServlet{
    
	private String to;
	private String from;
	private String host;
	private String code;
	
	public SendEmail(String To,String From,String Host,String Code)
	{
		this.setTo(To);
		this.setFrom(From);
		this.setHost(Host);
		this.setCode(Code);
	}
	
	public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	            throws ServletException, IOException
	{
	 
	      // Get system properties
	      Properties properties = System.getProperties();
	 
	      // Setup mail server
	      properties.setProperty("mail.smtp.host", this.getHost());
	 
	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);
	      
		  // Set response content type
	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	
	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);
	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(this.getFrom()));
	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.getTo()));
	         // Set Subject: header field
	         message.setSubject("This is the Subject Line!");
	         // Now set the actual message
	         message.setText("This is actual message");
	         // Send message
	         Transport.send(message);
	         String title = "Recovery Account from UCSC MOPTOOLS webpage";
	         String res = "Hi, we recently receive an recovery account petition. Use this code to set a new password. If you don't make this petition, send us an email or ommit this.";
	         String docType =
	         "<!doctype html public \"-//w3c//dtd html 4.0 " +
	         "transitional//en\">\n";
	         out.println(docType +
	         "<html>\n" +
	         "<head><title>" + title + "</title></head>\n" +
	         "<body bgcolor=\"#f0f0f0\">\n" +
	         "<h1 align=\"center\">" + title + "</h1>\n" +
	         "<p align=\"center\">" + res + "</p>\n" +
	         "</body></html>");
	      }
	      catch (MessagingException mex) 
	      {
	         mex.printStackTrace();
	      }
	 }
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
} 