package com.client.clinique.abernathy.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.client.clinique.abernathy.beans.PatientBean;
import com.client.clinique.abernathy.exceptions.ImpossibleAjouterPatientException;
import com.client.clinique.abernathy.exceptions.ImpossibleModifierPatientException;
import com.client.clinique.abernathy.exceptions.ListPatientsNonTrouvableException;
import com.client.clinique.abernathy.model.Patient;
import com.client.clinique.abernathy.proxies.MicroservicePatientProxy;
import com.client.clinique.abernathy.repository.PatientRepository;
import com.client.clinique.abernathy.service.PatientService;

@RestController
public class PatientController {

	@Autowired
	PatientRepository patientRepository;

	public PatientController(PatientRepository patientRepository) {
		super();
		this.patientRepository = patientRepository;
	}

//	private MicroservicePatientProxy patientsProxy;

//	public PatientController(MicroservicePatientProxy patientsProxy) {
//		this.patientsProxy = patientsProxy;
//	}

	public PatientController() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public static List<Patient> patients = new ArrayList<>();
//
//	static {
//		patients.add(new Patient(1, "Cerqueira", "Lara", new Date(), "F", "15 Rue Verdi", "0617348488"));
//		patients.add(new Patient(1, "Cerqueira", "Lara", new Date(), "F", "15 Rue Verdi", "0617348488"));
//	}

//	   static {
//		   patients.add(new Patient(1, "Cerqueira", "Lara", new Date(), "F", "15 Rue Verdi", "0617348488"));
//		   patients.add(new Patient(1, "Cerqueira", "Lara", new Date(), "F", "15 Rue Verdi", "0617348488"));
//	   }

	@GetMapping(value = "/patients")
	public String listePatients(Model model) {
		List<Patient> patients = patientRepository.findAll();
//		List<PatientBean> patients = patientsProxy.listeDesPatients();
		model.addAttribute("patients", patients);
		if (patients.equals(null))
			throw new ListPatientsNonTrouvableException(
					"La liste de patients ne peut pas etre affichée car elle ne contient aucun patient.");
		return "patients";
	}

	@GetMapping(value = "/patients/{id}")
	public Patient afficherUnPatient(@PathVariable int id) {
		return patientRepository.findById(id);
	}

	@PostMapping(value = "/patients")
	public ResponseEntity<Patient> ajouterPatient(@Valid @RequestBody Patient patient)
			throws ImpossibleAjouterPatientException {
		Patient patientAdded = patientRepository.save(patient);
		if (patientAdded == null)
			throw new ImpossibleAjouterPatientException("Impossible d'ajouter ce patient");
		return new ResponseEntity<Patient>(patient, HttpStatus.CREATED);
	}

	@PutMapping(value = "/patients")
	public ResponseEntity<Patient> updatePatient(@Valid @RequestBody Patient patient)
			throws ImpossibleModifierPatientException {
		Patient patientAdded = patientRepository.save(patient);
		if (patientAdded == null)
			throw new ImpossibleModifierPatientException("Impossible de modifier ce patient");
		return new ResponseEntity<Patient>(patient, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/patients/{id}")
	public ResponseEntity<Void> supprimerPatient(@PathVariable int id) {
		patientRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
