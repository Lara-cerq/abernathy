package com.client.clinique.abernathy.historiquePatient.beans;

public class ResultatBean {

	private PatientBean patient;

	private HistoriqueBean historique;

	public PatientBean getPatient() {
		return patient;
	}

	public void setPatient(PatientBean patient) {
		this.patient = patient;
	}

	public HistoriqueBean getHistorique() {
		return historique;
	}

	public void setHistorique(HistoriqueBean historique) {
		this.historique = historique;
	}

	public ResultatBean(PatientBean patient, HistoriqueBean historique) {
		super();
		this.patient = patient;
		this.historique = historique;
	}

	public ResultatBean() {
		super();
		// TODO Auto-generated constructor stub
	}

}
