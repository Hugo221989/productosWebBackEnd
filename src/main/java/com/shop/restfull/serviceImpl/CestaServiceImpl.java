package com.shop.restfull.serviceImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.restfull.dto.CestaDto;
import com.shop.restfull.model.Cesta;
import com.shop.restfull.model.producto.ProductoCesta;
import com.shop.restfull.repository.CestaRepository;
import com.shop.restfull.repository.ProductoCestaRepository;
import com.shop.restfull.service.ICestaService;

@Service
public class CestaServiceImpl implements ICestaService {
	
	@Autowired
	private CestaRepository cestaRepository;
	@Autowired
	private ProductoCestaRepository productoCestaRepository;

	@Override
	public Cesta obtenerCesta( String username) {
		return this.cestaRepository.findCestaByUsername(username).orElse(null);
	}
	
	@Override
	public CestaDto cestaEntityToDto(Cesta cesta) {
		CestaDto cestaReturn = new CestaDto(cesta.getId(), cesta.getCantidadProductos(), cesta.getImporteTotal(), cesta.getImporteSubTotal(), cesta.getEnvio(), cesta.getProductosCesta());
		return cestaReturn;
	}

	@Override
	public Cesta addProducto(Cesta cesta) {
		return this.cestaRepository.save(cesta);
	}

	@Override
	public void eliminarCestaUsuario(int idUsuario) {
		this.cestaRepository.deleteUserCart(idUsuario);
	}

	@Override
	public Cesta saveCesta(Cesta cestaBd, Cesta cestaActualizada) {
		Set<ProductoCesta> productosCesta = this.createNewsProductosCesta(cestaActualizada);
		if(productosCesta != null) {
			this.saveNewProductosCesta(productosCesta);
			cestaActualizada.setProductosCesta(productosCesta);
		}
		this.cestaRepository.save(cestaActualizada);
		return cestaActualizada;
	}
	
	@Override
	public Set<ProductoCesta> createNewsProductosCesta(Cesta cestaActualizada){
		if(cestaActualizada == null)return null;
		if(cestaActualizada.getProductosCesta() == null)return null;
		Set<ProductoCesta> productosCestaList = new HashSet<>();
		for(ProductoCesta productoCesta: cestaActualizada.getProductosCesta()) {
			ProductoCesta productoCestaEntity = new ProductoCesta(); 
			productoCestaEntity.setCantidad(productoCesta.getCantidad());
			productoCestaEntity.setCesta(cestaActualizada);
			productoCestaEntity.setProducto(productoCesta.getProducto());
			productoCestaEntity.setSaborSeleccionado(productoCesta.getSaborSeleccionado());
			productosCestaList.add(productoCestaEntity);
		}
		return productosCestaList;
	}
	
	private void saveNewProductosCesta(Set<ProductoCesta> productosCesta) {
		this.productoCestaRepository.saveAll(productosCesta);
	}


	@Override
	public void removeCesta(Cesta cesta) {
		this.cestaRepository.delete(cesta);
	}

	@Override
	public void removeProductoCesta(Set<ProductoCesta> productoCestaSet) {
		for(ProductoCesta productoCesta: productoCestaSet) {
			productoCesta.setCesta(null);
			this.productoCestaRepository.deleteById(productoCesta.getId());
		}
	}

}
