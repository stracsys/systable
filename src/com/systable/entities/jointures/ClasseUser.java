package com.systable.entities.jointures;

import com.systable.entities.Classe;
import com.systable.entities.User;

public class ClasseUser {
	
	private Classe classe;
	private User chief;
	
	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public User getChief() {
		return chief;
	}

	public void setChief(User chief) {
		this.chief = chief;
	}

	private int idClasse;
	private String nameClasse;
	private int idChief;
	private String firstNameChief;
	private String lastNameChief;
	private String emailChief;
	
	public ClasseUser() {
	}

	public ClasseUser(Classe classe, User user) {
		this.classe = classe;
		this.chief = user;
		this.idClasse = classe.getId();
		this.nameClasse = classe.getName();
		this.idChief = user.getId();
		this.firstNameChief = user.getFirstName();
		this.lastNameChief = user.getLastName();
		this.emailChief = user.getEmail();
	}

	public ClasseUser(Classe classe) {
		this.idClasse = classe.getId();
		this.nameClasse = classe.getName();
	}

	public int getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(int idClasse) {
		this.idClasse = idClasse;
	}

	public String getNameClasse() {
		return nameClasse;
	}

	public void setNameClasse(String nameClasse) {
		this.nameClasse = nameClasse;
	}

	public int getIdChief() {
		return idChief;
	}

	public void setIdChief(int idChief) {
		this.idChief = idChief;
	}

	public String getFirstNameChief() {
		return firstNameChief;
	}

	public void setFirstNameChief(String firstNameChief) {
		this.firstNameChief = firstNameChief;
	}

	public String getLastNameChief() {
		return lastNameChief;
	}

	public void setLastNameChief(String lastNameChief) {
		this.lastNameChief = lastNameChief;
	}

	public String getEmailChief() {
		return emailChief;
	}

	public void setEmailChief(String emailChief) {
		this.emailChief = emailChief;
	}
}
