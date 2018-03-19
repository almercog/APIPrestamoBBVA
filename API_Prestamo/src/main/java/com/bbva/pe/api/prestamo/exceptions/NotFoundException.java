package com.bbva.pe.api.prestamo.exceptions;

import javax.ws.rs.core.Response;

public class NotFoundException extends AbstractException {

	private static final long serialVersionUID = 1L;
	
	public NotFoundException(String message){
		super(Response.Status.NOT_FOUND.getStatusCode(), message);
	}
	
	public NotFoundException(String code, String message){
		super(Response.Status.NOT_FOUND.getStatusCode(), code, message);
	}

}
