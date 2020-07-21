package com.shop.restfull.repository.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.shop.restfull.config.H2TestProfileJPAConfig;
import com.shop.restfull.dto.ProductoDto;
import com.shop.restfull.model.producto.Categoria;
import com.shop.restfull.model.producto.CategoriaPadre;
import com.shop.restfull.model.producto.Producto;
import com.shop.restfull.model.producto.SubCategoria;
import com.shop.restfull.repository.CategoriaPadreRepository;
import com.shop.restfull.repository.CategoriaRepository;
import com.shop.restfull.repository.ProductoRepository;
import com.shop.restfull.repository.SubCategoriaRepository;

@ContextConfiguration(classes = { H2TestProfileJPAConfig.class }, 
loader = AnnotationConfigContextLoader.class)
@Transactional
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductoRepositoryTest {
	@Autowired
	private ProductoRepository productoRespository;
	
	@Autowired
	private CategoriaPadreRepository categoriaPadreRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private SubCategoriaRepository subCategoriaRepository;
	
	private CategoriaPadre categoriaPadre;
	private Categoria categoria;
	private SubCategoria subCategoria;
	private List<ProductoDto> productosDto;
	private List<Producto> productos;
	private Producto producto1;
	
	@Before
	public void loadCategoriasAndProducts() {
		this.loadCategoriasNutricion();
		this.loadProducts();
	}
	
	@Test
	public void findAllProductsShouldReturnCorrectSize() {
		this.productosDto = this.productoRespository.findAllProducts();
		assertThat(this.productosDto.size()).isEqualTo(this.productos.size());
	}
	
	@Test
	public void givenProductIdShouldReturnProductoDto() {
		ProductoDto productoDto = this.productoRespository.findProductoDtoById(this.producto1.getId());
		assertThat(productoDto.getNombre()).isEqualTo(this.producto1.getNombre());
	}
	
	@Test
	public void givenProductIdNullShouldReturnNull() {
		ProductoDto productoDto = this.productoRespository.findProductoDtoById(0);
		assertThat(productoDto).isNull();
	}
	
	@Test
	public void givenCategoriaPadreKeyShouldReturnRelatedProducts() {
		Pageable pag = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "id"));
		List<ProductoDto> productosDto = this.productoRespository.findRelatedProducts(pag, this.categoriaPadre.getKkey());
		assertThat(productosDto.size()).isEqualTo(this.productos.size());
	}
	
	@Test
	public void givenWrongCategoriaPadreKeyShouldReturnEmptyList() {
		Pageable pag = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "id"));
		List<ProductoDto> productosDto = this.productoRespository.findRelatedProducts(pag, "mojon");
		assertThat(productosDto).isEmpty();
	}
	
	@Test
	public void givenCategoriaKeyShouldReturnProducts() {
		List<ProductoDto> productosDto = this.productoRespository.findProductsByCategoria(this.categoria.getKkey());
		assertThat(productosDto.size()).isEqualTo(this.productos.size());
	}
	
	@Test
	public void givenWrongCategoriaKeyShouldReturnEmptyList() {
		List<ProductoDto> productosDto = this.productoRespository.findProductsByCategoria("chucha");
		assertThat(productosDto).isEmpty();
	}
	
	@Test
	public void givenSubCategoriaKeyShouldReturnProducts() {
		List<ProductoDto> productosDto = this.productoRespository.findProductsBySubCategoria(this.subCategoria.getKkey());
		assertThat(productosDto.size()).isEqualTo(this.productos.size());
	}
	
	@Test
	public void givenNullSubCategoriaKeyShouldReturnEmptyList() {
		List<ProductoDto> productosDto = this.productoRespository.findProductsBySubCategoria(null);
		assertThat(productosDto).isEmpty();
	}
	
	@Test
	public void givenTextShouldReturnProduct() {
		List<ProductoDto> productosDto = this.productoRespository.findProductsByBuscador("Iso");
		assertThat(productosDto.size()).isEqualTo(1);
	}
	
	@Test
	public void givenNullTextShouldReturnEmptyList() {
		List<ProductoDto> productosDto = this.productoRespository.findProductsByBuscador(null);
		assertThat(productosDto).isEmpty();
	}
	
	private void loadCategoriasNutricion() {
		this.categoriaPadre = new CategoriaPadre();
		this.categoriaPadre.setKkey("nutricion");
		this.categoriaPadre.setNombre("nutricion");
		this.categoriaPadreRepository.save(this.categoriaPadre);
		
		this.categoria = new Categoria();
		this.categoria.setKkey("proteina");
		this.categoria.setNombre("proteina");
		this.categoria.setCategoriaPadre(this.categoriaPadre);
		this.categoriaRepository.save(this.categoria);
		
		this.subCategoria = new SubCategoria();
		this.subCategoria.setKkey("concentrado");
		this.subCategoria.setNombre("concentrado");
		this.subCategoria.setCategoria(this.categoria);
		this.subCategoriaRepository.save(this.subCategoria);
	}
	private void loadProducts() {
		producto1 = new Producto();
		producto1.setNombre("Real Whey");
		producto1.setCategoriaPadre(this.categoriaPadre);
		producto1.setCategoria(this.categoria);
		producto1.setSubCategoria(this.subCategoria);
		
		Producto producto2 = new Producto();
		producto2.setNombre("Isostar");
		producto2.setCategoriaPadre(this.categoriaPadre);
		producto2.setCategoria(this.categoria);
		producto2.setSubCategoria(this.subCategoria);
		
		this.productos = new ArrayList<>();
		this.productos.add(producto1);
		this.productos.add(producto2);
		this.productoRespository.saveAll(this.productos);
	}
	
}
