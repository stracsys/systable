package com.systable.data.jointures;

import com.systable.entities.jointures.ClasseUser;
import com.systable.exceptions.UMSException;
import com.systable.jdbc.jointures.ClasseUserDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClasseUserDataSource {
	private ObservableList<ClasseUser> classeUsers;

	public ClasseUserDataSource() {
		
		try {
			listClasseUsers();
		} catch (UMSException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<ClasseUser> getClassesUsers() {
		return classeUsers;
	}

	private void listClasseUsers() throws UMSException {
		classeUsers = FXCollections.observableArrayList();
		classeUsers = ClasseUserDAO.getClassesUsers();
	}
}
