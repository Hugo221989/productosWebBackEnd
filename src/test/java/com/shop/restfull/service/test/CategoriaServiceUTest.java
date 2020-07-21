package com.shop.restfull.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.experimental.categories.Category;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shop.restfull.categoriaTest.TestUnitario;
import com.shop.restfull.dto.CategoriaDto;
import com.shop.restfull.dto.CategoriaPadreDto;
import com.shop.restfull.dto.SubCategoriaDto;
import com.shop.restfull.model.producto.Categoria;
import com.shop.restfull.model.producto.CategoriaPadre;
import com.shop.restfull.model.producto.SubCategoria;
import com.shop.restfull.repository.CategoriaPadreRepository;
import com.shop.restfull.service.ICategoriaService;

@Category(TestUnitario.class)
@SpringBootTest
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class CategoriaServiceUTest {

	@Mock
	private CategoriaPadreRepository categoriaPadreRepository;

	@Autowired
	private ICategoriaService categoriaService;
	
	@Mock
	CategoriaPadre categoriaPadre;
	
	@Mock
	Categoria categoria;
	
	List<Categoria> categorias;
	List<SubCategoria> subCategorias;
	SubCategoria subCategoria;
	
	private static final String CAT_PADRE_KEY = "nutricion";
	private static final String CAT_PADRE_MODULO = "products";
	private static final String CAT_PADRE_NOMBRE = "nutricion";
	private static final String CAT_PADRE_NOMBRE_ENG = "nutrition";
	
	@Before
    public void setup() {
		categorias = new ArrayList<>();
		this.createSubcategoriaList();
		this.createCategoria();
		categorias.add(this.createCategoria());
	}
	
	@Test
	public void givenServiceShouldReturnAnInstance() {
		assertThat(this.categoriaService).isNotNull();
	}
	
	@Test
	public void givenCategoriaPadreShouldReturnCategoriaPadreDtoIsNotNull() {
		CategoriaPadre categoriaPadreEntity = this.createCategoriaPadre();	
		CategoriaPadreDto categoriaPadreDtoMapped = categoriaService.fillCategoriaPadreDto(categoriaPadreEntity);
		assertThat(categoriaPadreDtoMapped).isNotNull();	
	}
	@Test
	public void givenCategoriaPadreShouldReturnCategoriaPadreDtoIsNull() {
		CategoriaPadreDto categoriaPadreDtoMapped = categoriaService.fillCategoriaPadreDto(null);
		assertThat(categoriaPadreDtoMapped).isNull();	
	}
	@Test
	public void givenCategoriaPadreShouldReturnCategoriaPadreDto() {
		CategoriaPadre categoriaPadreEntity = this.createCategoriaPadre();	
		CategoriaPadreDto categoriaPadreDtoMapped = categoriaService.fillCategoriaPadreDto(categoriaPadreEntity);
		assertThat(categoriaPadreEntity.getKkey()).isEqualTo(categoriaPadreDtoMapped.getKey());
	}
	@Test
	public void givenCategoriaPadreShouldReturnCategoriaPadreDtoFail() {
		CategoriaPadre categoriaPadreEntity = this.createCategoriaPadre();	
		CategoriaPadreDto categoriaPadreDtoMapped = categoriaService.fillCategoriaPadreDto(categoriaPadreEntity);
		categoriaPadreEntity.setKkey("modificada");
		assertThat(categoriaPadreEntity.getKkey()).isNotEqualTo(categoriaPadreDtoMapped.getKey());
	}
	
	@Test
	public void givenCategoriaPadreShouldReturnCategoriaPadreDtoCategoriaArrayLength() {
		CategoriaPadre categoriaPadreEntity = this.createCategoriaPadre();	
		CategoriaPadreDto categoriaPadreDtoMapped = categoriaService.fillCategoriaPadreDto(categoriaPadreEntity);
		assertThat(categoriaPadreDtoMapped.getCategorias().size()).isEqualTo(categoriaPadreEntity.getCategoria().size());
	}
	
	
	@Test
	public void givenCategoriaShouldReturnCategoriaDtoIsNotNull() {
		CategoriaDto categoriaDto = categoriaService.fillCategoriaDto(categoria);
		assertThat(categoriaDto).isNotNull();
	}
	@Test
	public void givenCategoriaShouldReturnCategoriaDtoIsNull() {
		CategoriaDto categoriaDto = categoriaService.fillCategoriaDto(null);
		assertThat(categoriaDto).isNull();
	}
	@Test
	public void givenCategoriaShouldReturnCategoriaDto() {
		CategoriaDto categoriaDto = categoriaService.fillCategoriaDto(categoria);
		assertEquals(categoriaDto.getNombre(), categoria.getNombre());
	}
	@Test
	public void givenCategoriaShouldReturnCategoriaDtoFail() {
		CategoriaDto categoriaDto = categoriaService.fillCategoriaDto(categoria);
		categoriaDto.setNombre("Nuevo nombre");
		assertNotEquals(categoriaDto.getNombre(), categoria.getNombre());
	}
	@Test
	public void givenCategoriaShouldReturnCategoriaDtoSubCategoriasLength() {
		CategoriaDto categoriaDto = categoriaService.fillCategoriaDto(categoria);
		assertThat(categoria.getSubCategoria().size()).isEqualTo(categoriaDto.getSubcategorias().size());
	}
	
	
	@Test
	public void givenSubCategoriaShouldReturnSubCategoriaDtoIsNotNull() {
		SubCategoriaDto subCategoriaDto = categoriaService.fillSubCategoriaDto(subCategoria);
		assertEquals(subCategoriaDto.getNombre(), subCategoria.getNombre());
	}
	@Test
	public void givenSubCategoriaShouldReturnSubCategoriaDtoIsNull() {
		SubCategoriaDto subCategoriaDto = categoriaService.fillSubCategoriaDto(null);
		assertThat(subCategoriaDto).isNull();
	}
	@Test
	public void givenSubCategoriaShouldReturnSubCategoriaDto() {
		SubCategoriaDto subCategoriaDto = categoriaService.fillSubCategoriaDto(subCategoria);
		assertEquals(subCategoriaDto.getNombre(), subCategoria.getNombre());
	}
	@Test
	public void givenSubCategoriaShouldReturnSubCategoriaDtoFail() {
		SubCategoriaDto subCategoriaDto = categoriaService.fillSubCategoriaDto(subCategoria);
		subCategoriaDto.setNombre("Cambio de nombre");
		assertNotEquals(subCategoriaDto.getNombre(), subCategoria.getNombre());
	}
	
	CategoriaPadre createCategoriaPadre() {
		CategoriaPadre categoriaPadreEntity = new CategoriaPadre();
		categoriaPadreEntity.setId(1);
		categoriaPadreEntity.setKkey(CAT_PADRE_KEY);
		categoriaPadreEntity.setModulo(CAT_PADRE_MODULO);
		categoriaPadreEntity.setNombre(CAT_PADRE_NOMBRE);
		categoriaPadreEntity.setNombreEng(CAT_PADRE_NOMBRE_ENG);
		categoriaPadreEntity.setCategoria(categorias);
		return categoriaPadreEntity;
	}
	
	Categoria createCategoria() {
		Categoria categoria = new Categoria();
		categoria.setId(1);
		categoria.setKkey("proteinas");
		categoria.setNombre("aislado");
		categoria.setNombreEng("isolated");
		categoria.setCategoriaPadre(categoriaPadre);
		categoria.setSubCategoria(this.createSubcategoriaList());
		return categoria;
	}
	
	List<SubCategoria> createSubcategoriaList(){
		subCategorias = new ArrayList<>();
		subCategoria = new SubCategoria();
		subCategoria.setId(1);
		subCategoria.setCategoria(categoria);
		subCategoria.setKkey("suero");
		subCategoria.setNombre("suero");
		subCategoria.setNombreEng("whey");
		subCategorias.add(subCategoria);
		return subCategorias;
	}
	
//	@Test
//	public void obtenerProductosByCategoriaPadreValido() {
//		CategoriaPadre categoriaPadre = new CategoriaPadre();
//		categoriaPadre.setId(1);
//		categoriaPadre.setKey("nutricion");
//		categoriaPadre.setProductos(productos);
//		categoriaPadre.setCategoria(categorias);
//		Optional<CategoriaPadre> categoriaPadreOptional = Optional.of(categoriaPadre);
//		String categoriaPadreKey = "nutricion";
//		when(categoriaPadreRepository.findCategoriaPadreByKey(categoriaPadreKey)).thenReturn(categoriaPadreOptional);
//		assertEquals(categoriaPadreRepository.findCategoriaPadreByKey(categoriaPadreKey), categoriaPadreOptional);
//	}
//	
//	@Test
//	public void obtenerProductosByCategoriaPadreFallido() {
//		CategoriaPadre categoriaPadre = new CategoriaPadre();
//		categoriaPadre.setId(1);
//		categoriaPadre.setKey("nutricion");
//		categoriaPadre.setProductos(productos);
//		categoriaPadre.setCategoria(categorias);
//		Optional<CategoriaPadre> categoriaPadreOptional = Optional.of(categoriaPadre);
//		String categoriaPadreKey = "alimentacion";
//		CategoriaPadre categoriaPadreDistinct = new CategoriaPadre();
//		categoriaPadreDistinct.setId(1);
//		categoriaPadreDistinct.setKey("nutricion");
//		categoriaPadreDistinct.setProductos(productos);
//		categoriaPadreDistinct.setCategoria(categorias);
//		Optional<CategoriaPadre> categoriaPadreDistinctOptional = Optional.of(categoriaPadreDistinct);
//		
//		when(categoriaPadreRepository.findCategoriaPadreByKey(categoriaPadreKey)).thenReturn(categoriaPadreDistinctOptional);
//		assertNotEquals(categoriaPadreRepository.findCategoriaPadreByKey(categoriaPadreKey), categoriaPadreOptional);
//	}
}
