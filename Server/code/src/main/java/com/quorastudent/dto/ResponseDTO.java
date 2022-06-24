package com.quorastudent.dto;

public class ResponseDTO {

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	private Object data;
	private boolean success;
	private String reason;

	public ResponseDTO getSuccessResponse(Object data) {
		ResponseDTO responseDto = new ResponseDTO();
		responseDto.setData(data);
		responseDto.setSuccess(true);
		responseDto.setReason(null);
		return responseDto;
	}

	public ResponseDTO getFailureResponse(String reason) {
		ResponseDTO responseDto = new ResponseDTO();
		responseDto.setData(null);
		responseDto.setSuccess(true);
		responseDto.setReason(reason);
		return responseDto;
	}

}
