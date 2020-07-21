package com.shop.restfull.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shop.restfull.dto.CatProductoDto;
import com.shop.restfull.dto.ProductoDto;
import com.shop.restfull.model.producto.Categoria;
import com.shop.restfull.model.producto.Producto;
import com.shop.restfull.repository.CategoriaRepository;
import com.shop.restfull.repository.ProductoRepository;
import com.shop.restfull.service.IProductoService;
@Service
public class ProductoServiceImpl implements IProductoService {
	
	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public List<ProductoDto> obtenerListaProductos() {
		// TODO Auto-generated method stub
		return this.productoRepository.findAllProducts();
	}

	@Override
	public Producto obtenerProductoById(int idProducto) {
		// TODO Auto-generated method stub
		return this.productoRepository.findById(idProducto).orElse(null);
	}

	@Override
	public List<ProductoDto> obtenerListaProductosRelacionadosLimit5(Pageable pageable, String categoriaPadreKey) {
		// TODO Auto-generated method stub
		return this.productoRepository.findRelatedProducts(pageable, categoriaPadreKey);
	}

	@Override
	public List<ProductoDto> obtenerListaProductosBuscador(String buscador) {
		// TODO Auto-generated method stub
		return this.productoRepository.findProductsByBuscador(buscador);
	}

	@Override
	public List<ProductoDto> obtenerListaProductosBySubCategoria(String subCategoria) {
		// TODO Auto-generated method stub
		return this.productoRepository.findProductsBySubCategoria(subCategoria);
	}

	@Override
	public List<ProductoDto> obtenerListaProductosByCategoria(String categoria) {
		// TODO Auto-generated method stub
		return this.productoRepository.findProductsByCategoria(categoria);
	}

	@Override
	public List<ProductoDto> obtenerListaProductosByCategoriaPadre(String categoriaPadre) {
		// TODO Auto-generated method stub
		List<ProductoDto> productos = this.productoRepository.findProductsByCategoriaPadre(categoriaPadre);
		return productos;
	}

	@Override
	public CatProductoDto obtenerCatSubCatProducto(int idProducto) {
		// TODO Auto-generated method stub
		Producto prod = new Producto();
		prod = this.productoRepository.findById(idProducto).orElse(null);
		return this.fillCatProductoDtoFromProducto(prod);
	}
	
	@Override
	public CatProductoDto fillCatProductoDtoFromProducto(Producto prod) {
		if(prod == null)return null;
		CatProductoDto catDto = new CatProductoDto();
		Categoria categoria;
		if(prod.getCategoria() != null) {
			categoria = prod.getCategoria();
			catDto.setCategoriaKey(categoria.getKkey());
			catDto.setCategoriaNombre(categoria.getNombre());
			catDto = this.fillCatProductoDtoCategoriaPadreFromCategoria(catDto, categoria);
		} 
		if(prod.getSubCategoria() != null) {
			catDto.setSubCategoriaKey(prod.getSubCategoria().getKkey());
			catDto.setSubCategoriaNombre(prod.getSubCategoria().getNombre());
		}		
		return catDto;
	}
	
	public CatProductoDto fillCatProductoDtoCategoriaPadreFromCategoria(CatProductoDto catDto, Categoria categoria) {
		if(categoria.getCategoriaPadre() != null) {
			catDto.setCategoriaPadreKey(categoria.getCategoriaPadre().getKkey());
			catDto.setCategoriaPadreNombre(categoria.getCategoriaPadre().getNombre());
			catDto.setCategoriaPadreModulo(categoria.getCategoriaPadre().getModulo());
			catDto.setCategoriaPadreId(categoria.getCategoriaPadre().getId());
		}
		return catDto;
	}


}
