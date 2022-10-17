package com.client.clinique.abernathy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ListPatientsNonTrouvableException extends RuntimeException {

	public ListPatientsNonTrouvableException(String s) {
		super(s);
	}

}
