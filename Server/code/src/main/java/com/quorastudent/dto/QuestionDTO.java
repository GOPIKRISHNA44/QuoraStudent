package com.quorastudent.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class QuestionDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public String getEtype() {
		return etype;
	}

	public void setEtype(String etype) {
		this.etype = etype;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	private Date updatedat;

	private String etype;

	private int active;

	private String tags;

}
