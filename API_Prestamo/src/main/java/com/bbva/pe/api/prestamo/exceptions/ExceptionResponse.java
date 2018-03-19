package com.bbva.pe.api.prestamo.exceptions;

public class ExceptionResponse {

	protected int status;
	protected String code;
	protected String message;
	protected String userMessage;
	protected String detail;

	protected ExceptionResponse(int status, String message) {
		this.setStatus(status);
		this.message = message;
	}

	protected ExceptionResponse(int status, String code, String message) {
		this.setStatus(status);
		this.code = code;
		this.message = message;
	}

	protected ExceptionResponse(int status, String code, String message, String userMessage) {
		this.setStatus(status);
		this.code = code;
		this.message = message;
		this.userMessage = userMessage;
	}

	protected ExceptionResponse(int status, String code, String message, String userMessage, String detail) {
		this.setStatus(status);
		this.code = code;
		this.message = message;
		this.userMessage = userMessage;
		this.detail = detail;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
