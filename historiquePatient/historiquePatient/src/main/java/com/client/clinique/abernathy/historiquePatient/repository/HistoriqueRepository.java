package com.client.clinique.abernathy.historiquePatient.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.client.clinique.abernathy.historiquePatient.model.HistoriquePatient;

@Repository
public interface HistoriqueRepository extends MongoRepository<HistoriquePatient, String> {
	HistoriquePatient findHistoriqueByIdPatient(Integer idPatient);
}
