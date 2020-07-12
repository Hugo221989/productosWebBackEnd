package com.shop.restfull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shop.restfull.dto.CategoriaPadreDto;
import com.shop.restfull.service.ICategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/categoria")
@Api(value = "VALUE", description = "Gestión Productos")
public class CategoriaController {
	
	@Autowired
	private ICategoriaService categoriaService;

	@ApiOperation(value = "Obtener categoria padre", notes = "Este servicio web obtiene la raiz principal de las categorias.", response = CategoriaPadreDto.class, responseContainer = "CategoriaPadreDto")
	@GetMapping("/obtenerCategoriaPadreByKey")
	public ResponseEntity<CategoriaPadreDto> obtenerCategoriaPadreByKey(@ApiParam(value = "idCategoriaPadre", required = true) @RequestParam(required = true) String idCategoriaPadre){
		
		CategoriaPadreDto categoriaPadre = this.categoriaService.obtenerProductosByCategoriaPadre(idCategoriaPadre);
		
		return ResponseEntity.ok(categoriaPadre);
	}

}
