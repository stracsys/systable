package com.systable.metier;

import com.systable.data.UserDataSource;
import com.systable.entities.User;
import com.systable.enumeration.Profile;
import com.systable.exceptions.UMSException;

import javafx.collections.ObservableList;

public class AdminMetier {

	private static UserDataSource userDataSource = new UserDataSource();

	public static int addUser(User user) throws UMSException {
		return userDataSource.addUser(user);
	}

	public static ObservableList<User> listUsers() {
		return userDataSource.getUsers();
	}
	
	public static void listUsersByProfile(Profile profile) throws UMSException {
		userDataSource.getUsersByProfile(profile);
	}
	
	public static void listUsersByAttributes(String attribute, String value) throws UMSException {
		userDataSource.getUsersByAttribute(attribute, value);
	}

	public static int deleteUser(User user) throws UMSException {
		return userDataSource.deleteUser(user);
	}
	
	public static int updateUser(User user) throws UMSException {
		return userDataSource.updateUser(user);
	}

	public static int updateUser(User user, int selectedUserIndex) throws UMSException {
		return userDataSource.updateUser(user, selectedUserIndex);
	}
}
