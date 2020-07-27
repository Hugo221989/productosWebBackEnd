package com.shop.restfull.repository.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.test.context.jdbc.SqlMergeMode.MergeMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.shop.restfull.config.H2TestProfileJPAConfig;
import com.shop.restfull.model.producto.Categoria;
import com.shop.restfull.model.producto.CategoriaPadre;
import com.shop.restfull.repository.CategoriaPadreRepository;
import com.shop.restfull.repository.CategoriaRepository;

@ContextConfiguration(classes = { H2TestProfileJPAConfig.class }, 
loader = AnnotationConfigContextLoader.class)
@Transactional
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Sql(scripts="classpath:data.sql", executionPhase=Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@SqlMergeMode(MergeMode.MERGE) 
public class CategoriaRepositoryTest {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CategoriaPadreRepository categoriaPadreRepository;
	
	private List<Categoria> categorias;
	private Categoria categoria;
	private CategoriaPadre categoriaPadre;
	
	@Before
	public void loadCategoria() {
		this.loadCategorias();
		this.categorias = this.categoriaRepository.findAll();
	}
	
	@Test
	public void shouldReturnCategoriaNotNull() {
		assertThat(this.categorias).isNotNull();
	}
	
	@Test
	public void givenCategoriaPadreIdShouldReturnCategoria() {
		Optional<List<Categoria>> categoriaOptional = this.categoriaRepository.findCategoriasByCategoriaPadre(this.categoriaPadre.getId());
		assertThat(categoriaOptional.get().size()).isEqualTo(1);
	}
	
	private void loadCategorias() {
		categoria = new Categoria();
		categoria.setKkey("proteina");
		categoria.setNombre("proteina");
		categoria.setNombreEng("protein");
		
		categoriaPadre = new CategoriaPadre();
		categoriaPadre.setKkey("products");
		categoriaPadre.setNombre("productos");
		this.categoriaPadreRepository.save(categoriaPadre);
		
		categoria.setCategoriaPadre(categoriaPadre);
		
		this.categoriaRepository.save(this.categoria);
	}
}
