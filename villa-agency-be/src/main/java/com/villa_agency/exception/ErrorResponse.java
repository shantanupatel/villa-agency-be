package com.villa_agency.exception;

public class ErrorResponse {

	private int HttpStatus;
	private String message;

	public ErrorResponse(String message, int httpStatus) {
		super();
		this.message = message;
		HttpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public int getHttpStatus() {
		return HttpStatus;
	}

}
