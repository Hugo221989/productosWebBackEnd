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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.shop.restfull.config.H2TestProfileJPAConfig;
import com.shop.restfull.model.Pedido;
import com.shop.restfull.model.Usuario;
import com.shop.restfull.repository.PedidoRepository;
import com.shop.restfull.repository.UsuarioRepository;

@ContextConfiguration(classes = { H2TestProfileJPAConfig.class }, 
loader = AnnotationConfigContextLoader.class)
//@Transactional
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PedidoRepositoryTest {

	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private Usuario usuario;
	
	@Before
	public void loadPedido() {
		usuario = new Usuario();
		usuario.setNombre("Paco");
		usuario.setApellido("Porras");
		usuario.setUsuario("Paquito");
		usuario.setGenero(null);
		this.usuarioRepository.save(usuario);
		
		Pedido pedido = new Pedido();
		pedido.setDestinatario("destinatario");
		pedido.setEnviado(true);
		pedido.setNumPedido("XSDA4545");
		pedido.setUsuario(usuario);
		this.pedidoRepository.save(pedido);
	}
	
	@Test
	public void givenUserIdShouldReturnItsOrders() {
		Optional<List<Pedido>> pedidos = this.pedidoRepository.findOrdersByUserId(usuario.getId());
		assertThat(pedidos.get().size()).isEqualTo(1);
	}
	
	@Test
	public void givenUserIdShouldReturnTwoOrders() {
		Pedido pedido = new Pedido();
		pedido.setDestinatario("destinatario 2");
		pedido.setEnviado(true);
		pedido.setNumPedido("XSDA45452");
		pedido.setUsuario(usuario);
		this.pedidoRepository.save(pedido);
		Optional<List<Pedido>> pedidos = this.pedidoRepository.findOrdersByUserId(usuario.getId());
		assertThat(pedidos.get().size()).isEqualTo(2);
	}
}
