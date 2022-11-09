package com.client.clinique.abernathy.historiquePatient.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.client.clinique.abernathy.historiquePatient.beans.HistoriqueBean;
import com.client.clinique.abernathy.historiquePatient.beans.PatientBean;
import com.client.clinique.abernathy.historiquePatient.beans.ResultatBean;
import com.client.clinique.abernathy.historiquePatient.model.HistoriquePatient;
import com.client.clinique.abernathy.historiquePatient.proxy.APIClient;
import com.client.clinique.abernathy.historiquePatient.repository.HistoriqueRepository;

@Service
public class HistoriqueService {

	@Autowired
	HistoriqueRepository historiqueRepository;

	@Autowired
	APIClient apiClient;

	public HistoriqueService(HistoriqueRepository historiqueRepository) {
		this.historiqueRepository = historiqueRepository;
	}

	public HistoriqueService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HistoriqueService(APIClient apiClient) {
		this.apiClient = apiClient;
	}

	public List<HistoriquePatient> getAllHistoriques() {
		List<HistoriquePatient> historiques = new ArrayList<>();
		try {
			historiques = historiqueRepository.findAll();
		} catch (DataAccessException e) {
			System.out.println("Il n'existe pas encore d'historique pour aucun patient.");
		}
		return historiques;
	}

	public HistoriquePatient findHistoriqueById(String id) {
		HistoriquePatient historique = new HistoriquePatient();
		try {
			historique = historiqueRepository.findById(id).get();
		} catch (Exception e) {
			System.out.println("Historique non trouvé!");
		}
		return historique;
	}

	public HistoriquePatient saveOrUpdateHistorique(HistoriquePatient historique) {
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

	public ResultatBean getHistorique(Integer idPatient) {
		ResultatBean resultat = new ResultatBean();
		HistoriquePatient historique = historiqueRepository.findHistoriqueByIdPatient(idPatient);
		HistoriqueBean historiqueBean = mapToHistorique(historique);

		PatientBean patientBean = apiClient.getPatientById(historique.getIdPatient());
		resultat.setPatient(patientBean);
		resultat.setHistorique(historiqueBean);
		return resultat;

	}

	public HistoriqueBean mapToHistorique(HistoriquePatient historique) {
		HistoriqueBean historiqueBean = new HistoriqueBean();
		historiqueBean.setId(historique.getId());
		historiqueBean.setIdPatient(historique.getIdPatient());
		historiqueBean.setNote(historique.getNote());
		return historiqueBean;

	}

}
