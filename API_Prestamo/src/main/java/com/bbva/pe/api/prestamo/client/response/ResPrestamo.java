package com.bbva.pe.api.prestamo.client.response;

import java.io.Serializable;
import java.util.List;

import com.bbva.pe.api.prestamo.beans.ErrorBean;
import com.bbva.pe.api.prestamo.domain.Prestamo;
import com.bbva.pe.api.prestamo.domain.PrestamoDet;

public class ResPrestamo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 957748204958619639L;
	private Integer codMensaje;
	private String dscMensaje;
	private Prestamo prestamo;
	private List<PrestamoDet> lstPrestamoDet;
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

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public List<PrestamoDet> getLstPrestamoDet() {
		return lstPrestamoDet;
	}

	public void setLstPrestamoDet(List<PrestamoDet> lstPrestamoDet) {
		this.lstPrestamoDet = lstPrestamoDet;
	}

	public ErrorBean getError() {
		return error;
	}

	public void setError(ErrorBean error) {
		this.error = error;
	}

}
