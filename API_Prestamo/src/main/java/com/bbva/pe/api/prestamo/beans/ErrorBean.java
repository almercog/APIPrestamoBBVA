package com.bbva.pe.api.prestamo.beans;

import java.io.Serializable;

/**
 * 
 * @author xt0390
 * @since 14.03.2018
 */
public class ErrorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1956114931769168193L;
	private Integer codigo = 0;
	private String mensajeUx;
	private String mensajeDev;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getMensajeUx() {
		return mensajeUx;
	}

	public void setMensajeUx(String mensajeUx) {
		this.mensajeUx = mensajeUx;
	}

	public String getMensajeDev() {
		return mensajeDev;
	}

	public void setMensajeDev(String mensajeDev) {
		this.mensajeDev = mensajeDev;
	}
}