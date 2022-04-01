package com.systable.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.systable.entities.User;
import com.systable.exceptions.UMSException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDAO {

	public static int addUser(User user) throws UMSException {
		int status = 0;
		try (Connection connection = DBManager.getConnection()) {

			String query = "INSERT INTO users (login, password, first_name, last_name, phone_code, phone_number, email, address, profile) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			int index = 0;
			preparedStatement.setString(++index, user.getLogin());
			preparedStatement.setString(++index, user.getPassword());
			preparedStatement.setString(++index, user.getFirstName());
			preparedStatement.setString(++index, user.getLastName());
			preparedStatement.setInt(++index, user.getPhoneCode());
			preparedStatement.setInt(++index, user.getPhoneNumber());
			preparedStatement.setString(++index, user.getEmail());
			preparedStatement.setString(++index, user.getAddress());
			preparedStatement.setString(++index, user.getProfile().toString());

			status = preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}
		
		return status;
	}

	public static int signIn(String login, String password) throws UMSException {
		int status = 0;
		try (Connection conn = DBManager.getConnection()) {

			String query = "SELECT count(*) FROM users WHERE login = ? and password = ?";
			PreparedStatement ps = conn.prepareStatement(query);

			int index = 0;
			ps.setString(++index, login);
			ps.setString(++index, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) 
				status = rs.getInt(1);
			
		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}
		
		return status;
	}

	public static User getUserById(int id) throws UMSException {
		User u = new User();
		try (Connection conn = DBManager.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int index = 0;
				u = new User(
						rs.getInt(++index),
						rs.getString(++index),
						rs.getString(++index),
						rs.getString(++index),
						rs.getString(++index),
						rs.getInt(++index),
						rs.getInt(++index),
						rs.getString(++index),
						rs.getString(++index),
						rs.getString(++index)
						);
			}

		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return u;
	}
	
	public static User getUserByLogin(String login) throws UMSException {
		User u = new User();
		try (Connection conn = DBManager.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE login = ?");
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int index = 0;
				u = new User(
						rs.getInt(++index),
						rs.getString(++index),
						rs.getString(++index),
						rs.getString(++index),
						rs.getString(++index),
						rs.getInt(++index),
						rs.getInt(++index),
						rs.getString(++index),
						rs.getString(++index),
						rs.getString(++index)
						);
			}

		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return u;
	}
	
	public static ObservableList<User> getUsers() throws UMSException {
		ObservableList<User> users = FXCollections.observableArrayList();
		try (Connection conn = DBManager.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM users");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int index = 0;
				User u = new User(
						rs.getInt(++index),
						rs.getString(++index),
						rs.getString(++index),
						rs.getString(++index),
						rs.getString(++index),
						rs.getInt(++index),
						rs.getInt(++index),
						rs.getString(++index),
						rs.getString(++index),
						rs.getString(++index)
						);
				users.add(u);
			}

		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return users;
	}
	
	public static int deleteUser(User user) throws UMSException {
		int status = 0;
		
		try (Connection conn = DBManager.getConnection()) {
			String query = "DELETE FROM users WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, user.getId());
			status = ps.executeUpdate();

		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return status;
	}
	
	public static int updateUser(User user) throws UMSException {
		int status = 0;
		try (Connection connection = DBManager.getConnection()) {

			String query = "UPDATE users SET login=?, password=?, first_name=?, last_name=?, phone_code=?, phone_number=?, email=?, address=? where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			int index = 0;
			preparedStatement.setString(++index, user.getLogin());
			preparedStatement.setString(++index, user.getPassword());
			preparedStatement.setString(++index, user.getFirstName());
			preparedStatement.setString(++index, user.getLastName());
			preparedStatement.setInt(++index, user.getPhoneCode());
			preparedStatement.setInt(++index, user.getPhoneNumber());
			preparedStatement.setString(++index, user.getEmail());
			preparedStatement.setString(++index, user.getAddress());
			preparedStatement.setInt(++index, user.getId());

			status = preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}
		
		return status;
	}
}
