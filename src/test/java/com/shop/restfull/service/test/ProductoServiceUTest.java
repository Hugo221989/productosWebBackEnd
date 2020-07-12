package com.shop.restfull.service.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shop.restfull.categoriaTest.TestUnitario;
import com.shop.restfull.dto.CatProductoDto;
import com.shop.restfull.dto.CategoriaDto;
import com.shop.restfull.model.producto.Categoria;
import com.shop.restfull.model.producto.Descripcion;
import com.shop.restfull.model.producto.Producto;
import com.shop.restfull.service.IProductoService;

@Category(TestUnitario.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductoServiceUTest {

	@Autowired
	private IProductoService productoService;
	
	@Mock
	private Descripcion descripcion;
	
	@Test
	public void givenProductoFillCategoriaProductoDto() {
		Producto producto = this.fillProducto();
		CatProductoDto categoriaProductoDto = new CatProductoDto();
		categoriaProductoDto = this.productoService.fillCatProductoDtoFromProducto(producto);
		assertThat(producto.getCategoria().getNombre()).isEqualTo(categoriaProductoDto.getCategoriaNombre());
	}
	
	@Test
	public void givenProductNullShouldReturnCatProductoDtoNull() {
		CatProductoDto categoriaProductoDto = new CatProductoDto();
		categoriaProductoDto = this.productoService.fillCatProductoDtoFromProducto(null);
		assertThat(categoriaProductoDto).isEqualTo(null);
	}
	
	public Producto fillProducto() {
		Producto producto = new Producto();
		producto.setId(1);
		producto.setDescuento(2.0);
		producto.setNombre("Pancakes");
		producto.setDescripcion(descripcion);
		Categoria categoria = new Categoria();
		categoria.setNombre("preentreno");
		producto.setCategoria(categoria);
		return producto;
	}
}
