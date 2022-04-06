package com.systable.data;

import com.systable.entities.User;
import com.systable.enumeration.Profile;
import com.systable.exceptions.UMSException;
import com.systable.jdbc.UserDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDataSource {
	private ObservableList<User> users;

	public UserDataSource() {

		try {
			listUsers();
		} catch (UMSException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<User> getUsers() {
		return users;
	}

	private void listUsers() throws UMSException {
		users = FXCollections.observableArrayList();
		users = UserDAO.getUsersByAttribute("profile", Profile.TEACHER.toString());
	}

	public int getMaxIdByUser() throws UMSException {
		return UserDAO.getMaxIdByUser();
	}

	public void getUsersByAttribute(String attribute, String value) throws UMSException {
		users = UserDAO.getUsersByAttribute(attribute, value);
	}

	public int addUser(User user) throws UMSException {

		int status = UserDAO.addUser(user);

		if (status > 0) {
			int id = UserDAO.getMaxIdByUser();
			user.setId(id);
		}

		return status;
	}

	public int deleteUser(User user) throws UMSException {
		int status = UserDAO.deleteUser(user);

		if (status > 0)
			users.remove(user);

		return status;
	}

	public int updateUser(User user) throws UMSException {
		return UserDAO.updateUser(user);
	}

	public int updateUser(User user, int selectedUserIndex) throws UMSException {
		int status = UserDAO.updateUser(user);

		if (status > 0) {
			users.remove(selectedUserIndex);
			users.add(selectedUserIndex, user);
		}

		return status;
	}

}
