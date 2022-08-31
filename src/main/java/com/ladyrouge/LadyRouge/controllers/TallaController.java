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

import com.ladyrouge.LadyRouge.models.Talla;
import com.ladyrouge.LadyRouge.services.apiServices.TallaService;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.TallasJsonDTO;
import com.ladyrouge.LadyRouge.services.apiServices.DTO.InsertarTallasResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/talla")
@RequiredArgsConstructor
@CrossOrigin
public class TallaController {

    private final TallaService tallaService;

    @GetMapping("/tallas")
	public ResponseEntity<Iterable<Talla>> tallas() {
		return new ResponseEntity<Iterable<Talla>>(tallaService.listaTallas(), HttpStatus.OK);
	}

	@GetMapping("/tallas/{tallaNombre}")
	public ResponseEntity<Iterable<Talla>> colorNombre(
		@PathVariable String tallaNombre
	) {
		return new ResponseEntity<Iterable<Talla>>(tallaService.findByNombre(tallaNombre), HttpStatus.OK);
	}

	@PostMapping("/insertarTallas")
	public ResponseEntity<InsertarTallasResponse> insertartallas(
		@RequestBody Iterable<TallasJsonDTO> tallasJsonDTO
	) {
		return new ResponseEntity<InsertarTallasResponse>(tallaService.insertarTallas(tallasJsonDTO), HttpStatus.OK);
	}

	@DeleteMapping("/eliminarTallas")
	public ResponseEntity<InsertarTallasResponse> eliminarTallas() {
		return new ResponseEntity<InsertarTallasResponse>(tallaService.eliminarTallas(), HttpStatus.OK);
	}
}
