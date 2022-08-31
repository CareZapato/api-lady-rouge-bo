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

import com.ladyrouge.LadyRouge.models.Color;
import com.ladyrouge.LadyRouge.services.apiServices.ColorService;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.ColoresJsonDTO;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.InsertarColoresResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/color")
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;

    @GetMapping("/colores")
	public ResponseEntity<Iterable<Color>> colores() {
		return new ResponseEntity<Iterable<Color>>(colorService.listaColores(), HttpStatus.OK);
	}

	@GetMapping("/colores/{colorNombre}")
	public ResponseEntity<Iterable<Color>> colorNombre(
		@PathVariable String colorNombre
	) {
		return new ResponseEntity<Iterable<Color>>(colorService.findByNombre(colorNombre), HttpStatus.OK);
	}

	@PostMapping("/insertarColores")
	public ResponseEntity<InsertarColoresResponse> insertarColores(
		@RequestBody Iterable<ColoresJsonDTO> coloresJsonDTO
	) {
		return new ResponseEntity<InsertarColoresResponse>(colorService.insertarColores(coloresJsonDTO), HttpStatus.OK);
	}

	@DeleteMapping("/eliminarColores")
	public ResponseEntity<InsertarColoresResponse> eliminarColores() {
		return new ResponseEntity<InsertarColoresResponse>(colorService.eliminarColores(), HttpStatus.OK);
	}
}
