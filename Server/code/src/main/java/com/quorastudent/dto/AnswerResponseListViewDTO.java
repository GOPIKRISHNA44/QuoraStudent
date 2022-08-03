package com.quorastudent.dto;

import java.util.Date;

public class AnswerResponseListViewDTO {

	private Long aid;

	private Long eqid;

	public String getAnsweredByUsername() {
		return answeredByUsername;
	}

	public void setAnsweredByUsername(String answeredByUsername) {
		this.answeredByUsername = answeredByUsername;
	}

	public String getAnsweredByUsernameAvatarid() {
		return answeredByUsernameAvatarid;
	}

	public void setAnsweredByUsernameAvatarid(String answeredByUsernameAvatarid) {
		this.answeredByUsernameAvatarid = answeredByUsernameAvatarid;
	}

	public Long getTotalNumberOfLikes() {
		return totalNumberOfLikes;
	}

	public void setTotalNumberOfLikes(Long totalNumberOfLikes) {
		this.totalNumberOfLikes = totalNumberOfLikes;
	}

	public Long getTotalNumberOfDislikes() {
		return totalNumberOfDislikes;
	}

	public void setTotalNumberOfDislikes(Long totalNumberOfDislikes) {
		this.totalNumberOfDislikes = totalNumberOfDislikes;
	}

	public Long getTotalNumberOfComments() {
		return totalNumberOfComments;
	}

	public void setTotalNumberOfComments(Long totalNumberOfComments) {
		this.totalNumberOfComments = totalNumberOfComments;
	}

	public boolean isLikedByTheRequestedUser() {
		return likedByTheRequestedUser;
	}

	public void setLikedByTheRequestedUser(boolean likedByTheRequestedUser) {
		this.likedByTheRequestedUser = likedByTheRequestedUser;
	}

	public boolean isDisLikedByTheRequestedUser() {
		return disLikedByTheRequestedUser;
	}

	public void setDisLikedByTheRequestedUser(boolean disLikedByTheRequestedUser) {
		this.disLikedByTheRequestedUser = disLikedByTheRequestedUser;
	}

	public boolean isAnswerOwnedByTheRequestedUser() {
		return answerOwnedByTheRequestedUser;
	}

	public void setAnswerOwnedByTheRequestedUser(boolean answerOwnedByTheRequestedUser) {
		this.answerOwnedByTheRequestedUser = answerOwnedByTheRequestedUser;
	}

	private Date doa;

	private Long userid;

	private String ctype;
	private Date updatedat;
	private int active;
	private String content;

	private String answeredByUsername;
	private String answeredByUsernameAvatarid;
	private Long totalNumberOfLikes;
	private Long totalNumberOfDislikes;
	private Long totalNumberOfComments;
	private boolean likedByTheRequestedUser;
	private boolean disLikedByTheRequestedUser;
	private boolean answerOwnedByTheRequestedUser;

	public Long getAid() {
		return aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}

	public Long getEqid() {
		return eqid;
	}

	public void setEqid(Long eqid) {
		this.eqid = eqid;
	}

	public Date getDoa() {
		return doa;
	}

	public void setDoa(Date doa) {
		this.doa = doa;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public Date getUpdatedat() {
		return updatedat;
	}

	public void setUpdatedat(Date updatedat) {
		this.updatedat = updatedat;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
