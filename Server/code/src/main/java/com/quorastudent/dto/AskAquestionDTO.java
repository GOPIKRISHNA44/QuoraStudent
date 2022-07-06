package com.quorastudent.dto;

import java.util.List;

public class AskAquestionDTO {

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

	private Long userid;
	private String text;
	private List<Integer> tags;

}
