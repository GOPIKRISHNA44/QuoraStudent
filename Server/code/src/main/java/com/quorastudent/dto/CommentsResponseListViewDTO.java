package com.quorastudent.dto;

import java.util.Date;

public class CommentsResponseListViewDTO {

	private Long cid;

	private Long cpid = (long) -1;

	private Long userid;

	private Long parentid;

	private String ctype;

	private String comment;

	private Date doc;

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Long getCpid() {
		return cpid;
	}

	public void setCpid(Long cpid) {
		this.cpid = cpid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getParentid() {
		return parentid;
	}

	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDoc() {
		return doc;
	}

	public void setDoc(Date doc) {
		this.doc = doc;
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

	private Date updatedat;

	private int active;

	private String commentedByUsername;
	private Long commentedByUsernameAvatarid;
	private Long totalNumberOfLikes;
	private Long totalNumberOfDislikes;
	private Long totalNumberOfComments;

	public String getCommentedByUsername() {
		return commentedByUsername;
	}

	public void setCommentedByUsername(String commentedByUsername) {
		this.commentedByUsername = commentedByUsername;
	}

	public Long getCommentedByUsernameAvatarid() {
		return commentedByUsernameAvatarid;
	}

	public void setCommentedByUsernameAvatarid(Long commentedByUsernameAvatarid) {
		this.commentedByUsernameAvatarid = commentedByUsernameAvatarid;
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

	public boolean isCommentOwnedByTheRequestedUser() {
		return commentOwnedByTheRequestedUser;
	}

	public void setCommentOwnedByTheRequestedUser(boolean commentOwnedByTheRequestedUser) {
		this.commentOwnedByTheRequestedUser = commentOwnedByTheRequestedUser;
	}

	private boolean likedByTheRequestedUser;
	private boolean disLikedByTheRequestedUser;
	private boolean commentOwnedByTheRequestedUser;

}
