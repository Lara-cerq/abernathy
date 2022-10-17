package com.client.clinique.abernathy.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import com.client.clinique.abernathy.exceptions.ListPatientsNonTrouvableException;
import com.client.clinique.abernathy.model.Patient;

@Repository
public abstract class PatientRepositoryImpl implements PatientRepository {

	   public static List<Patient> patients = new ArrayList<>();

	   static {
		   patients.add(new Patient(1, "Cerqueira", "Lara", new Date(), "F", "15 Rue Verdi", "0617348488"));
		   patients.add(new Patient(1, "Cerqueira", "Lara", new Date(), "F", "15 Rue Verdi", "0617348488"));
	   }
	
	@Override
	public List<Patient> findAll() {
		if(patients.equals(null)) throw new ListPatientsNonTrouvableException("La liste de patients ne peut pas etre affichée car elle ne contient aucun patient.");
		return patients;
	}

	@Override
	public Patient findById(int id) {
		for (Patient patient : patients){
	           if (patient.getIdPatient() == id){
	               return patient;
	           }
	       }
	       return null;
	}

	@Override
	public Patient save(Patient patient) {
		patients.add(patient);
		return patient;
	}

	@Override
	public List<Patient> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Optional<Patient> findById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Patient entity) {
		// TODO Auto-generated method stub
		
	}


}
