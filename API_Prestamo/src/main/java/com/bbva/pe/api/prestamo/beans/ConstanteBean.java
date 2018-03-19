package com.bbva.pe.api.prestamo.beans;

import java.io.Serializable;

/**
 * 
 * @author almercog
 * @since 14.03.2018
 *
 */
public class ConstanteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -312348788831754599L;
	private String idConstante;
	private String dscConstante;

	public String getIdConstante() {
		return idConstante;
	}

	public void setIdConstante(String idConstante) {
		this.idConstante = idConstante;
	}

	public String getDscConstante() {
		return dscConstante;
	}

	public void setDscConstante(String dscConstante) {
		this.dscConstante = dscConstante;
	}
}
