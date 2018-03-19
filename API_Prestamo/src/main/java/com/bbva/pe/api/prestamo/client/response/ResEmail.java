package com.bbva.pe.api.prestamo.client.response;

import java.io.Serializable;

import com.bbva.pe.api.prestamo.beans.ErrorBean;

public class ResEmail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5295177723910206059L;
	private Integer codMensaje;
	private String dscMensaje;
	private ErrorBean error;

	public Integer getCodMensaje() {
		return codMensaje;
	}

	public void setCodMensaje(Integer codMensaje) {
		this.codMensaje = codMensaje;
	}

	public String getDscMensaje() {
		return dscMensaje;
	}

	public void setDscMensaje(String dscMensaje) {
		this.dscMensaje = dscMensaje;
	}

	public ErrorBean getError() {
		return error;
	}

	public void setError(ErrorBean error) {
		this.error = error;
	}

}
