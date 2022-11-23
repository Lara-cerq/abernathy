package com.client.clinique.rapportDiabete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.client.clinique.rapportDiabete.service.RapportService;

@SpringBootApplication
@EnableFeignClients
public class RapportDiabeteApplication {

	RapportService rapportService;

	public RapportDiabeteApplication(RapportService rapportService) {
		super();
		this.rapportService = rapportService;
	}

	public static void main(String[] args) {
		SpringApplication.run(RapportDiabeteApplication.class, args);

	}

}
