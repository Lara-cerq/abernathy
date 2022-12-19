package com.client.clinique.rapportDiabete.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.client.clinique.rapportDiabete.model.PatientDto;

@FeignClient(name = "microservice-patient", url = "localhost:8084")
public interface MicroservicePatientProxy {

	@GetMapping(value = "/patients")
	List<PatientDto> listePatients();

	@GetMapping(value = "/patient/{id}")
	PatientDto getPatientById(@PathVariable("id") int id);

	@PostMapping(value = "/addPatient")
	PatientDto addOrUpdatePatient(PatientDto patient);

}
