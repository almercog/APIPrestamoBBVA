package com.bbva.pe.api.prestamo.beans;

public class RespuestaAPI {
	private int responseCode;
	private String body;
	private String cookies;
	
	public RespuestaAPI(){}
	
	public RespuestaAPI(int responseCode, String body, String cookies){
		this.responseCode = responseCode;
		this.body = body;
		this.cookies = cookies;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}
}