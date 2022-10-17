package com.client.clinique.abernathy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ImpossibleModifierPatientException extends Exception {

	public ImpossibleModifierPatientException(String s) {
		super(s);
	}

}
