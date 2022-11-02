package com.client.clinique.abernathy.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.client.clinique.abernathy.exceptions.ListPatientsNonTrouvableException;
import com.client.clinique.abernathy.model.Patient;
import com.client.clinique.abernathy.service.PatientService;

@Controller
public class PatientController {

	PatientService patientService;

	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	@GetMapping(value = "/patients")
	public String listePatients(Model model) {
		List<Patient> patients = patientService.getAllPatients();
		if (patients.equals(null))
			throw new ListPatientsNonTrouvableException(
					"La liste de patients ne peut pas etre affichée car elle ne contient aucun patient.");
		model.addAttribute("patients", patients);
		return "patients";
	}

	@GetMapping("/addPatient")
	public String showAjoutForm(Patient patient) {
		return "addPatient";
	}

	@Transactional
	@PostMapping("/addPatient")
	public String addPatient(@Valid Patient patient, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "addPatient";
		}
		patientService.addOrUpdatePatient(patient);
		return "redirect:/patients";
	}

	@GetMapping("/updatePatient/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Patient patient = patientService.getId(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
		model.addAttribute("patient", patient);
		return "updatePatient";
	}

	@Transactional
	@PostMapping("/updatePatient/{id}")
	public String updatePatient(@PathVariable("id") Integer id, @Valid Patient patient, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "updatePatient";
		}
		patient.setIdPatient(id);
		patientService.addOrUpdatePatient(patient);
		return "redirect:/patients";
	}

//	@DeleteMapping(value = "/patients/{id}")
//	public ResponseEntity<Void> supprimerPatient(@PathVariable int id) {
//		patientService.deletePatient(id);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}

}
