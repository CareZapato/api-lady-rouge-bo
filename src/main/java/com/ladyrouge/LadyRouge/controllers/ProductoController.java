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
//import com.ladyrouge.LadyRouge.services.apiServices.ProductService;
//import com.ladyrouge.LadyRouge.services.apiServices.DTO.ProductsJsonDTO;
//import com.ladyrouge.LadyRouge.services.apiServices.DTO.InsertarProductsResponse;
import com.ladyrouge.LadyRouge.services.apiServices.ProductoService;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.InsertarProductoResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/producto")
@RequiredArgsConstructor
@CrossOrigin
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping("/productos")
	public ResponseEntity<Iterable<Producto>> productos() {
		return new ResponseEntity<Iterable<Producto>>(productoService.listaProductos(), HttpStatus.OK);
	}

	@GetMapping("/productos/{productoNombre}")
	public ResponseEntity<Iterable<Producto>> productoNombre(
		@PathVariable String productoNombre
	) {
		return new ResponseEntity<Iterable<Producto>>(productoService.findByNombre(productoNombre), HttpStatus.OK);
	}

	@PostMapping("/insertarProducto")
	public ResponseEntity<InsertarProductoResponse> insertarProducto(
		@RequestBody Producto producto
	) {
		return new ResponseEntity<InsertarProductoResponse>(productoService.insertarProducto(producto), HttpStatus.OK);
	}

	@DeleteMapping("/eliminarProductos")
	public ResponseEntity<InsertarProductoResponse> eliminarProductos() {
		return new ResponseEntity<InsertarProductoResponse>(productoService.eliminarProductos(), HttpStatus.OK);
	}
}
