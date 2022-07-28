package com.quorastudent.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "answers")
public class AnswerDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long aid;

	private Long eqid;

	private Date doa;

	private Long userid;

	private String ctype;
	private Date updatedat;
	private int active;
	private String content;

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
