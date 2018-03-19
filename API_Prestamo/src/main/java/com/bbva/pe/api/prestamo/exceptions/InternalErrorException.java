package com.bbva.pe.api.prestamo.exceptions;

import javax.ws.rs.core.Response;

public class InternalErrorException extends AbstractException{

	private static final long serialVersionUID = 1L;
	
	public InternalErrorException(String message){
		super(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), message);
	}
	
	public InternalErrorException(String message, String detail){
		super(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), null, message, null, detail);
	}
}
