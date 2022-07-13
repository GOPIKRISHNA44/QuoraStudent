package com.quorastudent.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.quorastudent.constants.AppConstants;

@Entity
@Table(name = "userdetails")
public class UserDetailsDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userid;

	private int interestspopup = 0;

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

	public String getUniversitycode() {
		return universitycode;
	}

	public void setUniversitycode(String universityCode) {
		this.universitycode = universityCode;
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

	public int getAvatarid() {
		return avatarid;
	}

	public void setAvatarid(int avatarid) {
		this.avatarid = avatarid;
	}

	public int getInterestspopup() {
		return interestspopup;
	}

	public void setInterestspopup(int interestspopup) {
		this.interestspopup = interestspopup;
	}

	private Date dob;

	private String universitycode;

	private int avatarid = AppConstants.DEFAULT_AVATAR_ID;

}
