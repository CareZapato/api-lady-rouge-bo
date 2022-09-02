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

import com.ladyrouge.LadyRouge.models.StatusProducto;
import com.ladyrouge.LadyRouge.services.apiServices.StatusProductoService;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.StatusProductosJsonDTO;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.InsertarStatusProductosResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/statusProducto")
@RequiredArgsConstructor
@CrossOrigin
public class StatusProductController {

    private final StatusProductoService statusProductoService;

    @GetMapping("/statusProductos")
	public ResponseEntity<Iterable<StatusProducto>> statusProductos() {
		return new ResponseEntity<Iterable<StatusProducto>>(statusProductoService.listaStatusProductos(), HttpStatus.OK);
	}

	@GetMapping("/statusProductos/{statusProductoNombre}")
	public ResponseEntity<Iterable<StatusProducto>> colorNombre(
		@PathVariable String statusProductoNombre
	) {
		return new ResponseEntity<Iterable<StatusProducto>>(statusProductoService.findByNombre(statusProductoNombre), HttpStatus.OK);
	}

	@PostMapping("/insertarStatusProductos")
	public ResponseEntity<InsertarStatusProductosResponse> insertarstatusProductos(
		@RequestBody Iterable<StatusProductosJsonDTO> statusProductosJsonDTO
	) {
		return new ResponseEntity<InsertarStatusProductosResponse>(statusProductoService.insertarStatusProductos(statusProductosJsonDTO), HttpStatus.OK);
	}

	@DeleteMapping("/eliminarStatusProductos")
	public ResponseEntity<InsertarStatusProductosResponse> eliminarStatusProductos() {
		return new ResponseEntity<InsertarStatusProductosResponse>(statusProductoService.eliminarStatusProductos(), HttpStatus.OK);
	}
}
