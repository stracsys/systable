package com.systable.entities;

public class Modules {
	
	private int id;
	
	private String name;
	private int hoursDispense;
	private int hoursRemaining;
	private int hoursTotal;
	private boolean isClose;
	private int idClasse;
	private int idTeacher;
	
	public Modules() {
	}
	
	public Modules(String name, int hoursTotal, int idClasse) {
		this.name = name;
		this.hoursTotal = hoursTotal;
		this.idClasse = idClasse;
	}

	public Modules(int id, String name, int hoursDispense, int hoursRemaining, int hoursTotal, boolean isClose,
			int idClasse, int idTeacher) {
		this(name, hoursTotal, idClasse);
		this.id = id;
		this.hoursDispense = hoursDispense;
		this.hoursRemaining = hoursRemaining;
		this.isClose = isClose;
		this.idClasse = idClasse;
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

	public int getHoursDispense() {
		return hoursDispense;
	}

	public void setHoursDispense(int hoursDispense) {
		this.hoursDispense = hoursDispense;
	}

	public int getHoursRemaining() {
		return hoursRemaining;
	}

	public void setHoursRemaining(int hoursRemaining) {
		this.hoursRemaining = hoursRemaining;
	}

	public int getHoursTotal() {
		return hoursTotal;
	}

	public void setHoursTotal(int hoursTotal) {
		this.hoursTotal = hoursTotal;
	}

	public boolean isClose() {
		return isClose;
	}

	public void setClose(boolean isClose) {
		this.isClose = isClose;
	}

	public int getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(int idClasse) {
		this.idClasse = idClasse;
	}

	public int getIdTeacher() {
		return idTeacher;
	}

	public void setIdTeacher(int idTeacher) {
		this.idTeacher = idTeacher;
	}
}
