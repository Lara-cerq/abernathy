package com.client.clinique.rapportDiabete.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.client.clinique.rapportDiabete.service.RapportService;

@RestController
public class RapportController {

	@Autowired
	RapportService rapportService;

	public RapportController(RapportService rapportService) {
		super();
		this.rapportService = rapportService;
	}

	@GetMapping("/rapport/{idPatient}")
	public ResponseEntity<String> generationRapportDiabete(@PathVariable("idPatient") Integer idPatient) {
		String resultat = rapportService.generationRapportDiabete(idPatient);
		return new ResponseEntity<>(resultat, HttpStatus.OK);
	}
}
