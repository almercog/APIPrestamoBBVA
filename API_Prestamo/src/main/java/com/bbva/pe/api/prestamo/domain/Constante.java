package com.bbva.pe.api.prestamo.domain;

import java.io.Serializable;

/**
 * 
 * @author almercog
 * @since 14.03.2018
 *
 */
public class Constante extends Auditoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2170493077380068494L;
	private String idConstante;
	private String dscConstante;
	private String idpTipoDato;
	private String valor;

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

	public String getIdpTipoDato() {
		return idpTipoDato;
	}

	public void setIdpTipoDato(String idpTipoDato) {
		this.idpTipoDato = idpTipoDato;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
