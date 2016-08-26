package com.project.titulo.client;


import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.project.titulo.shared.model.Answer;
import com.project.titulo.shared.model.ResumeTopic;
import com.project.titulo.shared.model.Topic;
import com.project.titulo.shared.model.User;
import com.project.titulo.shared.model.UserFile;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("service")
public interface ServerService extends RemoteService {
	
	//autenticacion
	User authenticateAdmin(String user, String pass) throws IllegalArgumentException;
	
	//consultar existencia usuario
	Boolean userExist(String mail) throws IllegalArgumentException;
		
	//autenticacion
	User authenticateUser(String user, String pass) throws IllegalArgumentException;
	
	//get info
	User getUserInfo(String user) throws IllegalArgumentException;
	
	//set info
	Boolean setUserInfo(User myUser) throws IllegalArgumentException;

	//insert user
	String addUserInfo(User newUser) throws IllegalArgumentException;
	
	//found user files 
	List<UserFile> getUserFiles(String user) throws IllegalArgumentException;
	
	//recovery account
	void recoveryUser(String mail) throws IllegalArgumentException;
	
	//change pass
	void changeUserPassword(String mail, String PIN, String password) throws IllegalArgumentException;
	
	//new topic
	String addNewTopic(Topic myTopic) throws IllegalArgumentException;
	
	//get topic
	Topic getTopic(String idtopic) throws IllegalArgumentException;
	
	//set info
	Boolean setTopic(Topic myTopic) throws IllegalArgumentException;
	

	//all resume topic
	List<ResumeTopic> NewestResumeTopic() throws IllegalArgumentException;

	//all resume topic
	List<ResumeTopic> OldestResumeTopic() throws IllegalArgumentException;

	//all resume topic
	List<ResumeTopic> MyResumeTopic(String iduser) throws IllegalArgumentException;

	//all resume topic
	List<ResumeTopic> SearchResumeTopic(String specialword) throws IllegalArgumentException;
	

	//new coment
	String addNewComment(Answer myAnswer) throws IllegalArgumentException;

	//get coment
	Answer getComment(String idcomentary) throws IllegalArgumentException;
	
	//set coment
	Boolean setComment(Answer myAnswer) throws IllegalArgumentException;

	//add user file
	String addNewFile(UserFile myFile) throws IllegalArgumentException;

	//add user file
	String deleteFile(String iddatafile) throws IllegalArgumentException;
}
