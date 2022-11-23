package com.client.clinique.abernathy.historique.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.client.clinique.abernathy.historique.model.Historique;
import com.client.clinique.abernathy.historique.model.HistoriqueBean;
import com.client.clinique.abernathy.historique.repository.HistoriqueRepository;

@Service
public class HistoriqueService {

	@Autowired
	HistoriqueRepository historiqueRepository;

	public HistoriqueService(HistoriqueRepository historiqueRepository) {
		this.historiqueRepository = historiqueRepository;
	}

	public HistoriqueService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Historique findHistoriqueById(String id) {
		Historique historique = new Historique();
		try {
			historique = historiqueRepository.findById(id).get();
		} catch (Exception e) {
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

	public void deleteHistorique(String id) {
		try {
			historiqueRepository.deleteById(id);
		} catch (DataAccessException e) {
			System.out.println("Impossible de supprimer cet historique");
		}
	}

	public List<HistoriqueBean> getHistoriquesByIdPatient(Integer idPatient) {
		List<Historique> historiqueList = new ArrayList<>();
		List<HistoriqueBean> beanList = new ArrayList<>();
		try {
			historiqueList = historiqueRepository.getHistoriquesByIdPatient(idPatient);
			beanList = mapToHistorique(historiqueList);
		} catch (DataAccessException e) {
			System.out.println("Impossible de trouver un historique");
		}
		return beanList;
	}

	public List<HistoriqueBean> mapToHistorique(List<Historique> historiqueList) {
		List<HistoriqueBean> beanList = new ArrayList<>();
		for (Historique historique : historiqueList) {
			HistoriqueBean historiqueBean = new HistoriqueBean();
			historiqueBean.setId(historique.getId());
			historiqueBean.setIdPatient(historique.getIdPatient());
			historiqueBean.setNote(historique.getNote());
			beanList.add(historiqueBean);
		}
		return beanList;

	}
}
