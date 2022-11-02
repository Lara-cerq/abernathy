package com.client.clinique.abernathy.historiquePatient.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.client.clinique.abernathy.historiquePatient.model.HistoriquePatient;
import com.client.clinique.abernathy.historiquePatient.repository.HistoriqueRepository;

@Controller
public class HistoriqueController {

	HistoriqueRepository historiqueRepository;

	public HistoriqueController(HistoriqueRepository historiqueRepository) {
		this.historiqueRepository = historiqueRepository;
	}

//	@GetMapping("/historiques")
//	public ResponseEntity<List<HistoriquePatient>> getAllHistoriques() {
//		List<HistoriquePatient> historiques = historiqueRepository.findAll();
//		return new ResponseEntity<>(historiques, HttpStatus.OK);
//
//	}

//	@PostMapping("/historiques")
//	public ResponseEntity<HistoriquePatient> createHistorique(@RequestBody HistoriquePatient historiquePatient) {
//		HistoriquePatient historiqueSaved = historiqueRepository.save(historiquePatient);
//		return new ResponseEntity<>(historiqueSaved, HttpStatus.CREATED);
//
//	}

//	@PutMapping("/historiques/{id}")
//	public ResponseEntity<HistoriquePatient> updateHistorique(@PathVariable("id") String id,
//			@RequestBody HistoriquePatient historiquePatient) {
//		Optional<HistoriquePatient> historiqueOptional = historiqueRepository.findById(id);
//		HistoriquePatient historique = historiqueOptional.get();
//		historique.setNotes(historiquePatient.getNotes());
//		return new ResponseEntity<>(historiqueRepository.save(historique), HttpStatus.OK);
//	}

	@DeleteMapping("/historiques/{id}")
	public ResponseEntity<HttpStatus> deleteHistorique(@PathVariable("id") String id) {
		historiqueRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	@GetMapping(value = "/historiques")
	public String showHistorique(Model model) {
		List<HistoriquePatient> historiques = historiqueRepository.findAll();
		model.addAttribute("historiques", historiques);
		return "historiques";
	}
	
	@GetMapping("/addNote/{id}")
	public String showAjoutForm(@PathVariable("id") String id,Model model) {
		HistoriquePatient historique = historiqueRepository.findById(id).get();
		model.addAttribute("historique", historique);
		return "addNote";
	}

	
	@PostMapping("/addNote/{id}")
	public String addNote(@PathVariable("id") String id, @Valid HistoriquePatient historique, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "addNote";
		}
		HistoriquePatient historiqueOld = historiqueRepository.findById(id).get();
//		historique.setId(id);
        List<String> notes= new ArrayList<>();
        String newNote = historiqueOld.getNotes() + historique.getNote();
        notes.add(newNote);
        historique.setNotes(notes);
//        historique.setNote(historique.getNote());
		historiqueRepository.save(historique);
		return "redirect:/historiques";
	}
	
	@GetMapping("/updateHistorique/{id}")
	public String showUpdateForm(@PathVariable("id") String id, Model model) {
		HistoriquePatient historique = historiqueRepository.findById(id).get();
		model.addAttribute("historique", historique);
		return "updateHistorique";
	}

	@Transactional
	@PostMapping("/updateHistorique/{id}")
	public String updatePatient(@PathVariable("id") String id, @Valid HistoriquePatient historique, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "updateHistorique";
		}
		historique.setId(id);
		historiqueRepository.insert(historique);
		return "redirect:/historiques";
	}

//	@PostMapping("/addPatient")
//	public String addPatient(@Valid Patient patient, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			return "addPatient";
//		}
//		patientService.addOrUpdatePatient(patient);
//		return "redirect:/patients";
//	}
}
