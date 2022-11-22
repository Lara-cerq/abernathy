package com.client.clientui.beans;

public class TermesBean {

	private Integer id;

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

	public TermesBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TermesBean(String termeDeclencheur) {
		super();
		this.termeDeclencheur = termeDeclencheur;
	}

}
