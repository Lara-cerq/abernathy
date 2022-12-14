package com.client.clinique.abernathy.historique.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.client.clinique.abernathy.historique.model.Historique;
import com.client.clinique.abernathy.historique.model.HistoriqueDto;
import com.client.clinique.abernathy.historique.service.HistoriqueService;

@RestController
public class HistoriqueController {

	HistoriqueService historiqueService;

	public HistoriqueController(HistoriqueService historiqueService) {
		this.historiqueService = historiqueService;
	}

	@GetMapping(value = "/historiqueById/{id}")
	public ResponseEntity<Historique> getHistoriqueById(@PathVariable("id") String id) {
		Historique historique = historiqueService.findHistoriqueById(id);
		return new ResponseEntity<>(historique, HttpStatus.OK);
	}

	@PostMapping("/addNote/{idPatient}")
	public ResponseEntity<Historique> addNote(@PathVariable("idPatient") Integer idPatient,
			@Valid @RequestBody Historique historique) {
		historique.setIdPatient(idPatient);
		Historique newHistorique = historiqueService.saveOrUpdateHistorique(idPatient, historique);
		return new ResponseEntity<>(newHistorique, HttpStatus.CREATED);
	}

	@PutMapping("/updateNote/{id}")
	public ResponseEntity<Historique> updateHistorique(@PathVariable("id") String id,
			@Valid @RequestBody Historique historique) {
		historique.setId(id);
		Historique historiqueUpdated = historiqueService.saveOrUpdateHistorique(historique.getIdPatient(), historique);
		return new ResponseEntity<>(historiqueUpdated, HttpStatus.ACCEPTED);
	}

	@GetMapping("/historique/{idPatient}")
	public ResponseEntity<List<HistoriqueDto>> getHistoriqueByIdPatient(@PathVariable("idPatient") Integer idPatient,
			Historique historique) {
		historique.setIdPatient(idPatient);
		List<HistoriqueDto> historiqueList = historiqueService.getHistoriquesByIdPatient(idPatient);
		return new ResponseEntity<>(historiqueList, HttpStatus.OK);
	}

}
