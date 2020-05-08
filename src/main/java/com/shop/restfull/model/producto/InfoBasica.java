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
@Table(name = "info_basica")
public class InfoBasica implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "valor_energetico")
	private Integer ValorEnergetico;
	
	private Integer proteinas;
	
	private Integer hidratos;
	
	private Integer azucares;
	
	private Integer grasas;
	
	private Integer saturadas;
	
	private Integer sodio;
	
	@OneToOne
    @JoinColumn(name = "FK_VALOR_NUTRICIONAL", updatable = false, nullable = false)
	@JsonIgnore
    private ValorNutricional valorNutricional;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getValorEnergetico() {
		return ValorEnergetico;
	}

	public void setValorEnergetico(Integer valorEnergetico) {
		ValorEnergetico = valorEnergetico;
	}

	public Integer getProteinas() {
		return proteinas;
	}

	public void setProteinas(Integer proteinas) {
		this.proteinas = proteinas;
	}

	public Integer getHidratos() {
		return hidratos;
	}

	public void setHidratos(Integer hidratos) {
		this.hidratos = hidratos;
	}

	public Integer getAzucares() {
		return azucares;
	}

	public void setAzucares(Integer azucares) {
		this.azucares = azucares;
	}

	public Integer getGrasas() {
		return grasas;
	}

	public void setGrasas(Integer grasas) {
		this.grasas = grasas;
	}

	public Integer getSaturadas() {
		return saturadas;
	}

	public void setSaturadas(Integer saturadas) {
		this.saturadas = saturadas;
	}

	public Integer getSodio() {
		return sodio;
	}

	public void setSodio(Integer sodio) {
		this.sodio = sodio;
	}

	public ValorNutricional getValorNutricional() {
		return valorNutricional;
	}

	public void setValorNutricional(ValorNutricional valorNutricional) {
		this.valorNutricional = valorNutricional;
	}

}
