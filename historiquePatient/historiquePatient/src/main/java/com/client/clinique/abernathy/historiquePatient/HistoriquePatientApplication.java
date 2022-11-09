package com.client.clinique.abernathy.historiquePatient;

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

import com.client.clinique.abernathy.historiquePatient.model.HistoriquePatient;
import com.client.clinique.abernathy.historiquePatient.repository.HistoriqueRepository;
import com.mongodb.client.MongoClient;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = HistoriqueRepository.class)
@EnableFeignClients
public class HistoriquePatientApplication implements CommandLineRunner {
	
	private final Logger logger = LoggerFactory.getLogger(HistoriquePatientApplication.class);

	private HistoriqueRepository historiqueRepository;

	public HistoriquePatientApplication(HistoriqueRepository historiqueRepository) {
		this.historiqueRepository = historiqueRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(HistoriquePatientApplication.class, args);
	}

//	@Override
	public void run(String... args) throws Exception {
        HistoriquePatient historique= new HistoriquePatient();
        historique.setNote("Lara");
        historique.setIdPatient(1);

        historiqueRepository.insert(historique);
        
        List<HistoriquePatient> historiques = historiqueRepository.findAll();
//        historiques.stream().forEach((historiqueNew) -> logger.info(historiqueNew.toString()));
        
        Example<HistoriquePatient> example = Example.of(historique);

        // Getting the searched Post
        historique = historiqueRepository.findOne(example).get();

        // Building a new name and updating the object Post
        String newNote = historique.getNote() + " [updated]";
        historique.setNote(newNote);

        // Saving this change in database
        historiqueRepository.save(historique);
        
        historiques.stream().forEach((historiqueNew) -> logger.info(historiqueNew.toString()));
        
//        historiqueRepository.deleteAll();

	}

}
