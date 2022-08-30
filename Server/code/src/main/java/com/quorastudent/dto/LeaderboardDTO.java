package com.quorastudent.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "leaderboard")
public class LeaderboardDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String unvcode = null;
	private String username;
	private int useravatarid;
	private Long numberoflikes;
	private int position = 0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUnvcode() {
		return unvcode;
	}

	public void setUnvcode(String unvcode) {
		this.unvcode = unvcode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUseravatarid() {
		return useravatarid;
	}

	public void setUseravatarid(int useravatarid) {
		this.useravatarid = useravatarid;
	}

	public Long getNumberoflikes() {
		return numberoflikes;
	}

	public void setNumberoflikes(Long numberoflikes) {
		this.numberoflikes = numberoflikes;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}
