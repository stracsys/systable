package com.systable.entities;

import java.time.LocalDate;

public class TimeTable {
	
	private int id;
	private LocalDate start;
	private LocalDate end;
	
	private static int idAssigner;
	
	public TimeTable() {
	}
	
	public TimeTable(LocalDate start) {
		this.start = start;
	}

	public TimeTable(int id, LocalDate start, LocalDate end) {
		this(start);
		this.id = id;
		this.end = end;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public static int getIdAssigner() {
		return idAssigner;
	}

	public static void setIdAssigner(int idAssigner) {
		TimeTable.idAssigner = idAssigner;
	}
}
