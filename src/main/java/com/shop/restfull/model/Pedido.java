package com.shop.restfull.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.restfull.model.producto.Producto;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "num_pedido")
	private String numPedido;
	
	@ManyToOne
	@JoinColumn(name = "FK_PEDIDO_METODO_ENVIO", referencedColumnName = "id")
	private MetodoEnvio metodoEnvio;
	
	@ManyToOne
	@JoinColumn(name = "FK_PEDIDO_METODO_PAGO", referencedColumnName = "id")
	private MetodoPago metodoPago;
	
	private String destinatario;
	
	private Integer cantidadProductos;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaPedido;
	
	private Double precioTotal;
	
	private boolean finalizado;
	
	private boolean pagado;
	
	private boolean enviado;
	
	@ManyToOne
	@JoinColumn(name = "FK_USUARIO", nullable = false, updatable = false)
	private Usuario usuario;
	
	@JoinTable(
	        name = "rel_pedidos_productos",
	        joinColumns = @JoinColumn(name = "FK_PEDIDO", nullable = false),
	        inverseJoinColumns = @JoinColumn(name="FK_PRODUCTO", nullable = false)
	    )
	@ManyToMany(cascade = CascadeType.ALL)
    private List<Producto> productos;
	
	@Column(name = "precio_envio")
	private Double precioEnvio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getCantidadProductos() {
		return cantidadProductos;
	}

	public void setCantidadProductos(Integer cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Double getPrecioEnvio() {
		return precioEnvio;
	}

	public void setPrecioEnvio(Double precioEnvio) {
		this.precioEnvio = precioEnvio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	public void addProductos(Producto producto){
        if(this.productos == null){
            this.productos = new ArrayList<>();
        }
        
        this.productos.add(producto);
    }

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	public boolean isEnviado() {
		return enviado;
	}

	public void setEnviado(boolean enviado) {
		this.enviado = enviado;
	}

	public String getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(String numPedido) {
		this.numPedido = numPedido;
	}

	public MetodoEnvio getMetodoEnvio() {
		return metodoEnvio;
	}

	public void setMetodoEnvio(MetodoEnvio metodoEnvio) {
		this.metodoEnvio = metodoEnvio;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public MetodoPago getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(MetodoPago metodoPago) {
		this.metodoPago = metodoPago;
	}

}
