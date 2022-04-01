package com.systable.session;

public final class UserSession {
	
	private UserSession instance;
	private static int id;
	
	public UserSession(int id) {
		UserSession.id = id;
	}
	
	public UserSession getInstance(int id) {
		if (instance == null) {
			instance = new UserSession(id);
		}
		return instance;
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		UserSession.id = id;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
