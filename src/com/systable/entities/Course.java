package com.systable.entities;

import java.time.LocalDate;

public class Course {
	
	private int id;
	
	private int startHours;
	private int duration;
	private int endHours;
	private boolean isDispense;
	private boolean isModify;
	private boolean isValid;
	private boolean isPayed;
	private String content;
	private LocalDate date;
	private int idModule;
	private int idTimeTable;
	
	private static int idAssigner;

	public Course() {
	}

	public Course(int startHours, int duration, LocalDate date, int idModule) {
		this.startHours = startHours;
		this.duration = duration;
		this.date = date;
		this.idModule = idModule;
	}

	public Course(int id, int startHours, int duration, int endHours, boolean isDispense, boolean isModify,
			boolean isValid, boolean isPayed, String content, LocalDate date, int idModule, int idTimeTable) {
		this(startHours, duration, date, idModule);
		this.id = id;
		this.endHours = endHours;
		this.isDispense = isDispense;
		this.isModify = isModify;
		this.isValid = isValid;
		this.isPayed = isPayed;
		this.content = content;
		this.idTimeTable = idTimeTable;
	}

	public static int getIdAssigner() {
		return idAssigner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStartHours() {
		return startHours;
	}

	public void setStartHours(int startHours) {
		this.startHours = startHours;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getEndHours() {
		return endHours;
	}

	public void setEndHours(int endHours) {
		this.endHours = endHours;
	}

	public boolean isDispense() {
		return isDispense;
	}

	public void setDispense(boolean isDispense) {
		this.isDispense = isDispense;
	}

	public boolean isModify() {
		return isModify;
	}

	public void setModify(boolean isModify) {
		this.isModify = isModify;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean isPayed() {
		return isPayed;
	}

	public void setPayed(boolean isPayed) {
		this.isPayed = isPayed;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getIdModule() {
		return idModule;
	}

	public void setIdModule(int idModule) {
		this.idModule = idModule;
	}

	public int getIdTimeTable() {
		return idTimeTable;
	}

	public void setIdTimeTable(int idTimeTable) {
		this.idTimeTable = idTimeTable;
	}

	public static void setIdAssigner(int idAssigner) {
		Course.idAssigner = idAssigner;
	}
}
