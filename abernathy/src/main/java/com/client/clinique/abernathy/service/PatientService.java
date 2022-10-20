package com.client.clinique.abernathy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.clinique.abernathy.model.Patient;
import com.client.clinique.abernathy.repository.PatientRepository;

@Service
public class PatientService {

	PatientRepository patientRepository;

	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	public Patient findById(Integer id) {
		return patientRepository.getById(id);
	}

	public Optional<Patient> getId(Integer id) {
		return patientRepository.findById(id);
	}

	public Patient addOrUpdatePatient(Patient patient) {
		return patientRepository.save(patient);
	}

//	public void deletePatient(Integer id) {
//		patientRepository.deleteById(id);
//	}

}
