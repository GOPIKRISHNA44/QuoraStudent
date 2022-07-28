package com.quorastudent.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "blog")
public class BlogDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bid;

	private String title;
	
	private String content;
	
	private Date doblog;
	
	private Date updatedat;
	
	private int active;

	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDoblog() {
		return doblog;
	}

	public void setDoblog(Date doblog) {
		this.doblog = doblog;
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

}
