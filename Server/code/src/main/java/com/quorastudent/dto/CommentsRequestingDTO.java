package com.quorastudent.dto;

public class CommentsRequestingDTO {

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



	public Long getEqabcid() {
		return eqabcid;
	}

	public void setEqabcid(Long eqabcid) {
		this.eqabcid = eqabcid;
	}



	private Long eqabcid;

}
