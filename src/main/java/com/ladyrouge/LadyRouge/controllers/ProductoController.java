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

import com.ladyrouge.LadyRouge.models.Producto;
import com.ladyrouge.LadyRouge.services.apiServices.ProductoService;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.Producto.InsertarProductoResponse;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.Producto.InsertarProductosResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/producto")
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping("/productos")
	public ResponseEntity<Iterable<Producto>> productos() {
		log.info("[ProductoController][productos]");
		return new ResponseEntity<Iterable<Producto>>(productoService.listaProductos(), HttpStatus.OK);
	}

	@GetMapping("/productos/{productoNombre}")
	public ResponseEntity<Iterable<Producto>> productoNombre(
		@PathVariable String productoNombre
	) {
		log.info("[ProductoController][productoNombre]");
		return new ResponseEntity<Iterable<Producto>>(productoService.findByNombre(productoNombre), HttpStatus.OK);
	}

	@PostMapping("/insertarProducto")
	public ResponseEntity<InsertarProductoResponse> insertarProducto(
		@RequestBody Producto producto
	) {
		log.info("[ProductoController][insertarProducto]");
		return new ResponseEntity<InsertarProductoResponse>(productoService.insertarProducto(producto), HttpStatus.OK);
	}

	@PostMapping("/insertarProductos")
	public ResponseEntity<InsertarProductosResponse> insertarProductos(
		@RequestBody Producto[] productos
	) {
		log.info("[ProductoController][insertarProductos]");
		return new ResponseEntity<InsertarProductosResponse>(productoService.insertarProductos(productos), HttpStatus.OK);
	}

	@DeleteMapping("/eliminarProductos")
	public ResponseEntity<InsertarProductoResponse> eliminarProductos() {
		log.info("[ProductoController][eliminarProductos]");
		return new ResponseEntity<InsertarProductoResponse>(productoService.eliminarProductos(), HttpStatus.OK);
	}
}
