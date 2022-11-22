package com.client.clinique.abernathy.historique;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.client.clinique.abernathy.historique.model.Historique;
import com.client.clinique.abernathy.historique.repository.HistoriqueRepository;
import com.client.clinique.abernathy.historique.service.HistoriqueService;
import com.mongodb.client.MongoClient;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = HistoriqueRepository.class)
@EnableFeignClients
public class HistoriqueApplication implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(HistoriqueApplication.class);

	private HistoriqueService historiqueService;

	public HistoriqueApplication(HistoriqueService historiqueService) {
		super();
		this.historiqueService = historiqueService;
	}

	public static void main(String[] args) {
		SpringApplication.run(HistoriqueApplication.class, args);
	}

//	@Override
	public void run(String... args) throws Exception {
		Historique historique = new Historique(1,
				"Poids");
		Historique historique2 = new Historique(1,
				"fatigue. Tests de laboratoire indiquant une microalbumine élevée");
		Historique historique3 = new Historique(1,
				" Fumeur. Tests de laboratoire indiquant que les anticorps sont élevés");
		Historique historique4 = new Historique(2, "stress "
				+ "Il se plaint également que son audition est anormal dernièrement");
		Historique historique5 = new Historique(2,
				"Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois");
		Historique historique6 = new Historique(2, "Tests de laboratoire indiquant une microalbumine élevée");
		Historique historique7 = new Historique(2, "Le patient déclare que tout semble aller bien");
		Historique historique8 = new Historique(3, "Le patient déclare qu'il fume depuis peu");
		Historique historique9 = new Historique(3, "Tests de laboratoire indiquant une microalbumine élevée");
		Historique historique10 = new Historique(3,
				"\n" + "Il se plaint également de crises d’apnée respiratoire anormales\n"
						+ "Tests de laboratoire indiquant un taux de cholestérol LDL élevé\n");
		Historique historique11 = new Historique(3, "Tests de laboratoire indiquant un taux de cholestérol LDL élevé");
		Historique historique12 = new Historique(4,
				"Le patient déclare qu'il lui est devenu difficile de monter les escaliers\n"
						+ "Il se plaint également d’être essoufflé\n"
						+ "Tests de laboratoire indiquant que les anticorps sont élevés\n"
						+ "Réaction aux médicaments\n");
		Historique historique13 = new Historique(4,
				"Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps");
		Historique historique14 = new Historique(4, "Le patient déclare avoir commencé à fumer depuis peu\n"
				+ "Hémoglobine A1C supérieure au niveau recommandé\"");

		// Saving this change in database
		historiqueService.saveOrUpdateHistorique(1, historique);
		historiqueService.saveOrUpdateHistorique(1, historique2);
		historiqueService.saveOrUpdateHistorique(1, historique3);
		historiqueService.saveOrUpdateHistorique(1, historique4);
		historiqueService.saveOrUpdateHistorique(1, historique5);
		historiqueService.saveOrUpdateHistorique(1, historique6);
		historiqueService.saveOrUpdateHistorique(1, historique7);
		historiqueService.saveOrUpdateHistorique(1, historique8);
		historiqueService.saveOrUpdateHistorique(1, historique9);
		historiqueService.saveOrUpdateHistorique(1, historique10);
		historiqueService.saveOrUpdateHistorique(1, historique11);
		historiqueService.saveOrUpdateHistorique(1, historique12);
		historiqueService.saveOrUpdateHistorique(1, historique13);
		historiqueService.saveOrUpdateHistorique(1, historique14);
	}

}
