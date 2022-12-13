package com.client.clinique.abernathy.controller;

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

import com.client.clinique.abernathy.exceptions.ListPatientsNonTrouvableException;
import com.client.clinique.abernathy.model.Patient;
import com.client.clinique.abernathy.service.PatientService;

@RestController
public class PatientController {

	PatientService patientService;

	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	@GetMapping(value = "/patients")
	public ResponseEntity<List<Patient>> listePatients() {
		List<Patient> patients = patientService.getAllPatients();
		if (patients.equals(null))
			throw new ListPatientsNonTrouvableException(
					"La liste de patients ne peut pas etre affichée car elle ne contient aucun patient.");
		return new ResponseEntity<>(patients, HttpStatus.OK);
	}

	@PostMapping("/addPatient")
	public ResponseEntity<Patient> addPatient(@Valid @RequestBody Patient patient) {
		Patient newPatient = patientService.addOrUpdatePatient(patient);
		return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
	}

	@PutMapping("/updatePatient/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable("id") Integer id, @Valid @RequestBody Patient patient) {
		patient.setIdPatient(id);
		Patient patientUpdated = patientService.addOrUpdatePatient(patient);
		return new ResponseEntity<>(patientUpdated, HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/patient/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable("id") Integer idPatient) {
		Patient patient = patientService.getId(idPatient).get();
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}

}
