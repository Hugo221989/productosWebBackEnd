package com.shop.restfull.model.producto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.restfull.model.Usuario;
@Entity
@Table(name = "descripcion")
public class Descripcion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String titulo;
	
	private String subtitulo;
	
	private String apartado;
	
	private String caracteristicas;
	
	private String beneficios;
	
	@Column(name = "titulo_eng")
	private String tituloEng;
	
	@Column(name = "subtitulo_eng")
	private String subtituloEng;
	
	@Column(name = "apartado_eng")
	private String apartadoEng;
	
	@Column(name = "caracteristicas_eng")
	private String caracteristicasEng;
	
	@Column(name = "beneficios_eng")
	private String beneficiosEng;
	
	@OneToOne
    @JoinColumn(name = "FK_PRODUCTO", updatable = false, nullable = false)
	@JsonIgnore
    private Producto producto;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public String getApartado() {
		return apartado;
	}

	public void setApartado(String apartado) {
		this.apartado = apartado;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public String getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(String beneficios) {
		this.beneficios = beneficios;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getTituloEng() {
		return tituloEng;
	}

	public void setTituloEng(String tituloEng) {
		this.tituloEng = tituloEng;
	}

	public String getSubtituloEng() {
		return subtituloEng;
	}

	public void setSubtituloEng(String subtituloEng) {
		this.subtituloEng = subtituloEng;
	}

	public String getApartadoEng() {
		return apartadoEng;
	}

	public void setApartadoEng(String apartadoEng) {
		this.apartadoEng = apartadoEng;
	}

	public String getCaracteristicasEng() {
		return caracteristicasEng;
	}

	public void setCaracteristicasEng(String caracteristicasEng) {
		this.caracteristicasEng = caracteristicasEng;
	}

	public String getBeneficiosEng() {
		return beneficiosEng;
	}

	public void setBeneficiosEng(String beneficiosEng) {
		this.beneficiosEng = beneficiosEng;
	}
	
}
