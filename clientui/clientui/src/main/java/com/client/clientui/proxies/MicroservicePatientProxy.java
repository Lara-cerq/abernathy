package com.client.clientui.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.client.clientui.beans.PatientBean;

@FeignClient(name = "microservice-patient", url = "localhost:8080")
public interface MicroservicePatientProxy {
	
	   @GetMapping(value = "/patients")
	   List<PatientBean> listePatients();

	   @GetMapping( value = "/patients/{id}")
	   PatientBean recupererUnPatient(@PathVariable("id") int id);

}
