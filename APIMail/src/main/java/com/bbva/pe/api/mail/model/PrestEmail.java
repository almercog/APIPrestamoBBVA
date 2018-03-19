package com.bbva.pe.api.mail.model;

import java.io.Serializable;

public class PrestEmail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8498701007457041367L;
	private String prestamo;
	private String tasa;
	private String plazo;
	private String tipoPlazo;
	private String fecIniPrestamo;
	private String fecFinPrestamo;
	private String email;
	private String url;

	@Override
	public String toString() {
		return "Prestamo [prestamo=" + prestamo + ", tasa=" + tasa + ", plazo=" + plazo + ", tipoPlazo=" + tipoPlazo
				+ ", fecIniPrestamo=" + fecIniPrestamo + ", fecFinPrestamo=" + fecFinPrestamo + ", email=" + email
				+ "]";
	}

	public String getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(String prestamo) {
		this.prestamo = prestamo;
	}

	public String getTasa() {
		return tasa;
	}

	public void setTasa(String tasa) {
		this.tasa = tasa;
	}

	public String getPlazo() {
		return plazo;
	}

	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	public String getTipoPlazo() {
		return tipoPlazo;
	}

	public void setTipoPlazo(String tipoPlazo) {
		this.tipoPlazo = tipoPlazo;
	}

	public String getFecIniPrestamo() {
		return fecIniPrestamo;
	}

	public void setFecIniPrestamo(String fecIniPrestamo) {
		this.fecIniPrestamo = fecIniPrestamo;
	}

	public String getFecFinPrestamo() {
		return fecFinPrestamo;
	}

	public void setFecFinPrestamo(String fecFinPrestamo) {
		this.fecFinPrestamo = fecFinPrestamo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}