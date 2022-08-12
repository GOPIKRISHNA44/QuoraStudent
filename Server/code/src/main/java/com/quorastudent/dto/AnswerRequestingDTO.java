package com.quorastudent.dto;

public class AnswerRequestingDTO {

	private Long userid;

	private String ctype;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long requestingUserId) {
		this.userid = requestingUserId;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public Long getEqid() {
		return eqid;
	}

	public void setEqid(Long eqid) {
		this.eqid = eqid;
	}

	private Long eqid;

}
