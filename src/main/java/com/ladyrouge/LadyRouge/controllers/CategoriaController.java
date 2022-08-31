package com.ladyrouge.LadyRouge.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ladyrouge.LadyRouge.models.Categoria;
import com.ladyrouge.LadyRouge.services.apiServices.CategoriaService;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.CategoriasJsonDTO;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.InsertarCategoriasResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
@CrossOrigin
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping("/categorias")
	public ResponseEntity<Iterable<Categoria>> categorias() {
		return new ResponseEntity<Iterable<Categoria>>(categoriaService.listaCategorias(), HttpStatus.OK);
	}

	@GetMapping("/categorias/{categoriaNombre}")
	public ResponseEntity<Iterable<Categoria>> colorNombre(
		@PathVariable String categoriaNombre
	) {
		return new ResponseEntity<Iterable<Categoria>>(categoriaService.findByNombre(categoriaNombre), HttpStatus.OK);
	}

	@PostMapping("/insertarCategorias")
	public ResponseEntity<InsertarCategoriasResponse> insertarcategorias(
		@RequestBody Iterable<CategoriasJsonDTO> categoriasJsonDTO
	) {
		return new ResponseEntity<InsertarCategoriasResponse>(categoriaService.insertarCategorias(categoriasJsonDTO), HttpStatus.OK);
	}

	@DeleteMapping("/eliminarCategorias")
	public ResponseEntity<InsertarCategoriasResponse> eliminarCategorias() {
		return new ResponseEntity<InsertarCategoriasResponse>(categoriaService.eliminarCategorias(), HttpStatus.OK);
	}
}
