package com.bbva.pe.api.prestamo.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Prestamo extends Auditoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7467072073102548177L;
	private BigDecimal idPrestamo;
	private BigDecimal prestamo;
	private BigDecimal tasa;
	private Integer plazo;
	private String tipoPlazo;
	private Date fecDesembolso;
	private Date fecIniPrestamo;
	private Date fecFinPrestamo;
	private String indPeriodoGracia;
	private Integer periodoGracia;
	private String indCapInteres;
	private String email;
	private String url;
	private String origenTrans;

	public BigDecimal getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(BigDecimal idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public Date getFecDesembolso() {
		return fecDesembolso;
	}

	public void setFecDesembolso(Date fecDesembolso) {
		this.fecDesembolso = fecDesembolso;
	}

	public Date getFecIniPrestamo() {
		return fecIniPrestamo;
	}

	public void setFecIniPrestamo(Date fecIniPrestamo) {
		this.fecIniPrestamo = fecIniPrestamo;
	}

	public Date getFecFinPrestamo() {
		return fecFinPrestamo;
	}

	public void setFecFinPrestamo(Date fecFinPrestamo) {
		this.fecFinPrestamo = fecFinPrestamo;
	}

	public BigDecimal getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(BigDecimal prestamo) {
		this.prestamo = prestamo;
	}

	public BigDecimal getTasa() {
		return tasa;
	}

	public void setTasa(BigDecimal tasa) {
		this.tasa = tasa;
	}

	public Integer getPlazo() {
		return plazo;
	}

	public void setPlazo(Integer plazo) {
		this.plazo = plazo;
	}

	public String getIndPeriodoGracia() {
		return indPeriodoGracia;
	}

	public String getTipoPlazo() {
		return tipoPlazo;
	}

	public void setTipoPlazo(String tipoPlazo) {
		this.tipoPlazo = tipoPlazo;
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

	public String getIndCapInteres() {
		return indCapInteres;
	}

	public void setIndCapInteres(String indCapInteres) {
		this.indCapInteres = indCapInteres;
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

	public String getOrigenTrans() {
		return origenTrans;
	}

	public void setOrigenTrans(String origenTrans) {
		this.origenTrans = origenTrans;
	}

}
