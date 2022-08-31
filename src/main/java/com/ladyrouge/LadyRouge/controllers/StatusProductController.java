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

import com.ladyrouge.LadyRouge.models.StatusProduct;
import com.ladyrouge.LadyRouge.services.apiServices.StatusProductService;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.StatusProductsJsonDTO;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.InsertarStatusProductsResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/statusProduct")
@RequiredArgsConstructor
@CrossOrigin
public class StatusProductController {

    private final StatusProductService statusProductService;

    @GetMapping("/statusProducts")
	public ResponseEntity<Iterable<StatusProduct>> statusProducts() {
		return new ResponseEntity<Iterable<StatusProduct>>(statusProductService.listaStatusProducts(), HttpStatus.OK);
	}

	@GetMapping("/statusProducts/{statusProductNombre}")
	public ResponseEntity<Iterable<StatusProduct>> colorNombre(
		@PathVariable String statusProductNombre
	) {
		return new ResponseEntity<Iterable<StatusProduct>>(statusProductService.findByNombre(statusProductNombre), HttpStatus.OK);
	}

	@PostMapping("/insertarStatusProducts")
	public ResponseEntity<InsertarStatusProductsResponse> insertarstatusProducts(
		@RequestBody Iterable<StatusProductsJsonDTO> statusProductsJsonDTO
	) {
		return new ResponseEntity<InsertarStatusProductsResponse>(statusProductService.insertarStatusProducts(statusProductsJsonDTO), HttpStatus.OK);
	}

	@DeleteMapping("/eliminarStatusProducts")
	public ResponseEntity<InsertarStatusProductsResponse> eliminarStatusProducts() {
		return new ResponseEntity<InsertarStatusProductsResponse>(statusProductService.eliminarStatusProducts(), HttpStatus.OK);
	}
}
