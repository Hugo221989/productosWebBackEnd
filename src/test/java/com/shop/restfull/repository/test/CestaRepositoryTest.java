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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import com.shop.restfull.config.H2TestProfileJPAConfig;
import com.shop.restfull.model.Cesta;
import com.shop.restfull.model.Usuario;
import com.shop.restfull.repository.CestaRepository;
import com.shop.restfull.repository.UsuarioRepository;

@ContextConfiguration(classes = { H2TestProfileJPAConfig.class }, 
loader = AnnotationConfigContextLoader.class)
//@Transactional
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CestaRepositoryTest {

	@Autowired
	private CestaRepository cestaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private Optional<Cesta> cesta;
	private Usuario usuario;
	
	@Before
	public void initCesta() {
		usuario = new Usuario();
		usuario.setNombre("Paco");
		usuario.setApellido("Porras");
		usuario.setUsuario("Paquito");
		usuario.setGenero(null);
		usuarioRepository.save(usuario);
		
		Cesta cestaNueva = new Cesta();
		cestaNueva.setId(1);
		cestaNueva.setEnvio(0.0);
		cestaNueva.setImporteSubTotal(20.0);
		cestaNueva.setImporteTotal(20.0);
		cestaNueva.setUsuario(usuario);
		this.cestaRepository.save(cestaNueva);
	}
	
	@Test
	public void givenUsuarioIdShouldReturnCesta() {
		cesta = this.cestaRepository.findCestaByUserId(usuario.getId());
		assertThat(cesta.get().getImporteTotal()).isEqualTo(20.0);
	}
	
	@Test
	public void givenUsuarioNameShouldReturnCesta() {
		cesta = this.cestaRepository.findCestaByUsername(usuario.getUsuario());
		assertThat(cesta.get().getImporteTotal()).isEqualTo(20.0);
	}
	
	@Test
	public void givenUsuarioIdShouldDeleteItsCesta() {
		this.cestaRepository.deleteUserCart(usuario.getId());
		assertThat(this.cestaRepository.findCestaByUserId(usuario.getId()).orElse(null)).isNull();
	}
}
