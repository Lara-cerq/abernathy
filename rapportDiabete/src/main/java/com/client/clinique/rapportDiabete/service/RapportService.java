package com.client.clinique.rapportDiabete.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.clinique.rapportDiabete.model.HistoriqueDto;
import com.client.clinique.rapportDiabete.model.PatientDto;
import com.client.clinique.rapportDiabete.proxy.MicroserviceHistoriqueProxy;
import com.client.clinique.rapportDiabete.proxy.MicroservicePatientProxy;

import feign.FeignException;

@Service
public class RapportService {

	@Autowired
	MicroserviceHistoriqueProxy historiqueProxy;

	@Autowired
	MicroservicePatientProxy patientProxy;

	public RapportService(MicroservicePatientProxy patientProxy) {
		super();
		this.patientProxy = patientProxy;
	}

	public RapportService(MicroserviceHistoriqueProxy historiqueProxy) {
		super();
		this.historiqueProxy = historiqueProxy;
	}

	public RapportService() {
		super();
		// TODO Auto-generated constructor stub
	}

	String[] termesDeclencheurs = { "Hémoglobine A1C", "Microalbumine", "Taille", "Poids", "Fumeur", "Anormal",
			"Cholestérol", "Vertige", "Rechute", "Réaction", "Anticorps" };
	List<String> termesList = Arrays.asList(termesDeclencheurs);

	public String generationRapportDiabete(Integer idPatient) {
		List<HistoriqueDto> historiques = new ArrayList<>();
		PatientDto patient = new PatientDto();
		String resultat = "";
		try {
			historiques = historiqueProxy.getHistoriquesByIdPatient(idPatient);
			patient = patientProxy.getPatientById(idPatient);
			String genre = patient.getGenre();
			int dateBirth = patient.getDateNaissance().getYear();
			Calendar currentDate = Calendar.getInstance();
			int age = currentDate.get(Calendar.YEAR) - dateBirth;
			List<String> notes = new ArrayList<>();
			for (HistoriqueDto historiqueCherche : historiques) {
				String note = historiqueCherche.getNote();
				notes.add(note);
			}
			int countNor = 0;
			int countLow = 0;
			for (String terme : termesList) {
				String termeLowerCase = terme.toLowerCase();
				for (String noteCherche : notes) {
					if (noteCherche.contains(terme)) {
						countNor++;
					} else if (noteCherche.contains(termeLowerCase)) {
						countLow++;
					}
				}
			}
			int count = countNor + countLow;
			if (count >= 2 && count < 6 && age >= 30) {
				resultat = "Borderline";
			} else if (genre.equals("H") && age < 30 && count == 3 || genre.equals("F") && age < 30 && count == 4
					|| age >= 30 && count >= 6 && count < 8) {
				resultat = "Danger";
			} else if (genre.equals("H") && age < 30 && count == 5 || genre.equals("F") && age < 30 && count == 6
					|| age >= 30 && count >= 8) {
				resultat = "Early onset";
			} else {
				resultat = "None";
			}
		} catch (FeignException e) {
			System.out.println("Not exists!");
		}
		return resultat;
	}
}
