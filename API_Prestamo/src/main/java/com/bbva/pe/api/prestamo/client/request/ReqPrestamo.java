package com.bbva.pe.api.prestamo.client.request;

import java.io.Serializable;

import com.bbva.pe.api.prestamo.validate.Column;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqPrestamo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(nullable = true, num = true)
	private String idPrestamo;
	@Column(nullable = false, num = true)
	private String prestamo;
	@Column(nullable = false, num = true)
	private String tasa;
	@Column(nullable = false, length = true, maxlength = 3, num = true)
	private String plazo;
	@Column(nullable = false, length = true, maxlength = 1, alphanum = true)
	private String tipoPlazo;
	@Column(nullable = false)
	private String fecDesembolso;
	@Column(length = true, maxlength = 1, alphanum = true)
	private String indPeriodoGracia;
	@Column(length = true, maxlength = 3, num = true)
	private Integer periodoGracia;
	@Column(length = true, maxlength = 1, alphanum = true)
	private String indCapitalInteres;
	@Column(nullable = false, length = true, maxlength = 50, alphanum = true)
	private String origen;
	
	public String getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(String idPrestamo) {
		this.idPrestamo = idPrestamo;
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

	public String getFecDesembolso() {
		return fecDesembolso;
	}

	public void setFecDesembolso(String fecDesembolso) {
		this.fecDesembolso = fecDesembolso;
	}

	public String getIndPeriodoGracia() {
		return indPeriodoGracia;
	}

	public void setIndPeriodoGracia(String indPeriodoGracia) {
		this.indPeriodoGracia = indPeriodoGracia;
	}

	public Integer getPeriodoGracia() {
		return periodoGracia;
	}

	public void setPeriodoGracia(Integer periodoGracia) {
		this.periodoGracia = periodoGracia;
	}

	public String getIndCapitalInteres() {
		return indCapitalInteres;
	}

	public void setIndCapitalInteres(String indCapitalInteres) {
		this.indCapitalInteres = indCapitalInteres;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

}
