package com.client.clinique.abernathy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableFeignClients
public class AbernathyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbernathyApplication.class, args);
	}

}
