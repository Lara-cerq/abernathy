package com.client.clinique.rapportDiabete.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.client.clinique.rapportDiabete.model.HistoriqueBean;

@FeignClient(name = "microservice-historique", url = "localhost:8081")
public interface MicroserviceHistoriqueProxy {

	@GetMapping(value = "/historique/{idPatient}")
	List<HistoriqueBean> getHistoriquesByIdPatient(@PathVariable("idPatient") Integer idPatient);

	@PostMapping(value = "/addNote/{idPatient}")
	HistoriqueBean addOrUpdateHistorique(@PathVariable("idPatient") Integer idPatient, HistoriqueBean historique);
	
	@GetMapping(value = "/historiqueById/{id}")
	HistoriqueBean getHistoriqueById(@PathVariable("id") String id);
}
