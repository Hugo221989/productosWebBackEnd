package com.shop.restfull.model.producto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "valor_nutricional")
public class ValorNutricional implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private Integer dosis;
	
	@Column(name = "dosis_envase")
	private Integer dosisEnvase;
	
	@Column(name = "dosis_diaria")
	private Integer dosisDiaria;
	@OneToOne(mappedBy = "valorNutricional", cascade = CascadeType.ALL)
	private InfoBasica infoBasica;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "valorNutricional")
	private List<InfoVitaminas> infoVitaminas;
	
	@Column(name = "ingredientes", columnDefinition="TEXT")
	private String ingredientes;
	
	@Column(name = "ingredientes_eng", columnDefinition="TEXT")
	private String ingredientesEng;
	
	@Column(name = "otros_ingredientes", columnDefinition="TEXT")
	private String otrosIngredientes;
	
	@Column(name = "otros_ingredientes_eng", columnDefinition="TEXT")
	private String otrosIngredientesEng;
	
	private String conservacion;
	
	@Column(name = "conservacion_eng")
	private String conservacionEng;
	
	@Column(name = "alergias", columnDefinition="TEXT")
	private String alergias;
	
	@Column(name = "alergias_eng", columnDefinition="TEXT")
	private String alergiasEng;
	
	@Column(name = "modo_empleo")
	private String modoEmpleo;
	
	@Column(name = "modo_empleo_eng")
	private String modoEmpleoEng;
	
	@Column(name = "advertencias", columnDefinition="TEXT")
	private String advertencias;
	
	@Column(name = "advertencias_eng", columnDefinition="TEXT")
	private String advertenciasEng;
	
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

	public Integer getDosis() {
		return dosis;
	}

	public void setDosis(Integer dosis) {
		this.dosis = dosis;
	}

	public Integer getDosisEnvase() {
		return dosisEnvase;
	}

	public void setDosisEnvase(Integer dosisEnvase) {
		this.dosisEnvase = dosisEnvase;
	}

	public Integer getDosisDiaria() {
		return dosisDiaria;
	}

	public void setDosisDiaria(Integer dosisDiaria) {
		this.dosisDiaria = dosisDiaria;
	}

	public InfoBasica getInfoBasica() {
		return infoBasica;
	}

	public void setInfoBasica(InfoBasica infoBasica) {
		this.infoBasica = infoBasica;
	}

	public List<InfoVitaminas> getInfoVitaminas() {
		return infoVitaminas;
	}

	public void setInfoVitaminas(List<InfoVitaminas> infoVitaminas) {
		this.infoVitaminas = infoVitaminas;
	}

	public String getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}

	public String getOtrosIngredientes() {
		return otrosIngredientes;
	}

	public void setOtrosIngredientes(String otrosIngredientes) {
		this.otrosIngredientes = otrosIngredientes;
	}

	public String getConservacion() {
		return conservacion;
	}

	public void setConservacion(String conservacion) {
		this.conservacion = conservacion;
	}

	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}

	public String getModoEmpleo() {
		return modoEmpleo;
	}

	public void setModoEmpleo(String modoEmpleo) {
		this.modoEmpleo = modoEmpleo;
	}

	public String getAdvertencias() {
		return advertencias;
	}

	public void setAdvertencias(String advertencias) {
		this.advertencias = advertencias;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getIngredientesEng() {
		return ingredientesEng;
	}

	public void setIngredientesEng(String ingredientesEng) {
		this.ingredientesEng = ingredientesEng;
	}

	public String getOtrosIngredientesEng() {
		return otrosIngredientesEng;
	}

	public void setOtrosIngredientesEng(String otrosIngredientesEng) {
		this.otrosIngredientesEng = otrosIngredientesEng;
	}

	public String getConservacionEng() {
		return conservacionEng;
	}

	public void setConservacionEng(String conservacionEng) {
		this.conservacionEng = conservacionEng;
	}

	public String getAlergiasEng() {
		return alergiasEng;
	}

	public void setAlergiasEng(String alergiasEng) {
		this.alergiasEng = alergiasEng;
	}

	public String getModoEmpleoEng() {
		return modoEmpleoEng;
	}

	public void setModoEmpleoEng(String modoEmpleoEng) {
		this.modoEmpleoEng = modoEmpleoEng;
	}

	public String getAdvertenciasEng() {
		return advertenciasEng;
	}

	public void setAdvertenciasEng(String advertenciasEng) {
		this.advertenciasEng = advertenciasEng;
	}
	
	
}
