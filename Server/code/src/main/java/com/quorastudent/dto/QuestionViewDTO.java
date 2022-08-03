package com.quorastudent.dto;

import java.util.Date;

public class QuestionViewDTO {

	private Long eqid;

	private Long userid;

	private String question;

	private Date doq;

	/**
	 * @return the eqid
	 */
	public Long getEqid() {
		return eqid;
	}

	/**
	 * @param eqid the eqid to set
	 */
	public void setEqid(Long eqid) {
		this.eqid = eqid;
	}

	/**
	 * @return the userid
	 */
	public Long getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(Long userid) {
		this.userid = userid;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the doq
	 */
	public Date getDoq() {
		return doq;
	}

	/**
	 * @param doq the doq to set
	 */
	public void setDoq(Date doq) {
		this.doq = doq;
	}

	/**
	 * @return the updatedat
	 */
	public Date getUpdatedat() {
		return updatedat;
	}

	/**
	 * @param updatedat the updatedat to set
	 */
	public void setUpdatedat(Date updatedat) {
		this.updatedat = updatedat;
	}

	/**
	 * @return the active
	 */
	public int getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(int active) {
		this.active = active;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	private Date updatedat;

	private String ctype;

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
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

	private int active;

	private String tags;

	private Long totalNumberOfLikes = (long) 0;

	private Long totalNumberOfDislikes = (long) 0;

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

	public boolean isQuestionOwnedByTheRequestedUser() {
		return questionOwnedByTheRequestedUser;
	}

	public void setQuestionOwnedByTheRequestedUser(boolean questionOwnedByTheRequestedUser) {
		this.questionOwnedByTheRequestedUser = questionOwnedByTheRequestedUser;
	}

	public String getUsernameOfWhoAskedThisQuestion() {
		return usernameOfWhoAskedThisQuestion;
	}

	public void setUsernameOfWhoAskedThisQuestion(String usernameOfWhoAskedThisQuestion) {
		this.usernameOfWhoAskedThisQuestion = usernameOfWhoAskedThisQuestion;
	}

	public int getAvataridOfWhoAskedThisQuestion() {
		return avataridOfWhoAskedThisQuestion;
	}

	public void setAvataridOfWhoAskedThisQuestion(int avataridOfWhoAskedThisQuestion) {
		this.avataridOfWhoAskedThisQuestion = avataridOfWhoAskedThisQuestion;
	}

	private Long totalNumberOfComments = (long) 0;

	private boolean likedByTheRequestedUser = false;
	private boolean disLikedByTheRequestedUser = false;

	private boolean questionOwnedByTheRequestedUser = false;

	private String usernameOfWhoAskedThisQuestion;

	private int avataridOfWhoAskedThisQuestion;

}
