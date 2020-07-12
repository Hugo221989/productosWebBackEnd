package com.shop.restfull.service.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shop.restfull.categoriaTest.TestUnitario;
import com.shop.restfull.model.Cesta;
import com.shop.restfull.model.Usuario;
import com.shop.restfull.model.producto.Producto;
import com.shop.restfull.model.producto.ProductoCesta;
import com.shop.restfull.service.ICestaService;

@Category(TestUnitario.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class CestaServiceUTest {

	@Autowired
	private ICestaService cestaService;
	private Cesta cesta;
	@Mock
	private Usuario usuario;
	@Mock
	private Producto producto;
	@Test
	public void givenCestaShouldReturnProductosCestaCorrectLength() {
		this.createCesta();
		Set<ProductoCesta> productosCesta = this.cestaService.createNewsProductosCesta(cesta);
		assertThat(productosCesta.size()).isEqualTo(1);
	}
	
	@Test
	public void givenNullCestaShouldReturnNull() {
		Set<ProductoCesta> productosCesta = this.cestaService.createNewsProductosCesta(null);
		assertThat(productosCesta).isNull();
	}
	@Test
	public void givenNullProductsCestaShouldReturnNull() {
		Cesta cestaNull = this.createCestaNullProducts();
		Set<ProductoCesta> productosCesta = this.cestaService.createNewsProductosCesta(cestaNull);
		assertThat(productosCesta).isNull();
	}
	
	private Cesta createCesta() {
		cesta = new Cesta();
		cesta.setId(1);
		cesta.setEnvio(25.0);
		cesta.setImporteSubTotal(80.0);
		cesta.setImporteTotal(80.0);
		cesta.setUsuario(usuario);
		cesta.setProductosCesta(this.createProductosCesta());
		return cesta;
	}
	private Set<ProductoCesta> createProductosCesta(){
		Set<ProductoCesta> productosCesta = new HashSet<>();
		ProductoCesta productoCesta = new ProductoCesta();
		productoCesta.setId(1);
		productoCesta.setCantidad(2);
		productoCesta.setProducto(producto);
		productosCesta.add(productoCesta);
		return productosCesta;
	}
	
	private Cesta createCestaNullProducts() {
		cesta = new Cesta();
		cesta.setId(1);
		cesta.setEnvio(25.0);
		cesta.setImporteSubTotal(80.0);
		cesta.setImporteTotal(80.0);
		cesta.setUsuario(usuario);
		cesta.setProductosCesta(null);
		return cesta;
	}
}
