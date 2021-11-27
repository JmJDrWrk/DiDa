package com.example.dida;

public class Users {
	
	private int id_user;
	private String name;
	private String surname;
	private String mail;
	private String isadmin;
	private String pass;
	private String id_historial;
	
	public Users() {
		super();
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String isIsadmin() {
		return isadmin;
	}
	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getId_historial() {
		return id_historial;
	}
	public void setId_historial(String id_historial) {
		this.id_historial = id_historial;
	}

	
	
}
