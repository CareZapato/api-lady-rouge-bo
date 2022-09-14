package com.ladyrouge.LadyRouge.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ladyrouge.LadyRouge.models.Factura;
import com.ladyrouge.LadyRouge.services.apiServices.FacturaService;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.Factura.FacturaJsonDTO;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.Factura.InsertarFacturaResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/factura")
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
public class FacturaController {

    private final FacturaService facturaService;

    @GetMapping("/facturas")
	public ResponseEntity<Iterable<Factura>> facturas() {
		log.info("[FacturaService][facturas]");
		return new ResponseEntity<Iterable<Factura>>(facturaService.listaFacturas(), HttpStatus.OK);
	}

	@PostMapping("/insertarFactura")
	public ResponseEntity<InsertarFacturaResponse> insertarFactura(
		@RequestBody FacturaJsonDTO facturaJsonDTO
	) {
		log.info("[FacturaController][insertarFactura]");
		return new ResponseEntity<InsertarFacturaResponse>(facturaService.insertarFactura(facturaJsonDTO), HttpStatus.OK);
	}
}
