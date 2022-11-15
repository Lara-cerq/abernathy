package com.client.clinique.abernathy.historique.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.client.clinique.abernathy.historique.model.Historique;

@Repository
public interface HistoriqueRepository extends MongoRepository<Historique, String> {
	List<Historique> getHistoriquesByIdPatient(Integer idPatient);
}
