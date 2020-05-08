package com.shop.restfull.model.producto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "info_vitaminas")
public class InfoVitaminas implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String nombre;
	
	private String valor;
	
	@ManyToOne
	@JoinColumn(name = "FK_VALOR_NUTRICIONAL", referencedColumnName = "id")
	@JsonIgnore
	private ValorNutricional valorNutricional;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public ValorNutricional getValorNutricional() {
		return valorNutricional;
	}

	public void setValorNutricional(ValorNutricional valorNutricional) {
		this.valorNutricional = valorNutricional;
	}


}
