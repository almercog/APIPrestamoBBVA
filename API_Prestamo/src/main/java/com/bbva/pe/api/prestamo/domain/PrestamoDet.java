package com.bbva.pe.api.prestamo.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PrestamoDet extends Auditoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2867052671054171567L;
	private BigDecimal idPrestamoDet;
	private BigDecimal idPrestamo;
	private Date fecVencPagoCuota;
	private Integer periodo;
	private BigDecimal cuota;
	private BigDecimal interes;
	private BigDecimal amortizacion;
	private BigDecimal totalAmortizado;
	private BigDecimal capitalPendiente;

	public BigDecimal getIdPrestamoDet() {
		return idPrestamoDet;
	}

	public void setIdPrestamoDet(BigDecimal idPrestamoDet) {
		this.idPrestamoDet = idPrestamoDet;
	}

	public BigDecimal getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(BigDecimal idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public Date getFecVencPagoCuota() {
		return fecVencPagoCuota;
	}

	public void setFecVencPagoCuota(Date fecVencPagoCuota) {
		this.fecVencPagoCuota = fecVencPagoCuota;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public BigDecimal getCuota() {
		return cuota;
	}

	public void setCuota(BigDecimal cuota) {
		this.cuota = cuota;
	}

	public BigDecimal getInteres() {
		return interes;
	}

	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}

	public BigDecimal getAmortizacion() {
		return amortizacion;
	}

	public void setAmortizacion(BigDecimal amortizacion) {
		this.amortizacion = amortizacion;
	}

	public BigDecimal getTotalAmortizado() {
		return totalAmortizado;
	}

	public void setTotalAmortizado(BigDecimal totalAmortizado) {
		this.totalAmortizado = totalAmortizado;
	}

	public BigDecimal getCapitalPendiente() {
		return capitalPendiente;
	}

	public void setCapitalPendiente(BigDecimal capitalPendiente) {
		this.capitalPendiente = capitalPendiente;
	}

}
