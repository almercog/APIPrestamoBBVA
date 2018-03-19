package com.bbva.pe.api.prestamo.exceptions;

public class AbstractException extends Exception {

	private static final long serialVersionUID = 1L;

	protected ExceptionResponse response;

	protected AbstractException(int status, String message) {
		super(message);
		response = new ExceptionResponse(status, message);
	}

	protected AbstractException(int status, String code, String message) {
		super(message);
		response = new ExceptionResponse(status, code, message);
	}

	protected AbstractException(int status, String code, String message, String userMessage) {
		super(message);
		response = new ExceptionResponse(status, code, message, userMessage);
	}

	protected AbstractException(int status, String code, String message, String userMessage, String detail) {
		super(message);
		response = new ExceptionResponse(status, code, message, userMessage, detail);
	}

	public ExceptionResponse getResponse() {
		return response;
	}

	public void setResponse(ExceptionResponse response) {
		this.response = response;
	}

	

}
