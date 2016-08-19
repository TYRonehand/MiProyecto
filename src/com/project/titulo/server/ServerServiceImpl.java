package com.project.titulo.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.project.titulo.client.model.*;
import com.project.titulo.client.ServerService;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ServerServiceImpl extends RemoteServiceServlet implements ServerService {
	//datos conexion
	private Connection conn = null;
	//private String status;
	private String url = "jdbc:mysql://127.0.0.1:3306/proyectotitulo";//jdbc:mysql://mysql6.000webhost.com:3306/a2623180_project";//"jdbc:mysql://127.0.0.1:3306/proyectotitulo";
	private String user = "root";//"a2623180_root";
	private String pass = "";
	


	//Constructor
	public ServerServiceImpl() {
		conn= CreateConn();
	}
	//create conection
	public Connection CreateConn(){
		Connection myconn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) 
		{
		 	GWT.log(e.toString());
			e.printStackTrace();
		}
		
		try {
			myconn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) 
		{
		 	GWT.log(e.toString());
			e.printStackTrace();
		}
		
		return myconn;
	}
	
	/*--------------RPC FUNCTIONS---------------*/
	
	/*USER------------------------------------------------------------------------------*/
	//consulta si existe usuario
	@Override
	public Boolean userExist(String mail) throws IllegalArgumentException 
	{
		int count=0;
		try 
		 {
			 PreparedStatement ps = conn.prepareStatement("select mail from user where mail = '"+mail+"' limit 1");
			 ResultSet result = ps.executeQuery();
			 
			 while (result.next()) 
			 {
				 count++;
			 }
			 result.close();
			 ps.close();
			 
		 } 
		 catch (SQLException sqle) 
		 {
		 	GWT.log(sqle.toString());
		 	sqle.printStackTrace();
		 }
		
		if(count==0){
			 return false;
		}
		return true;
	}
	
	//user info validation
	@Override
	public User authenticateUser(String user, String pass) throws IllegalArgumentException
	{
		 User miuser = null;
		 
		 try 
		 {
			 //consultamos a base de datos
			 String Query= "SELECT * FROM user WHERE mail = '"+user+"' AND password = '" + pass + "' LIMIT 1";
			 PreparedStatement ps = conn.prepareStatement(Query);
			 ResultSet result = ps.executeQuery();
			 //recorremos resultado
			 while (result.next()) 
			 {
				 miuser = new User( result.getString("iduser"),
						 			result.getString("mail"), 
						 			result.getString("name"), 
						 			result.getString("lastname"), 
						 			result.getString("country"), 
						 			result.getString("ocupation"), 
						 			result.getString("web"), 
						 			result.getString("university"),
						 			"",
						 			result.getString("creation"),
						 			result.getString("registered"),
						 			result.getString("banned"),
						 			""
						 		  );
			 }
			 result.close();
			 ps.close();
		 } 
		 catch (SQLException sqle) 
		 {
		 	GWT.log(sqle.toString());
		 	sqle.printStackTrace();
		 }
		 return miuser;
	}
	
	//user info no security
	@Override
	public User getUserInfo(String iduser) throws IllegalArgumentException {
		 User miuser = null;
		 try 
		 {
			 //consultamos a base de datos
			 String Query= "SELECT * FROM user WHERE iduser = '"+iduser+"' LIMIT 1";
			 PreparedStatement ps = conn.prepareStatement(Query);
			 ResultSet result = ps.executeQuery();
			 //recorremos resultado
			 while (result.next()) 
			 {
				 miuser = new User( 
						 			result.getString("iduser"),
						 			result.getString("mail"), 
						 			result.getString("name"), 
						 			result.getString("lastname"), 
						 			result.getString("country"), 
						 			result.getString("ocupation"), 
						 			result.getString("web"), 
						 			result.getString("university"),
						 			"",
						 			result.getString("creation"),
						 			result.getString("registered"),
						 			result.getString("banned"),
						 			""
						 		  );
			 }
			 result.close();
			 ps.close();
		 } 
		 catch (SQLException sqle) 
		 {
		 	GWT.log(sqle.toString());
		 	sqle.printStackTrace();
		 }
		 return miuser;
	}
		
	//update info user
	@Override
	public Boolean setUserInfo(User myUser) throws IllegalArgumentException {

		 try 
		 {
			 //actualizamos usuario
			 String Query= " UPDATE user SET "
			 			 + "name='"+myUser.getName()+"', "
			 			 + "lastname='"+myUser.getLastname()+"', "
			 			 + "country='"+myUser.getCountry()+"', "
			 			 + "ocupation='"+myUser.getOcupation()+"', "
			 			 + "web='"+myUser.getWeb()+"',"
			 			 + "university='"+myUser.getUniversity()+"' "
			 			 + "WHERE iduser="+myUser.getId()+" ";
			 
			//execute query
			PreparedStatement ps = conn.prepareStatement(Query);
			ResultSet result = ps.executeQuery();
			 
			result.close();
			ps.close();
		 } 
		 catch (SQLException sqle) 
		 {
		 	GWT.log(sqle.toString());
		 	sqle.printStackTrace();
		 	return false;
		 }
		 return true;
	}

	//add new user in database
	@Override
	public String addUserInfo(User newUser) throws IllegalArgumentException {
		
		try 
		 {
			 //consultamos a base de datos
			 String Query= "INSERT INTO user (iduser,mail,name,lastname,country,ocupation,web,university,password,creation,registered,banned,pin)"
			 			+"VALUES ('','"+newUser.getMail()+"','"+newUser.getName()+"','"+newUser.getLastname()+"','"+newUser.getCountry()+"','','','','"+newUser.getPassword()+"', null, null, null,'');";
				
			 PreparedStatement ps = conn.prepareStatement(Query);
			 ps.executeUpdate();
			 
			 //recorremos resultado
			 ps.close();
		 } 
		 catch (SQLException sqle) 
		 {
		 	GWT.log(sqle.toString());
		 	sqle.printStackTrace();
			return "false: "+sqle.toString();
		 }
		return "true";
	}

	//recovery account
	@Override
	public void recoveryUser(String mail) throws IllegalArgumentException {
		
		//see if user exist
		
		//create code and save it in user
		
		//
		
		
		try 
		 {
			 //consultamos a base de datos
			 String Query= "query";
			 PreparedStatement ps = conn.prepareStatement(Query);
			 ResultSet result = ps.executeQuery();
			 
			 //recorremos resultado
			 result.close();
			 ps.close();
		 } 
		 catch (SQLException sqle) 
		 {
		 	GWT.log(sqle.toString());
		 	sqle.printStackTrace();
		 }
	}
	
	//recovery account
	@Override
	public void changeUserPassword(String mail, String PIN, String password) throws IllegalArgumentException {
		try 
		{
			 //consultamos a base de datos
			 String Query= "";
			 
			 PreparedStatement ps = conn.prepareStatement(Query);
			 ResultSet result = ps.executeQuery();
			 
			 //recorremos resultado
			 result.close();
			 ps.close();
		 } 
		 catch (SQLException sqle) 
		 {
		 	GWT.log(sqle.toString());
		 	sqle.printStackTrace();
		 }
	}
	
	
	
	/*FORUM-TOPIC-------------------------------------------------------------------------*/
	//add new topic in database
	@Override
	public String addNewTopic(Topic myTopic) throws IllegalArgumentException {
		
		try 
		 {
			//consultamos a base de datos
			 String Query= "INSERT INTO topic (title,description,iduser) VALUES ('"+myTopic.getTitle()+"','"+myTopic.getDescription()+"','"+myTopic.getIduser()+"')";
			 
			 PreparedStatement ps = conn.prepareStatement(Query);
			 ps.executeUpdate();
			 
			 //recorremos resultado
			 ps.close();
		 } 
		 catch (SQLException sqle) 
		 {
		 	GWT.log(sqle.toString());
		 	sqle.printStackTrace();
			return "false: "+sqle.toString();
		 }
		return "true";
	}
	
	//get soome topic
	@Override
	public Topic getTopic(String idtopic) throws IllegalArgumentException {
		Topic mytopic = null;
		 try 
		 {
			 //consultamos a base de datos
			 String Query= "SELECT * FROM topic WHERE idtopic= '"+idtopic+"' LIMIT 1";
			 PreparedStatement ps = conn.prepareStatement(Query);
			 ResultSet result = ps.executeQuery();
			 //recorremos resultado
			 while (result.next()) 
			 {
				 mytopic = new Topic(   result.getString("idtopic"),
						 				result.getString("title"),
						 				result.getString("description"),
						 				result.getString("iduser"),
						 				result.getString("creation"),
						 				result.getString("enabled"),
						 				result.getString("edition"));
			 }
			 result.close();
			 ps.close();
		 } 
		 catch (SQLException sqle) 
		 {
		 	GWT.log(sqle.toString());
		 	sqle.printStackTrace();
		 }
		 return mytopic;
	}
	
	//update topic
	@Override
	public Boolean setTopic(Topic myTopic) throws IllegalArgumentException {
		 try 
		 {
			 //actualizamos usuario
			 String Query= " UPDATE topic SET "
			 			 + "title='"+myTopic.getTitle()+"', "
			 			 + "description='"+myTopic.getDescription()+"', "
			 			 + "edition='"+myTopic.getEdition()+"', "
			 			 + "enabled='"+myTopic.getEnabled()+"' "
			 			 + "WHERE idtopic="+myTopic.getIdtopic()+" ";
			 
			//execute query
			PreparedStatement ps = conn.prepareStatement(Query);
			ResultSet result = ps.executeQuery();
			 
			result.close();
			ps.close();
		 } 
		 catch (SQLException sqle) 
		 {
		 	GWT.log(sqle.toString());
		 	sqle.printStackTrace();
		 	return false;
		 }
		 return true;
	}
	
	
	
	/*FORUM-ANSWER-------------------------------------------------------------------------*/
	//add new answer to topic
	@Override
	public String addNewComment(Answer myAnswer) throws IllegalArgumentException {
		
		try 
		 {
			 //consultamos a base de datos
			 String Query= "INSERT INTO comentary(comentary,idtopic,iduser) VALUES ('"+myAnswer.getComentary()+"','"+myAnswer.getIdtopic()+"','"+myAnswer.getIduser()+"')";
			 
			 PreparedStatement ps = conn.prepareStatement(Query);
			 ps.executeUpdate();
			 
			 //recorremos resultado
			 ps.close();
		 } 
		 catch (SQLException sqle) 
		 {
		 	GWT.log(sqle.toString());
		 	sqle.printStackTrace();
			return "false: "+sqle.toString();
		 }
		return "true";
	}

	//get some comment
	@Override
	public Answer getComment(String idcomentary) throws IllegalArgumentException {
		Answer myComment = null;
		 try 
		 {
			 //consultamos a base de datos
			 String Query= "SELECT * FROM comentary WHERE idcomentary= '"+idcomentary+"' LIMIT 1";
			 PreparedStatement ps = conn.prepareStatement(Query);
			 ResultSet result = ps.executeQuery();
			 //recorremos resultado
			 while (result.next()) 
			 {
				 myComment = new Answer(	result.getString("idcomentary"),
							 				result.getString("idtopic"),
							 				result.getString("comentary"),
							 				result.getString("creation"),
							 				result.getString("modify"),
							 				result.getString("enabled"),
							 				result.getString("iduser"));
			 }
			 result.close();
			 ps.close();
		 } 
		 catch (SQLException sqle) 
		 {
		 	GWT.log(sqle.toString());
		 	sqle.printStackTrace();
		 }
		 return myComment;
	}
	
	//set a comment
	@Override
	public Boolean setComment(Answer myAnswer) throws IllegalArgumentException {
		 try 
		 {
			 //actualizamos usuario
			 String Query= " UPDATE topic SET "
			 			 + "comentary='"+myAnswer.getComentary()+"', "
			 			 + "modify='"+myAnswer.getModify()+"', "
			 			 + "enabled='"+myAnswer.getEnabled()+"', "
			 			 + "WHERE idcomentary="+myAnswer.getIdcomentary()+" ";
			 
			//execute query
			PreparedStatement ps = conn.prepareStatement(Query);
			ResultSet result = ps.executeQuery();
			 
			result.close();
			ps.close();
		 } 
		 catch (SQLException sqle) 
		 {
		 	GWT.log(sqle.toString());
		 	sqle.printStackTrace();
		 	return false;
		 }
		 return true;
	}
	
	
	
	/*FILES------------------------------------------------------------------------------*/
	//all user files 
	@SuppressWarnings("null")
	@Override
	public List<UserFile> getUserFiles(String user) throws IllegalArgumentException {

		 
		 List<UserFile> listFiles = null;
		 try 
		 {
			 //consultamos a base de datos
			 String Query= "SELECT * FROM datafile WHERE iduser = '"+user+"'";
			 PreparedStatement ps = conn.prepareStatement(Query);
			 ResultSet result = ps.executeQuery();
			 //recorremos resultado
			 while (result.next()) 
			 {
				 //create userfile
				 UserFile aux = new UserFile(   result.getString("iddatafile"), 
							 					result.getString("iduser"),
									 			result.getString("title"), 
									 			result.getString("description"),  
									 			result.getString("creation")
						 		  			);
				 //add element to list
				 listFiles.add(aux);
			 }
			 result.close();
			 ps.close();
		 } 
		 catch (SQLException sqle) 
		 {
		 	GWT.log(sqle.toString());
		 	sqle.printStackTrace();
		 }
		 return listFiles;
	}
	
	//add file
	@Override
	public String addNewFile(UserFile myFile) throws IllegalArgumentException {
		
		try 
		 {
			 //consultamos a base de datos
			 String Query= "INSERT INTO comentary(title,description,iduser) VALUES ('"+myFile.getTitle()+"','"+myFile.getDescription()+"','"+myFile.getIduser()+"')";
			 
			 PreparedStatement ps = conn.prepareStatement(Query);
			 ps.executeUpdate();
			 
			 //recorremos resultado
			 ps.close();
		 } 
		 catch (SQLException sqle) 
		 {
		 	GWT.log(sqle.toString());
		 	sqle.printStackTrace();
			return "false: "+sqle.toString();
		 }
		return "true";
	}
	
	//delete file
	@Override
	public String deleteFile(String iddatafile) throws IllegalArgumentException {
		
		try 
		 {
			 //consultamos a base de datos
			 String Query= "DELETE FROM datafile WHERE iddatafile ="+iddatafile;
			 
			 PreparedStatement ps = conn.prepareStatement(Query);
			 ps.executeUpdate();
			 
			 //recorremos resultado
			 ps.close();
		 } 
		 catch (SQLException sqle) 
		 {
		 	GWT.log(sqle.toString());
		 	sqle.printStackTrace();
			return "false: "+sqle.toString();
		 }
		return "true";
	}
		
	
	
	
	
	
	
}//end class
