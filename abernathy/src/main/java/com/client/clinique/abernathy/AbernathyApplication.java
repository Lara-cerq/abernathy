package com.client.clinique.abernathy;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.client.clinique.abernathy.model.Patient;
import com.client.clinique.abernathy.service.PatientService;

@SpringBootApplication
@EnableFeignClients
public class AbernathyApplication  implements CommandLineRunner {
	
	@Autowired
	PatientService patientService;

	public static void main(String[] args) {
		SpringApplication.run(AbernathyApplication.class, args);
	}

//	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Patient patient= new Patient("Lara", "Cerqueira", new Date(), "F", "15 Rue Verdi", "00000000");
		patientService.addOrUpdatePatient(patient);
	}

}
