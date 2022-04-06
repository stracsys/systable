package com.systable.session;

import com.systable.entities.User;

public final class UserSession {
	
	private static UserSession instance;
	
	private static User user;
	
	public UserSession(User user) {
		UserSession.user = user;
	}
	
	public static UserSession getInstance(User user) {
		if (instance == null) {
			instance = new UserSession(user);
		}
		return instance;
	}

	public static User getUser() {
		return user;
	}
	
	public static void setUser(User user) {
		UserSession.user = user;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	public static void logOut() {
		user = null;
		instance = null;
	}
}
