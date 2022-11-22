package com.client.clinique.rapportDiabete.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rapport")
public class RapportDiabete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column
	private String termeDeclencheur;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTermeDeclencheur() {
		return termeDeclencheur;
	}

	public void setTermeDeclencheur(String termeDeclencheur) {
		this.termeDeclencheur = termeDeclencheur;
	}

	public RapportDiabete(Integer id, String termeDeclencheur) {
		super();
		this.id = id;
		this.termeDeclencheur = termeDeclencheur;
	}

	public RapportDiabete(String termeDeclencheur) {
		super();
		this.termeDeclencheur = termeDeclencheur;
	}

	public RapportDiabete() {
		super();
		// TODO Auto-generated constructor stub
	}

}
