package com.bbva.pe.api.prestamo.exceptions;

import javax.ws.rs.core.Response;

public class ValidationException extends AbstractException {

	private static final long serialVersionUID = 1L;
	
	public ValidationException(String message){
		super(Response.Status.BAD_REQUEST.getStatusCode(), message);
	}
	
	public ValidationException(String code, String message){
		super(Response.Status.BAD_REQUEST.getStatusCode(), code, message);
	}

}
