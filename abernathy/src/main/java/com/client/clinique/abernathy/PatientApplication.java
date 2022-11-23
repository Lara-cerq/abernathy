package com.client.clinique.abernathy;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.client.clinique.abernathy.model.Patient;
import com.client.clinique.abernathy.service.PatientService;

@SpringBootApplication
@EnableFeignClients
public class PatientApplication implements CommandLineRunner {

	@Autowired
	PatientService patientService;

	public static void main(String[] args) {
		SpringApplication.run(PatientApplication.class, args);
	}

//	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Patient patient1 = new Patient("Ferguson", "Lucas", LocalDate.of(1968, 06, 22), "M", "2 Warren Street",
				"387-866-1399");
		Patient patient2 = new Patient("Rees", "Pippa", LocalDate.of(1993, 11, 11), "F", "745 West Valley Farms Drive",
				"387-866-1399");
		Patient patient3 = new Patient("Arnold", "Edward", LocalDate.of(2004, 11, 11), "M",
				"745 West Valley Farms Drive", "387-866-1399");
		Patient patient4 = new Patient("Sharp", "Anthony", LocalDate.of(1946, 11, 26), "M",
				"745 West Valley Farms Drive", "387-866-1399");
		Patient patient5 = new Patient("Ince", "Wendy", LocalDate.of(1946, 11, 26), "M", "745 West Valley Farms Drive",
				"387-866-1399");
		Patient patient6 = new Patient("Ross", "Tracey", LocalDate.of(1949, 12, 07), "F", "745 West Valley Farms Drive",
				"387-866-1399");
		Patient patient7 = new Patient("Wilson", "Claire", LocalDate.of(1966, 12, 31), "F",
				"745 West Valley Farms Drive", "387-866-1399");
		Patient patient8 = new Patient("Buckland", "Max", LocalDate.of(1945, 06, 24), "M",
				"745 West Valley Farms Drive", "387-866-1399");
		Patient patient9 = new Patient("Clark", "Natalie", LocalDate.of(1964, 06, 18), "F",
				"745 West Valley Farms Drive", "387-866-1399");
		Patient patient10 = new Patient("Bailey", "Piers", LocalDate.of(1959, 06, 28), "F",
				"745 West Valley Farms Drive", "387-866-1399");
		patientService.addOrUpdatePatient(patient1);
		patientService.addOrUpdatePatient(patient2);
		patientService.addOrUpdatePatient(patient3);
		patientService.addOrUpdatePatient(patient4);
		patientService.addOrUpdatePatient(patient5);
		patientService.addOrUpdatePatient(patient6);
		patientService.addOrUpdatePatient(patient7);
		patientService.addOrUpdatePatient(patient8);
		patientService.addOrUpdatePatient(patient9);
		patientService.addOrUpdatePatient(patient10);
	}

}
