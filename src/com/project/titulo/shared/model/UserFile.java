package com.project.titulo.shared.model;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserFile  implements IsSerializable{
	private String iddatafile;
	private String iduser;
	private String title;
	private String description;
	private String creation;
	private String file;
	private Boolean state = false;

	@SuppressWarnings("unused")
	private UserFile(){
		
	}
	
	//add file
	public UserFile(String Title, String Description, String Iduser, String File){
		this.setIduser(Iduser);
		this.setTitle(Title);
		this.setDescription(Description);
		this.setFile(File);
	}
	//get file
	public UserFile(String Idfile, String Title, String Description, String Iduser, String Creation){
		this.setIddatafile(Idfile);
		this.setIduser(Iduser);
		this.setTitle(Title);
		this.setDescription(Description);
		this.setCreation(Creation);
		
	}

	public String getIddatafile() {
		return iddatafile;
	}

	public void setIddatafile(String iddatafile) {
		this.iddatafile = iddatafile;
	}

	public String getIduser() {
		return iduser;
	}

	public void setIduser(String iduser) {
		this.iduser = iduser;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreation() {
		return creation;
	}

	public void setCreation(String creation) {
		this.creation = creation;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}
}
