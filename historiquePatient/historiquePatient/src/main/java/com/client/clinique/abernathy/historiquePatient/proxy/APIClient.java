package com.client.clinique.abernathy.historiquePatient.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.client.clinique.abernathy.historiquePatient.beans.PatientBean;

@FeignClient(name = "microservice-patient", url = "localhost:8080")
public interface APIClient {
	
	   @GetMapping(value = "/patients")
	   List<PatientBean> listePatients();
	   
	   @GetMapping(value = "/patient/{id}")
	   PatientBean getPatientById(@PathVariable("id") Integer idPatient);

}
