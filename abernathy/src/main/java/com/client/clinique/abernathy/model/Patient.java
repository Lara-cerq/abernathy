package com.client.clinique.abernathy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

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
	private Date dateNaissance;

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

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
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

	public Patient(Integer idPatient, @NotNull String nom, @NotNull String prenom, @Past Date dateNaissance,
			@Length(max = 1) String genre, @NotNull String adresse,
			String numeroTelephone) {
		super();
		this.idPatient = idPatient;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.genre = genre;
		this.adresse = adresse;
		this.numeroTelephone = numeroTelephone;
	}

	public Patient(@NotNull String nom, @NotNull String prenom, @Past Date dateNaissance, @Length(max = 1) String genre,
			@NotNull String adresse, String numeroTelephone) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.genre = genre;
		this.adresse = adresse;
		this.numeroTelephone = numeroTelephone;
	}

}
