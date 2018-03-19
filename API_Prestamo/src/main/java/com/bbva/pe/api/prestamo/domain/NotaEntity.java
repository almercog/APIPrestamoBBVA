package com.bbva.pe.api.prestamo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
@XmlRootElement
@Entity
@Table(name = "\"nota\"")
@NamedQueries({ @NamedQuery(name = "NotaEntity.findAll", query = "SELECT e FROM NotaEntity e") })
public class NotaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"idNota\"")
	private Integer idNota;
	@Column(name = "\"pagina\"")
	private Integer pagina;
	@Column(name = "\"left\"")
	private Integer left;
	@Column(name = "\"top\"")
	private Integer top;
	@Column(name = "\"contenido\"")
	private String contenido;
	@Column(name = "\"idDocumento\"")
	private Integer idDocumento;
	@Column(name = "\"color\"")
	private String color;
	@Column(name = "\"indActivo\"")
	private String indActivo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "\"idDocumento\"", insertable = false, updatable = false)
	private DocumentoEntity documento;

	public Integer getIdNota() {
		return idNota;
	}

	public void setIdNota(Integer idNota) {
		this.idNota = idNota;
	}

	public Integer getPagina() {
		return pagina;
	}

	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}

	public Integer getLeft() {
		return left;
	}

	public void setLeft(Integer left) {
		this.left = left;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Integer getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getIndActivo() {
		return indActivo;
	}

	public void setIndActivo(String indActivo) {
		this.indActivo = indActivo;
	}

	@XmlTransient
	public DocumentoEntity getDocumento() {
		return documento;
	}

	public void setDocumento(DocumentoEntity documento) {
		this.documento = documento;
	}

}
