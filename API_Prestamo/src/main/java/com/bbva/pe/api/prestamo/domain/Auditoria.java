package com.bbva.pe.api.prestamo.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author almercog
 * @since 14.03.2018
 *
 */
public class Auditoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1461720421789813595L;
	private String usuCreacion;
	private Date fecCreacion;
	private String usuModif;
	private Date fecModif;

	public String getUsuCreacion() {
		return usuCreacion;
	}

	public void setUsuCreacion(String usuCreacion) {
		this.usuCreacion = usuCreacion;
	}

	public Date getFecCreacion() {
		return fecCreacion;
	}

	public void setFecCreacion(Date fecCreacion) {
		this.fecCreacion = fecCreacion;
	}

	public String getUsuModif() {
		return usuModif;
	}

	public void setUsuModif(String usuModif) {
		this.usuModif = usuModif;
	}

	public Date getFecModif() {
		return fecModif;
	}

	public void setFecModif(Date fecModif) {
		this.fecModif = fecModif;
	}

}
