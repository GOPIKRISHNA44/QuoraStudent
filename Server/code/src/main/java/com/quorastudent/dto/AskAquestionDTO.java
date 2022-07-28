package com.quorastudent.dto;

import java.util.List;

public class AskAquestionDTO {

	private Long eqid = (long) -1;
	private String ctype = null;

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
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the tags
	 */
	public List<Integer> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<Integer> tags) {
		this.tags = tags;
	}

	public Long getEqid() {
		return eqid;
	}

	public void setEqid(Long eqid) {
		this.eqid = eqid;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	private Long userid;
	private String text;
	private List<Integer> tags;

}
