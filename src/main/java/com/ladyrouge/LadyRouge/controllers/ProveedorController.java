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

import com.ladyrouge.LadyRouge.models.Proveedor;
import com.ladyrouge.LadyRouge.services.apiServices.ProveedorService;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.Proveedor.InsertarProveedoresResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/proveedor")
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
public class ProveedorController {

    private final ProveedorService proveedorService;

    @GetMapping("/proveedores")
	public ResponseEntity<Iterable<Proveedor>> proveedors() {
		log.info("[ProveedorService][proveedores]");
		return new ResponseEntity<Iterable<Proveedor>>(proveedorService.listaProveedores(), HttpStatus.OK);
	}

	@PostMapping("/insertarProveedores")
	public ResponseEntity<InsertarProveedoresResponse> insertarProveedores(
		@RequestBody Iterable<Proveedor> proveedores
	) {
		log.info("[ProveedorController][insertarProveedores]");
		return new ResponseEntity<InsertarProveedoresResponse>(proveedorService.insertarProveedores(proveedores), HttpStatus.OK);
	}

	@GetMapping("/proveedores/{proveedorNombre}")
	public ResponseEntity<Iterable<Proveedor>> proveedorNombre(
		@PathVariable String proveedorNombre
	) {
		log.info("[ProveedorController][proveedorNombre]");
		return new ResponseEntity<Iterable<Proveedor>>(proveedorService.findByNombre(proveedorNombre), HttpStatus.OK);
	}

	@DeleteMapping("/eliminarProveedores")
	public ResponseEntity<InsertarProveedoresResponse> eliminarProveedoress() {
		log.info("[ProveedorController][eliminarProveedores]");
		return new ResponseEntity<InsertarProveedoresResponse>(proveedorService.eliminarProveedores(), HttpStatus.OK);
	}
}
