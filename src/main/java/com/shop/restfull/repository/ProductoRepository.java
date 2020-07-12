package com.shop.restfull.repository;

import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shop.restfull.dto.ProductoDto;
import com.shop.restfull.model.producto.Producto;

@Repository
@Transactional
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	
	 final String CONSULTA_CATEGORICA = "SELECT new com.shop.restfull.dto.ProductoDto(p.id, padre.key, cat.key, subcat.key, p.nombre, p.nombreEng, p.precio, p.tamano, p.puntuacion, p.disponible, " + 
	 		"p.descuento, p.precioFinal, des.titulo, f) " +
	 		"FROM Producto p " +
	 		"LEFT JOIN CategoriaPadre padre ON p.categoriaPadre.id = padre.id " +
	 		"LEFT JOIN Categoria cat ON p.categoria.id = cat.id " +
	 		"LEFT JOIN SubCategoria subcat ON p.subCategoria.id = subcat.id " +
	 		"LEFT JOIN Foto f ON f.productos.id = p.id " +
	 		"LEFT JOIN Descripcion des ON des.producto.id = p.id ";

	 final String WHERE = "WHERE f.principal = TRUE and ";
	 final String CATEGORIA_PADRE = "p.categoriaPadre.key = ?1";
	 final String CATEGORIA = "p.categoria.key = ?1";
	 final String SUB_CATEGORIA = "p.subCategoria.key = ?1";
	 final String BUSCADOR = "p.nombre LIKE CONCAT('%',?1,'%')";
	 final String RELATED = "padre.key = ?1";
	 final String BY_ID = "p.id = ?1";
	 	
	@Cacheable("all_products")
	@Query(CONSULTA_CATEGORICA)
	public List<ProductoDto> findAllProducts();
	 
	@Query(CONSULTA_CATEGORICA+WHERE+BY_ID)
	public ProductoDto findProductoDtoById(int idProducto);

	@Cacheable("products_related_products")
	@Query(CONSULTA_CATEGORICA+WHERE+RELATED)
	public List<ProductoDto> findRelatedProducts(Pageable pageable, String categoriaPadreKey);
	
	@Query(CONSULTA_CATEGORICA+WHERE+BUSCADOR)
	public List<ProductoDto> findProductsByBuscador(String buscador);

	@Cacheable("products_by_categoria_padre")
	@Query(CONSULTA_CATEGORICA+WHERE+CATEGORIA_PADRE)
	public List<ProductoDto> findProductsByCategoriaPadre(String categoria);

	@Cacheable("products_by_categoria")
	@Query(CONSULTA_CATEGORICA+WHERE+CATEGORIA)
	public List<ProductoDto> findProductsByCategoria(String categoria);

	@Cacheable("products_by_subcategoria")
	@Query(CONSULTA_CATEGORICA+WHERE+SUB_CATEGORIA)
	public List<ProductoDto> findProductsBySubCategoria(String subCategoria);
	
}
