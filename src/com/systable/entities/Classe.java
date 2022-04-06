package com.systable.entities;

public class Classe {
	
	private int id;
	private String name;
	private int idChief;
	
	public Classe() {
	}
	
	public Classe (String name) {
		this.name = name;
	}
	
	public Classe(int id, String name, int idChief) {
		this.id = id;
		this.name = name;
		this.idChief = idChief;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdChief() {
		return idChief;
	}

	public void setIdChief(int idChief) {
		this.idChief = idChief;
	}
}
