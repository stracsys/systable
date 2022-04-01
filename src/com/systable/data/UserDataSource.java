package com.systable.data;


import com.systable.entities.User;
import com.systable.enumeration.Profile;
import com.systable.exceptions.UMSException;
import com.systable.jdbc.UserDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDataSource {
	private ObservableList<User> allUsers;
	private ObservableList<User> users;

	public UserDataSource() {
		this.users = FXCollections.observableArrayList();
		
		int lastId = -1;

		try {
			listAllUsers();

			int lastIndex = allUsers.size() - 1;
			lastId = allUsers.get(lastIndex).getId();
			User.setIdAssigners(lastId);

			getUsersByProfile(allUsers.get(0).getProfile());;
		} catch (UMSException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<User> getUsers() {
		return users;
	}

	public void getUsersByProfile(Profile profile) throws UMSException {
		users.clear();

		for (User user : allUsers) {
			if (user.getProfile() == profile) {
				users.add(user);
			}
		}
	}

	public void getUsersByAttribute(String attribute, String value) throws UMSException {
		users.clear();
		
		value = value.toLowerCase();

		for (User user : allUsers) {
			switch (attribute) {
			case "First name":
				if (user.getFirstName().toLowerCase().contains(value))
					users.add(user);

				break;
			case "Last name":
				if (user.getLastName().toLowerCase().contains(value))
					users.add(user);

				break;

			default:
				break;
			}
		}
	}

	public int addUser(User user) throws UMSException {

		int status = UserDAO.addUser(user);

		if (status < 0)
			return -1;

		int id = User.getIdAssigners() + 1;
		User.setIdAssigners(id);

		user.setId(id);
		this.allUsers.add(user);

		return status;
	}

	private void listAllUsers() throws UMSException {
		allUsers = UserDAO.getUsers();
	}

	public int deleteUser(User user) throws UMSException {
		int status = UserDAO.deleteUser(user);

		if (status < 0)
			return -1;

		this.allUsers.remove(user);

		return status;
	}

	public int updateUser(User user) throws UMSException {
		int status = UserDAO.updateUser(user);

		if (status < 0)
			return -1;

		return status;
	}

	public int updateUser(User user, int selectedUserIndex) throws UMSException {
		int status = UserDAO.updateUser(user);

		if (status < 0)
			return -1;

		this.allUsers.remove(selectedUserIndex);
		this.allUsers.add(selectedUserIndex, user);

		return status;
	}

}
