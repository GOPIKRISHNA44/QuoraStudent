package com.quorastudent.dto;

import java.util.List;

public class UpdateInterestsDTO {

	private Long userid;
	private List<InterestsDTO> interests;

	public List<InterestsDTO> getInterests() {
		return interests;
	}

	public void setInterests(List<InterestsDTO> userInterests) {
		this.interests = userInterests;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

}
