package com.project.titulo.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.project.titulo.client.model.*;

/**
 * The async counterpart of <code>ServiceService</code>.
 */
public interface ServerServiceAsync {

	void userExist(String mail, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void authenticateUser(String user, String pass, AsyncCallback<User>	callback) throws IllegalArgumentException;
	
	void getUserInfo(String user, AsyncCallback<User>	callback) throws IllegalArgumentException;
	
	void getUserFiles(String user, AsyncCallback<List<UserFile>> callback) throws IllegalArgumentException;
	
	void setUserInfo(User myUser, AsyncCallback<Boolean> callback) throws IllegalArgumentException;
	
	void addUserInfo(User myUser, AsyncCallback<String> callback) throws IllegalArgumentException;
	
	void recoveryUser(String mail, AsyncCallback<Void> callback) throws IllegalArgumentException;
	
	void changeUserPassword(String mail, String PIN, String password, AsyncCallback<Void> callback) throws IllegalArgumentException;
	
	void addNewTopic(Topic myTopic, AsyncCallback<String> callback) throws IllegalArgumentException;
	
	void getTopic(String idtopic, AsyncCallback<Topic> callback) throws IllegalArgumentException;
	
	void setTopic(Topic mytopic, AsyncCallback<Boolean> callback) throws IllegalArgumentException;
	
	void addNewComment(Answer myAnswer, AsyncCallback<String> callback) throws IllegalArgumentException;
	
	void getComment(String idcomentary, AsyncCallback<Answer> callback) throws IllegalArgumentException;
	
	void setComment(Answer myAnswer, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void addNewFile(UserFile myFile, AsyncCallback<String> callback) throws IllegalArgumentException;

	void deleteFile(String iddatafile, AsyncCallback<String> callback) throws IllegalArgumentException;
	
	
}
