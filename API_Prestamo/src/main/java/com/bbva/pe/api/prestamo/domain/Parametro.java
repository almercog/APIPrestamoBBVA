package com.bbva.pe.api.prestamo.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author almercog
 * @since 14.03.2018
 *
 */
public class Parametro extends Auditoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6468762977642753373L;
	private Integer idPar;
	private String idTipPar;
	private String codigo;
	private String codigoC;
	private BigDecimal codigoN;
	private String abreviatura;
	private String descripcion;
	private String descripcion2;
	private String indActivo;

	public Integer getIdPar() {
		return idPar;
	}

	public void setIdPar(Integer idPar) {
		this.idPar = idPar;
	}

	public String getIdTipPar() {
		return idTipPar;
	}

	public void setIdTipPar(String idTipPar) {
		this.idTipPar = idTipPar;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoC() {
		return codigoC;
	}

	public void setCodigoC(String codigoC) {
		this.codigoC = codigoC;
	}

	public BigDecimal getCodigoN() {
		return codigoN;
	}

	public void setCodigoN(BigDecimal codigoN) {
		this.codigoN = codigoN;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion2() {
		return descripcion2;
	}

	public void setDescripcion2(String descripcion2) {
		this.descripcion2 = descripcion2;
	}

	public String getIndActivo() {
		return indActivo;
	}

	public void setIndActivo(String indActivo) {
		this.indActivo = indActivo;
	}

}
