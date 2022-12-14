package com.client.clinique.abernathy.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPatient")
	private Integer idPatient;

	@Column(name = "nom")
	@NotBlank
	private String nom;

	@Column(name = "prenom")
	@NotBlank
	private String prenom;

	@Column(name = "dateNaissance")
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateNaissance;

	@Column(name = "genre")
	@Length(max = 1)
	@NotBlank
	private String genre;

	@Column(name = "adresse")
	private String adresse;

	@Column(name = "numeroTelephone")
	private String numeroTelephone;

	public Integer getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(Integer idPatient) {
		this.idPatient = idPatient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getNumeroTelephone() {
		return numeroTelephone;
	}

	public void setNumeroTelephone(String numeroTelephone) {
		this.numeroTelephone = numeroTelephone;
	}

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patient(Integer idPatient, @NotBlank String nom, @NotBlank String prenom, @Past LocalDate dateNaissance,
			@Length(max = 1) @NotBlank String genre, String adresse, String numeroTelephone) {
		super();
		this.idPatient = idPatient;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.genre = genre;
		this.adresse = adresse;
		this.numeroTelephone = numeroTelephone;
	}

	public Patient(@NotBlank String nom, @NotBlank String prenom, @Past LocalDate dateNaissance,
			@Length(max = 1) @NotBlank String genre, String adresse, String numeroTelephone) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.genre = genre;
		this.adresse = adresse;
		this.numeroTelephone = numeroTelephone;
	}

}
