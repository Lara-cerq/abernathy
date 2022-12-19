package com.client.clinique.abernathy.historique.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.client.clinique.abernathy.historique.model.Historique;
import com.client.clinique.abernathy.historique.model.HistoriqueDto;
import com.client.clinique.abernathy.historique.repository.HistoriqueRepository;

@Service
public class HistoriqueService {

	HistoriqueRepository historiqueRepository;

	public HistoriqueService(HistoriqueRepository historiqueRepository) {
		this.historiqueRepository = historiqueRepository;
	}

	public Historique findHistoriqueById(String id) {
		Historique historique = new Historique();
		try {
			historique = historiqueRepository.findById(id).get();
		} catch (DataAccessException e) {
			System.out.println("Historique non trouvé!");
		}
		return historique;
	}

	@Transactional
	public Historique saveOrUpdateHistorique(Integer idPatient, Historique historique) {
		try {
			historiqueRepository.save(historique);
		} catch (DataAccessException e) {
			System.out.println("Impossible d'ajouter/modifier un historique");
		}
		return historique;
	}

	public List<HistoriqueDto> getHistoriquesByIdPatient(Integer idPatient) {
		List<Historique> historiqueList = new ArrayList<>();
		List<HistoriqueDto> dtoList = new ArrayList<>();
		try {
			historiqueList = historiqueRepository.getHistoriquesByIdPatient(idPatient);
			dtoList = mapToHistorique(historiqueList);
		} catch (DataAccessException e) {
			System.out.println("Impossible de trouver un historique");
		}
		return dtoList;
	}

	public List<HistoriqueDto> mapToHistorique(List<Historique> historiqueList) {
		List<HistoriqueDto> dtoList = new ArrayList<>();
		for (Historique historique : historiqueList) {
			HistoriqueDto historiqueDto = new HistoriqueDto();
			historiqueDto.setId(historique.getId());
			historiqueDto.setIdPatient(historique.getIdPatient());
			historiqueDto.setNote(historique.getNote());
			dtoList.add(historiqueDto);
		}
		return dtoList;
	}
}
