package com.client.clientui.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.client.clientui.beans.HistoriqueBean;
import com.client.clientui.beans.PatientBean;
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
		// TODO Auto-generated constructor stub
	}

	@GetMapping(value = "/patients")
	public String listePatients(Model model) {
		List<PatientBean> patients = patientProxy.listePatients();
		model.addAttribute("patients", patients);
		return "patients";

	}

	@GetMapping("/addPatient")
	public String showAjoutForm(PatientBean patient, Model model) {
		model.addAttribute("patient", patient);
		return "addPatient";
	}

	@PostMapping(value = "/addPatient")
	public String addPatient(@Valid @ModelAttribute("patient") PatientBean patient, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "addPatient";
		}
		patientProxy.addOrUpdatePatient(patient);
//		model.addAttribute("patient", patient);
		return "redirect:/patients";
	}

	@GetMapping("/updatePatient/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		PatientBean patient = patientProxy.getPatientById(id);
		model.addAttribute("patient", patient);
		return "updatePatient";
	}

	@PostMapping("/updatePatient/{id}")
	public String updatePatient(@PathVariable("id") Integer id, @Valid PatientBean patient, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "updatePatient";
		}
		patient.setIdPatient(id);
		patientProxy.addOrUpdatePatient(patient);
		return "redirect:/patients";
	}

	@GetMapping(value = "/historique/{idPatient}")
	public String getHistoriquesByIdPatient(@PathVariable("idPatient") Integer idPatient, HistoriqueBean historique,
			Model model) {
		List<HistoriqueBean> historiques = historiqueProxy.getHistoriquesByIdPatient(idPatient);
		PatientBean patient = patientProxy.getPatientById(idPatient);
		model.addAttribute("historique", historique);
		model.addAttribute("historiques", historiques);
		model.addAttribute("patient", patient);
		return "historiques";
	}

	@GetMapping("/addNote/{idPatient}")
	public String showAjoutForm(@PathVariable("idPatient") Integer idPatient, @Valid HistoriqueBean historique,
			Model model) {
		historique.setIdPatient(idPatient);
		model.addAttribute("idPatient", idPatient);
		model.addAttribute("historique", historique);
		return "/addNote";
	}

	@PostMapping("/addNote/{idPatient}")
	public String addNote(@PathVariable("idPatient") Integer idPatient, @Valid HistoriqueBean historique,
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
		HistoriqueBean historique = historiqueProxy.getHistoriqueById(id);
		model.addAttribute("historique", historique);
		return "updateHistorique";
	}

	@PostMapping("/updateHistorique/{id}")
	public String updateHistorique(@PathVariable("id") String id, @Valid HistoriqueBean historique,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "updateHistorique";
		}
		historique.setId(id);
		historique.setIdPatient(historique.getIdPatient());

		historiqueProxy.addOrUpdateHistorique(historique.getIdPatient(), historique);
		model.addAttribute("historique", historique);
		return "redirect:/historique/" + historique.getIdPatient();
	}

	@GetMapping("/rapport/{idPatient}")
	public String generationRapportDiabete(@ModelAttribute("resultat") String resultat,
			@PathVariable("idPatient") Integer idPatient, Model model) {
		resultat = rapportProxy.generationRapportDiabete(idPatient);
		PatientBean patient = patientProxy.getPatientById(idPatient);
		model.addAttribute("resultat", resultat);
		model.addAttribute("patient", patient);
		return "rapport";
	}

}
