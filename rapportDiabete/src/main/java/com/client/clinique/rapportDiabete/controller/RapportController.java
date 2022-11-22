package com.client.clinique.rapportDiabete.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.client.clinique.rapportDiabete.model.HistoriqueBean;
import com.client.clinique.rapportDiabete.model.PatientBean;
import com.client.clinique.rapportDiabete.proxy.MicroserviceHistoriqueProxy;
import com.client.clinique.rapportDiabete.proxy.MicroservicePatientProxy;
import com.client.clinique.rapportDiabete.service.RapportService;

@RestController
public class RapportController {

	@Autowired
	RapportService rapportService;

	@Autowired
	MicroserviceHistoriqueProxy historiqueProxy;

	@Autowired
	MicroservicePatientProxy patientProxy;

	public RapportController(RapportService rapportService) {
		super();
		this.rapportService = rapportService;
	}

	public RapportController(MicroserviceHistoriqueProxy historiqueProxy) {
		super();
		this.historiqueProxy = historiqueProxy;
	}

	public RapportController(MicroservicePatientProxy patientProxy) {
		super();
		this.patientProxy = patientProxy;
	}

	public RapportController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@GetMapping(value = "/termes")
	public ResponseEntity<List<String>> getAllTermes() {
		List<String> termes = rapportService.getAllTermes();
		return new ResponseEntity<>(termes, HttpStatus.OK);
	}

	@GetMapping("/rapport/{idPatient}")
	public ResponseEntity<String> generationRapportDiabete(@PathVariable("idPatient") Integer idPatient) {
		List<String> termesDeclencheurs = rapportService.getAllTermes();
		List<HistoriqueBean> historiques = historiqueProxy.getHistoriquesByIdPatient(idPatient);
		PatientBean patient = patientProxy.getPatientById(idPatient);
		String genre = patient.getGenre();
		int dateBirth = patient.getDateNaissance().getYear();
		Calendar currentDate = Calendar.getInstance();
		int age = currentDate.get(Calendar.YEAR) - dateBirth;
		List<String> notes = new ArrayList<>();
		for (HistoriqueBean historiqueCherche : historiques) {
			String note = historiqueCherche.getNote();
			notes.add(note);
		}
		int count = 0;
		for (String terme : termesDeclencheurs) {
			for (String noteCherche : notes) {
				if (noteCherche.contains(terme)) {
					count++;
				}
			}
		}
		String resultat = "";
		if (count < 2) {
			resultat = "None";
		} else if (count == 2 && age >= 30) {
			resultat = "Borderline";
		} else if (genre.equals("H") && age < 30 && count == 3 || genre.equals("F") && age < 30 && count == 4
				|| age >= 30 && count == 6) {
			resultat = "Danger";
		} else if (genre.equals("H") && age < 30 && count == 5 || genre.equals("F") && age < 30 && count == 6
				|| age >= 30 && count >= 8) {
			resultat = "Early onset";
		}
		return new ResponseEntity<>(resultat, HttpStatus.OK);
	}
}
