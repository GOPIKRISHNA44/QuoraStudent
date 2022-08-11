package com.quorastudent.dto;

import java.util.List;

public class FeedRequestDTO {

	public int getPageNumber() {
		return pageNumber;
	}

	public int getUnvcode() {
		return unvcode;
	}

	public void setUnvcode(int unvCode) {
		this.unvcode = unvCode;
	}

	private int unvcode;

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getNumberOfPostsRequired() {
		return numberOfPostsRequired;
	}

	public void setNumberOfPostsRequired(int numberOfPostsRequired) {
		this.numberOfPostsRequired = numberOfPostsRequired;
	}

	private String ctype;

	private Long userid;

	private int pageNumber;

	private int numberOfPostsRequired;

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public List<Long> getVisitedEqids() {
		return visitedEqids;
	}

	public void setVisitedEqids(List<Long> visitedEqids) {
		this.visitedEqids = visitedEqids;
	}

	private List<Long> visitedEqids;

}
