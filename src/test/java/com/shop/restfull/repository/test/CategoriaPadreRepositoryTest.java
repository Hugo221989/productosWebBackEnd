package com.shop.restfull.repository.test;

import static org.assertj.core.api.Assertions.assertThat;
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
import com.shop.restfull.model.producto.CategoriaPadre;
import com.shop.restfull.repository.CategoriaPadreRepository;


@ContextConfiguration(classes = { H2TestProfileJPAConfig.class }, 
loader = AnnotationConfigContextLoader.class)
//@Transactional
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Sql(scripts="classpath:data.sql", executionPhase=Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@SqlMergeMode(MergeMode.MERGE) 
public class CategoriaPadreRepositoryTest {
	
	@Autowired
	private CategoriaPadreRepository categoriaPadreRepository;
	
	private Optional<CategoriaPadre> categoriaPadre;
	
	@Before
	public void loadCategoriaPadreQuery() {
		this.categoriaPadre = this.categoriaPadreRepository.findCategoriaPadreByKey("nutricion");
	}
	
	@Test
	public void givenCategoriaPadreKeyShouldReturnCategoriaPadreEntity() {
		System.out.println("NOMBRE: "+this.categoriaPadre.get().getNombre());
		assertThat(this.categoriaPadreRepository).isNotNull();
	}
	@Test
	public void givenCategoriaPadreKeyShouldReturnCategoriaPadreEntityModulo() {
		assertThat(categoriaPadre.get().getModulo()).isEqualTo("products");
	}
	@Test
	public void givenCategoriaPadreKeyShouldReturnCategoriaPadreEntityModuloIncorrect() {
		assertThat(categoriaPadre.get().getModulo()).isNotEqualTo("feeding");
	}
	
	@Test
	public void CategoriaPadreShouldReturnThreeRecords() {
		assertThat(this.categoriaPadreRepository.findAll().size()).isEqualTo(3);
	}

}
