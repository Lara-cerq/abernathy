package com.client.clinique.abernathy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.client.clinique.abernathy.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

	Patient findById(int id);

}
