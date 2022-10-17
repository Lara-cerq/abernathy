package com.client.clinique.abernathy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.clinique.abernathy.model.Patient;
import com.client.clinique.abernathy.repository.PatientRepository;

@Service
public class PatientService {
	
	   public static List<Patient> patients = new ArrayList<>();

//	   static {
//		   patients.add(new Patient(1, "Cerqueira", "Lara", "28/10/1993", "F", "15 Rue Verdi", "0617348488"));
//		   patients.add(new Patient(1, "Cerqueira", "Lara", "28/10/1993", "F", "15 Rue Verdi", "0617348488"));
//	   }

	@Autowired
	PatientRepository patientRepository;

	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}
	
	public Patient addNewPatient(Patient patient) {
		return patientRepository.save(patient);
	}

}
