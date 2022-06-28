package com.quorastudent.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sessiondetails")
public class SessionDetailsDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getSessionkey() {
		return sessionkey;
	}

	public void setSessionkey(String sessionkey) {
		this.sessionkey = sessionkey;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	private Date loggedinat;
	private Long userid;
	private String sessionkey;

	public Date getLoggedinat() {
		return loggedinat;
	}

	public void setLoggedinat(Date loggedinat) {
		this.loggedinat = loggedinat;
	}

	public Date getLoggedoutat() {
		return loggedoutat;
	}

	public void setLoggedoutat(Date loggedoutat) {
		this.loggedoutat = loggedoutat;
	}

	private Date loggedoutat;
	private int active;

}
