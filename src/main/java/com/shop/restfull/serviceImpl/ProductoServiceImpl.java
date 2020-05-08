package com.shop.restfull.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shop.restfull.model.producto.Producto;
import com.shop.restfull.repository.ProductoRepository;
import com.shop.restfull.service.IProductoService;
@Service
public class ProductoServiceImpl implements IProductoService {
	
	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public List<Producto> obtenerListaProductos() {
		// TODO Auto-generated method stub
		return this.productoRepository.findAll();
	}

	@Override
	public Optional<Producto> obtenerProductoById(int idProducto) {
		// TODO Auto-generated method stub
		return this.productoRepository.findById(idProducto);
	}

	@Override
	public List<Producto> obtenerListaProductosLimit5(Pageable pageable) {
		// TODO Auto-generated method stub
		return this.productoRepository.findRelatedProducts(pageable).orElse(null);
	}


}
