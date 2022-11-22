package com.client.clinique.rapportDiabete;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.client.clinique.rapportDiabete.model.RapportDiabete;
import com.client.clinique.rapportDiabete.service.RapportService;

@SpringBootApplication
@EnableFeignClients
public class RapportDiabeteApplication implements CommandLineRunner {

	RapportService rapportService;

	public RapportDiabeteApplication(RapportService rapportService) {
		super();
		this.rapportService = rapportService;
	}

	public static void main(String[] args) {
		SpringApplication.run(RapportDiabeteApplication.class, args);

	}

//	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		RapportDiabete rapport1 = new RapportDiabete("Hémoglobine A1C");
		RapportDiabete rapport2 = new RapportDiabete("Microalbumine");
		RapportDiabete rapport3 = new RapportDiabete("Taille");
		RapportDiabete rapport4 = new RapportDiabete("Poids");
		RapportDiabete rapport5 = new RapportDiabete("Fumeur");
		RapportDiabete rapport6 = new RapportDiabete("Anormal");
		RapportDiabete rapport7 = new RapportDiabete("Cholestérol");
		RapportDiabete rapport8 = new RapportDiabete("Vertige");
		RapportDiabete rapport9 = new RapportDiabete("Rechute");
		RapportDiabete rapport10 = new RapportDiabete("Réaction");
		RapportDiabete rapport11 = new RapportDiabete("Anticorps");
		RapportDiabete rapport12 = new RapportDiabete("Anormale");

		rapportService.addRapport(rapport1);
		rapportService.addRapport(rapport2);
		rapportService.addRapport(rapport3);
		rapportService.addRapport(rapport4);
		rapportService.addRapport(rapport5);
		rapportService.addRapport(rapport6);
		rapportService.addRapport(rapport7);
		rapportService.addRapport(rapport8);
		rapportService.addRapport(rapport9);
		rapportService.addRapport(rapport10);
		rapportService.addRapport(rapport11);
		rapportService.addRapport(rapport12);
	}

}
