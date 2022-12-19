package com.client.clientui.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.client.clientui.dto.HistoriqueDto;
import com.client.clientui.dto.PatientDto;
import com.client.clientui.proxies.MicroserviceHistoriqueProxy;
import com.client.clientui.proxies.MicroservicePatientProxy;
import com.client.clientui.proxies.MicroserviceRapportProxy;

@Controller
public class ClientController {

	@Autowired
	private MicroservicePatientProxy patientProxy;

	@Autowired
	private MicroserviceHistoriqueProxy historiqueProxy;

	@Autowired
	private MicroserviceRapportProxy rapportProxy;

	public ClientController(MicroservicePatientProxy patientProxy) {
		this.patientProxy = patientProxy;
	}

	public ClientController(MicroserviceHistoriqueProxy historiqueProxy) {
		super();
		this.historiqueProxy = historiqueProxy;
	}

	public ClientController(MicroserviceRapportProxy rapportProxy) {
		super();
		this.rapportProxy = rapportProxy;
	}

	public ClientController() {
		super();
	}

	@GetMapping(value = "/patients")
	public String listePatients(Model model) {
		List<PatientDto> patients = patientProxy.listePatients();
		model.addAttribute("patients", patients);
		return "patients";

	}

	@GetMapping("/addPatient")
	public String showAjoutForm(PatientDto patient, Model model) {
		model.addAttribute("patient", patient);
		return "addPatient";
	}

	@PostMapping(value = "/addPatient")
	public String addPatient(@Valid @ModelAttribute("patient") PatientDto patient, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "addPatient";
		}
		patientProxy.addOrUpdatePatient(patient);
		return "redirect:/patients";
	}

	@GetMapping("/updatePatient/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		PatientDto patient = patientProxy.getPatientById(id);
		model.addAttribute("patient", patient);
		return "updatePatient";
	}

	@PostMapping("/updatePatient/{id}")
	public String updatePatient(@PathVariable("id") Integer id, @Valid PatientDto patient, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "updatePatient";
		}
		patient.setIdPatient(id);
		patientProxy.addOrUpdatePatient(patient);
		return "redirect:/patients";
	}

	@GetMapping(value = "/historique/{idPatient}")
	public String getHistoriquesByIdPatient(@PathVariable("idPatient") Integer idPatient, HistoriqueDto historique,
			Model model) {
		List<HistoriqueDto> historiques = historiqueProxy.getHistoriquesByIdPatient(idPatient);
		PatientDto patient = patientProxy.getPatientById(idPatient);
		model.addAttribute("historique", historique);
		model.addAttribute("historiques", historiques);
		model.addAttribute("patient", patient);
		return "historiques";
	}

	@GetMapping("/addNote/{idPatient}")
	public String showAjoutForm(@PathVariable("idPatient") Integer idPatient, @Valid HistoriqueDto historique,
			Model model) {
		historique.setIdPatient(idPatient);
		model.addAttribute("idPatient", idPatient);
		model.addAttribute("historique", historique);
		return "/addNote";
	}

	@PostMapping("/addNote/{idPatient}")
	public String addNote(@PathVariable("idPatient") Integer idPatient, @Valid HistoriqueDto historique,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "addNote";
		}
		historique.setIdPatient(idPatient);
		historiqueProxy.addOrUpdateHistorique(idPatient, historique);
		model.addAttribute("historique", historique);
		return "redirect:/historique/" + historique.getIdPatient();
	}

	@GetMapping("/updateHistorique/{id}")
	public String showUpdateForm(@PathVariable("id") String id, Model model) {
		HistoriqueDto historique = historiqueProxy.getHistoriqueById(id);
		historique.setIdPatient(historique.getIdPatient());
		model.addAttribute("historique", historique);
		return "updateHistorique";
	}

	@PostMapping("/updateHistorique/{id}")
	public String updateHistorique(@PathVariable("id") String id, @Valid HistoriqueDto historique,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "updateHistorique";
		}
		historique.setId(id);
		historique.setIdPatient(historique.getIdPatient());

		historiqueProxy.addOrUpdateHistorique(historique.getIdPatient(), historique);
		model.addAttribute("idPatient", historique.getIdPatient());
		model.addAttribute("historique", historique);
		return "redirect:/historique/" + historique.getIdPatient();
	}

	@GetMapping("/rapport/{idPatient}")
	public String generationRapportDiabete(@ModelAttribute("resultat") String resultat,
			@PathVariable("idPatient") Integer idPatient, Model model) {
		resultat = rapportProxy.generationRapportDiabete(idPatient);
		PatientDto patient = patientProxy.getPatientById(idPatient);
		model.addAttribute("resultat", resultat);
		model.addAttribute("patient", patient);
		return "rapport";
	}

}
