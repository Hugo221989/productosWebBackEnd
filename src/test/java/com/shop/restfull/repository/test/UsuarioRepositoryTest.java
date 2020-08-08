package com.shop.restfull.repository.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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
import org.springframework.transaction.annotation.Transactional;

import com.shop.restfull.config.H2TestProfileJPAConfig;
import com.shop.restfull.dto.CustomerBasicDto;
import com.shop.restfull.model.Usuario;
import com.shop.restfull.repository.UsuarioRepository;

@ContextConfiguration(classes = { H2TestProfileJPAConfig.class }, 
loader = AnnotationConfigContextLoader.class)
@Transactional
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private Usuario usuario;
	private Usuario usuario2;
	
	@Before
	public void loadUserData() {
		usuario = new Usuario();
		usuario.setNombre("Juan");
		usuario.setApellido("Lomonaco");
		usuario.setEmail("archi@gmail");
		usuario2 = new Usuario();
		usuario2.setNombre("Maria");
		usuario2.setApellido("Nabo");
		usuario2.setEmail("rabo@gmail");
		
		this.usuarioRepository.save(usuario);
		this.usuarioRepository.save(usuario2);
	}
	
	@Test
	public void givenUserListShouldReturnCorrectSize() {
		List<CustomerBasicDto> customers = this.usuarioRepository.findCustomersBasic();
		assertThat(customers.size()).isEqualTo(2);
	}
	
	@Test
	public void givenUserShouldReturnEmail() {
		Usuario user = this.usuarioRepository.findByEmail(usuario2.getEmail()).orElse(null);
		assertThat(user.getEmail()).isEqualTo(usuario2.getEmail());
	}
	
	@Test
	public void whenEditUserShouldReturnDataUpdated() {
		String nombreModificado = "Juan Modificado";
		Usuario user = this.usuarioRepository.findById(usuario.getId()).orElse(null);
		if(user != null) {
			user.setNombre(nombreModificado);
			this.usuarioRepository.save(user);
		}
		user = this.usuarioRepository.findById(usuario.getId()).orElse(null);
		assertThat(user.getNombre()).isEqualTo(nombreModificado);
	}
}
