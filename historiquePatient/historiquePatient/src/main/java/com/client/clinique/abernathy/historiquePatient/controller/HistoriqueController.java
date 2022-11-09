package com.client.clinique.abernathy.historiquePatient.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.client.clinique.abernathy.historiquePatient.beans.HistoriqueBean;
import com.client.clinique.abernathy.historiquePatient.beans.PatientBean;
import com.client.clinique.abernathy.historiquePatient.beans.ResultatBean;
import com.client.clinique.abernathy.historiquePatient.model.HistoriquePatient;
import com.client.clinique.abernathy.historiquePatient.repository.HistoriqueRepository;
import com.client.clinique.abernathy.historiquePatient.service.HistoriqueService;

@Controller
public class HistoriqueController {

	@Autowired
	HistoriqueService historiqueService;

//	public HistoriqueController(HistoriqueRepository historiqueRepository) {
//		this.historiqueRepository = historiqueRepository;
//	}

	public HistoriqueController(HistoriqueService historiqueService) {
		this.historiqueService = historiqueService;
	}

//	@DeleteMapping("/historiques/{id}")
//	public ResponseEntity<HttpStatus> deleteHistorique(@PathVariable("id") String id) {
//		historiqueRepository.deleteById(id);
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//	}

	@GetMapping(value = "/historiques")
	public String showHistorique(Model model) {
//		Integer idPatient = 0;
		List<HistoriquePatient> historiques = historiqueService.getAllHistoriques();
//		for (HistoriquePatient historique : historiques) {
//			idPatient = historique.getIdPatient();
//		}
//		ResultatBean resultat= historiqueService.getHistorique(idPatient);
//		PatientBean patient= resultat.getPatient();
		model.addAttribute("historiques", historiques);
//		model.addAttribute("patient", patient);
		return "historiques";
	}

	@GetMapping("/addHistorique")
	public String showAjoutForm(HistoriquePatient historique, Model model) {
		model.addAttribute("historique", historique);
		return "addHistorique";
	}

	@Transactional
	@PostMapping("/addHistorique")
	public String addNote(@Valid HistoriquePatient historique, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "addHistorique";
		}
//		historique.setNote(historique.getNote());
		historiqueService.saveOrUpdateHistorique(historique);
		model.addAttribute("historique", historique);
		return "redirect:/historiques";
	}

	@GetMapping("/updateHistorique/{id}")
	public String showUpdateForm(@PathVariable("id") String id, Model model) {
		HistoriquePatient historique = historiqueService.findHistoriqueById(id);
		model.addAttribute("historique", historique);
		return "updateHistorique";
	}

	@Transactional
	@PostMapping("/updateHistorique/{id}")
	public String updatePatient(@PathVariable("id") String id, @Valid HistoriquePatient historique,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "updateHistorique";
		}
		historique.setId(id);
		historiqueService.saveOrUpdateHistorique(historique);
		return "redirect:/historiques";
	}

	@DeleteMapping(value = "/patients/{id}")
	public void supprimerHistorique(@PathVariable String id) {
		historiqueService.deleteHistorique(id);
	}

	@GetMapping("/getPatientById/{id}")
	public ResponseEntity<ResultatBean> getPatientById(@PathVariable("id") Integer idPatient) {
		ResultatBean resultat = historiqueService.getHistorique(idPatient);
		return new ResponseEntity<>(resultat, HttpStatus.OK);
	}

}
