package com.quorastudent.dto;

public class AnswerRequestingDTO {

	private Long requestingUserId;

	private String ctype;

	public Long getRequestingUserId() {
		return requestingUserId;
	}

	public void setRequestingUserId(Long requestingUserId) {
		this.requestingUserId = requestingUserId;
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
