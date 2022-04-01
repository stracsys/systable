package com.systable.enumeration;

public enum Profile {
	ADMIN("ADMIN"),
	ASSISTANT("ASSISTANT"),
	CHIEF("CHIEF"),
	RESPONSIBLE("RESPONSIBLE"),
	TEACHER("TEACHER"),
	ACCOUNTANT("ACCOUNTANT");
	
	private final String label;
	
	Profile(String label){
		this.label = label;
	}

	@Override
	public String toString() {
		return label;
	}
}
