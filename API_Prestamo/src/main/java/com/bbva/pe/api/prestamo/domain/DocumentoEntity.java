package com.bbva.pe.api.prestamo.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
@XmlRootElement
@Entity
@Table(name = "\"documento\"")
@NamedQueries({ @NamedQuery(name = "DocumentoEntity.findAll", query = "SELECT e FROM DocumentoEntity e"),
		@NamedQuery(name = "DocumentoEntity.findByUrl", query = "SELECT distinct d FROM DocumentoEntity d LEFT JOIN FETCH d.notas n WHERE d.url = :url")

})
public class DocumentoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"idDocumento\"")
	private Integer idDocumento;
	@Column(name = "\"url\"")
	private String url;
	@Column(name = "\"cantidadPaginas\"")
	private Integer cantidadPaginas;

	@OneToMany(mappedBy = "documento", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private List<NotaEntity> notas;

	public Integer getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getCantidadPaginas() {
		return cantidadPaginas;
	}

	public void setCantidadPaginas(Integer cantidadPaginas) {
		this.cantidadPaginas = cantidadPaginas;
	}

	public List<NotaEntity> getNotas() {
		return notas;
	}

	public void setNotas(List<NotaEntity> notas) {
		this.notas = notas;
	}

}
