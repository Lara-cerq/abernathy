package com.client.clinique.abernathy.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.client.clinique.abernathy.beans.PatientBean;

@FeignClient(name = "microservice-patients", url = "localhost:8080")
public interface MicroservicePatientProxy {
	
	   @GetMapping(value = "/patients")
	   List<PatientBean> listeDesPatients();

	   @GetMapping( value = "/patients/{id}")
	   PatientBean recupererUnPatient(@PathVariable("id") int id);

}
