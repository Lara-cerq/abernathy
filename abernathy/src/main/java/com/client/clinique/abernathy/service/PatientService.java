package com.client.clinique.abernathy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
		List<Patient> patients = new ArrayList<>();
		try {
			patients = patientRepository.findAll();
		} catch (DataAccessException e) {
			System.out.println("La liste de patiensts est vide");
		}
		return patients;
	}

	public Patient findById(Integer id) {
		return patientRepository.getById(id);
	}

	public Optional<Patient> getId(Integer id) {
		Optional<Patient> patient = null;
		try {
			patient = patientRepository.findById(id);
		} catch (DataAccessException e) {
			System.out.println("Patient non trouvé!");
		}
		return patient;
	}

	public Patient addOrUpdatePatient(Patient patient) {
		Patient patientUpdated= new Patient();
		try {
			patientUpdated=patientRepository.save(patient);
		} catch (DataAccessException e) {
			System.out.println("Patient non modifié!");
		}
		return patientUpdated;
	}

	public void deletePatient(Integer id) {
		try {
			patientRepository.deleteById(id);
		} catch (DataAccessException e) {
			System.out.println("Patient non supprimé!");
		}
	}

}
