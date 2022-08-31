package com.ladyrouge.LadyRouge.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ladyrouge.LadyRouge.models.Product;
//import com.ladyrouge.LadyRouge.services.apiServices.ProductService;
//import com.ladyrouge.LadyRouge.services.apiServices.DTO.ProductsJsonDTO;
//import com.ladyrouge.LadyRouge.services.apiServices.DTO.InsertarProductsResponse;
import com.ladyrouge.LadyRouge.services.apiServices.ProductService;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.InsertarProductsResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // @GetMapping("/tallas")
	// public ResponseEntity<Iterable<Product>> tallas() {
	// 	return new ResponseEntity<Iterable<Product>>(tallaService.listaProducts(), HttpStatus.OK);
	// }

	// @GetMapping("/tallas/{tallaNombre}")
	// public ResponseEntity<Iterable<Product>> colorNombre(
	// 	@PathVariable String tallaNombre
	// ) {
	// 	return new ResponseEntity<Iterable<Product>>(tallaService.findByNombre(tallaNombre), HttpStatus.OK);
	// }

	@PostMapping("/insertarProduct")
	public ResponseEntity<InsertarProductsResponse> insertarProduct(
		@RequestBody Product product
	) {
		return new ResponseEntity<InsertarProductsResponse>(productService.insertarProduct(product), HttpStatus.OK);
	}

	// @DeleteMapping("/eliminarProducts")
	// public ResponseEntity<InsertarProductsResponse> eliminarProducts() {
	// 	return new ResponseEntity<InsertarProductsResponse>(tallaService.eliminarProducts(), HttpStatus.OK);
	// }
}
