package com.client.clientui.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-rapport", url = "localhost:8083")
public interface MicroserviceRapportProxy {

	@GetMapping(value = "/termes")
	List<String> getAllTermes();

	@GetMapping("/rapport/{idPatient}")
	String generationRapportDiabete(@PathVariable("idPatient") Integer idPatient);

}
