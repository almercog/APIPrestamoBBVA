package com.bbva.pe.api.prestamo.client.request;

import java.io.Serializable;

import com.bbva.pe.api.prestamo.validate.Column;

public class ReqEmail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6045819246179828761L;
	@Column(nullable = false, length = true, maxlength = 20, num = true)
	private String idPrestamo;
	@Column(nullable = false, length = true, maxlength = 1000, alphanum = true)
	private String email;

	public String getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(String idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
