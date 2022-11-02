package com.client.clinique.abernathy.historiquePatient.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "historique")
public class HistoriquePatient {

	@Id
	private String id;

//	private Patient patient;

	private String note;

	private List<String> notes;

//	public Patient getPatient() {
//		return patient;
//	}
//
//	public void setPatient(Patient patient) {
//		this.patient = patient;
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getNotes() {
		return notes;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setNotes(List<String> notes) {
		this.notes = notes;
	}

	public HistoriquePatient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HistoriquePatient(String id, List<String> notes) {
		super();
		this.id = id;
		this.notes = notes;
	}

	public HistoriquePatient(List<String> notes) {
		super();
		this.notes = notes;
	}

	public HistoriquePatient(String id, String note, List<String> notes) {
		super();
		this.id = id;
		this.note = note;
		this.notes = notes;
	}

	// @Override
	public String toString() {
		return "HistoriquePatient [id=" + id + ", notes=" + notes + "]";
	}

//	public Historique(Patient patient, String notes) {
//		super();
//		this.patient = patient;
//		this.notes = notes;
//	}

}
