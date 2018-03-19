package com.bbva.pe.api.prestamo.beans;

import java.io.Serializable;

/**
 * 
 * @author almercog
 * @since 14.03.2018
 *
 */
public class ParametroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4768521108618393311L;
	private String idTipPar;
	private String abreviatura;
	private String descripcion;

	public String getIdTipPar() {
		return idTipPar;
	}

	public void setIdTipPar(String idTipPar) {
		this.idTipPar = idTipPar;
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

}
