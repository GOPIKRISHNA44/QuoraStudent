package com.quorastudent.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userdetails")
public class UserDetailsDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userid;

	

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getUniversityCode() {
		return universitycode;
	}

	public void setUniversityCode(String universityCode) {
		this.universitycode = universityCode;
	}

	private String username;

	private String password;

	private String emailid;

	public String getEmailId() {
		return emailid;
	}

	public void setEmailId(String emailId) {
		this.emailid = emailId;
	}

	private Date dob;

	private String universitycode;

}
