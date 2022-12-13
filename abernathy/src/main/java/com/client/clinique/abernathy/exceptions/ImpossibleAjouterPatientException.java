package com.client.clinique.abernathy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ImpossibleAjouterPatientException extends Exception {

	public ImpossibleAjouterPatientException(String s) {
		super(s);
	}
}
