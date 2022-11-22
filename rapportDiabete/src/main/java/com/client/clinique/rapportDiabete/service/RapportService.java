package com.client.clinique.rapportDiabete.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.client.clinique.rapportDiabete.model.RapportDiabete;
import com.client.clinique.rapportDiabete.repository.RapportRepository;

@Service
public class RapportService {

	RapportRepository rapportRepository;

	public RapportService(RapportRepository rapportRepository) {
		super();
		this.rapportRepository = rapportRepository;
	}

	public List<String> getAllTermes() {
		List<RapportDiabete> rapports = new ArrayList<>();
		List<String> termes = new ArrayList<>();
		try {
			rapports = rapportRepository.findAll();
		} catch (DataAccessException e) {
			System.out.println("La liste de rapports est vide");
		}
		for (RapportDiabete rapport : rapports) {
			String terme = rapport.getTermeDeclencheur();
			termes.add(terme);
		}
		return termes;
	}

	@Transactional
	public RapportDiabete addRapport(RapportDiabete rapport) {
		RapportDiabete rapportAdded = new RapportDiabete();
		try {
			rapportAdded = rapportRepository.save(rapport);
		} catch (DataAccessException e) {
			System.out.println("Rapport non ajouté!");
		}
		return rapportAdded;
	}
}
