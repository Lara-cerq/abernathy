package com.client.clientui.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.client.clientui.beans.PatientBean;
import com.client.clientui.proxies.MicroservicePatientProxy;

@Controller
public class ClientController {

	private final MicroservicePatientProxy patientProxy;

	public ClientController(MicroservicePatientProxy patientProxy) {
		this.patientProxy = patientProxy;
	}
	
	@GetMapping(value = "/patients")
	public String listePatients(Model model) {
		List<PatientBean> patients = patientProxy.listePatients();
		model.addAttribute("patients", patients);
		return "patients";
	}

}
