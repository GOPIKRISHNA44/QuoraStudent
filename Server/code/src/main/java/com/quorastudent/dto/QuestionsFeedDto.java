package com.quorastudent.dto;

import java.util.Date;

public class QuestionsFeedDto {
	
	private Long eqid;
	private Long userid;
	private String question;
	private Date doq;
	private Date updatedat;
	private String ctype;
	private int active;
	private String tags;
	private String username;
	private int avatarid;
	private int upvotes;
	private int downvotes;
	public Long getEqid() {
		return eqid;
	}
	public void setEqid(Long eqid) {
		this.eqid = eqid;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Date getDoq() {
		return doq;
	}
	public void setDoq(Date doq) {
		this.doq = doq;
	}
	public Date getUpdatedat() {
		return updatedat;
	}
	public void setUpdatedat(Date updatedat) {
		this.updatedat = updatedat;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAvatarid() {
		return avatarid;
	}
	public void setAvatarid(int avatarid) {
		this.avatarid = avatarid;
	}
	public int getUpvotes() {
		return upvotes;
	}
	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}
	public int getDownvotes() {
		return downvotes;
	}
	public void setDownvotes(int downvotes) {
		this.downvotes = downvotes;
	}

}
