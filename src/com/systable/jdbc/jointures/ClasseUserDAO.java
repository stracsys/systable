package com.systable.jdbc.jointures;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.systable.entities.Classe;
import com.systable.entities.User;
import com.systable.entities.jointures.ClasseUser;
import com.systable.exceptions.UMSException;
import com.systable.jdbc.DBManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClasseUserDAO {
public static ObservableList<ClasseUser> getClassesUsers() throws UMSException {
		ObservableList<ClasseUser> classesUsers = FXCollections.observableArrayList();
		try (Connection conn = DBManager.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT classes.id, classes.name, users.id, users.first_name, users.last_name, users.email FROM classes LEFT JOIN users ON classes.id_chief = users.id;");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int index = 0;
				Classe c = new Classe();
				c.setId(rs.getInt(++index));
				c.setName(rs.getString(++index));
				
				User u = new User();
				u.setId(rs.getInt(++index));
				u.setFirstName(rs.getString(++index));
				u.setLastName(rs.getString(++index));
				u.setEmail(rs.getString(++index));

				ClasseUser cu = new ClasseUser(c, u);
				classesUsers.add(cu);
			}

		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return classesUsers;
	}
}
