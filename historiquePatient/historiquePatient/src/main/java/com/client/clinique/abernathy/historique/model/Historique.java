package com.client.clinique.abernathy.historique.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "historique")
public class Historique {

	@Id
	private String id;

	private Integer idPatient;

	private String note;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(Integer idPatient) {
		this.idPatient = idPatient;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Historique() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Historique(String id, String note) {
		super();
		this.id = id;
		this.note = note;
	}

	public Historique(Integer idPatient, String note) {
		super();
		this.idPatient = idPatient;
		this.note = note;
	}

	public Historique(String note) {
		super();
		this.note = note;
	}

//	@Override
	public String toString() {
		return "HistoriquePatient [id=" + id + ", idPatient=" + idPatient + ", note=" + note + "]";
	}

}
