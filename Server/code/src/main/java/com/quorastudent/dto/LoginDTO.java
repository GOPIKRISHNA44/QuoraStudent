package com.quorastudent.dto;

public class LoginDTO {

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String username;

	private String password;

	private String emailid;

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailId) {
		this.emailid = emailId;
	}

}
