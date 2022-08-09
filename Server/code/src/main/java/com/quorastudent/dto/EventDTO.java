package com.quorastudent.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class EventDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long eid;
	
	private Date fromdate;
	
	private Date todate;
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEid() {
		return eid;
	}

	public void setEid(Long eid) {
		this.eid = eid;
	}

	public Date getFrom() {
		return fromdate;
	}

	public void setFrom(Date from) {
		this.fromdate = from;
	}

	public Date getTo() {
		return todate;
	}

	public void setTo(Date to) {
		this.todate = to;
	}

	
	
	
	
}
