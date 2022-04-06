package com.systable.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import com.systable.entities.Classe;
import com.systable.exceptions.UMSException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClasseDAO {
	public static int addClasse(Classe classe) throws UMSException {
		int status = 0;
		try (Connection conn = DBManager.getConnection()) {

			String query = "INSERT INTO classes (name) values (?)";
			PreparedStatement ps = conn.prepareStatement(query);

			int index = 0;
			ps.setString(++index, classe.getName());

			status = ps.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println(e.getMessage());
			if (e.getMessage().contains("id"))
				status = -1;
			else if (e.getMessage().contains("name"))
				status = -2;
			else if (e.getMessage().contains("id_chief"))
				status = -3;
			else
				System.err.println(e.getMessage());
		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return status;
	}

	public static int updateClasse(Classe classe) throws UMSException {
		int status = 0;
		try (Connection conn = DBManager.getConnection()) {

			String query = "UPDATE classes SET name=?, id_chief=? WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(query);

			int index = 0;
			ps.setString(++index, classe.getName());
			ps.setInt(++index, classe.getIdChief());
			ps.setInt(++index, classe.getId());

			status = ps.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			if (e.getMessage().contains("id_chief"))
				status = -1;
		} 
		catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return status;
	}

	public static int getMaxIdByClasse() throws UMSException {
		int id = 0;
		try (Connection conn = DBManager.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT MAX( id ) FROM classes");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return id;
	}

	public static Classe getClasseById(int id) throws UMSException {
		Classe c = new Classe();
		try (Connection conn = DBManager.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM classes WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int index = 0;
				c = new Classe(rs.getInt(++index), rs.getString(++index), rs.getInt(++index));
			}

		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return c;
	}

	public static ObservableList<Classe> getClasses() throws UMSException {
		ObservableList<Classe> classes = FXCollections.observableArrayList();
		try (Connection conn = DBManager.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM classes");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int index = 0;
				Classe c = new Classe(rs.getInt(++index), rs.getString(++index), rs.getInt(++index));
				classes.add(c);
			}

		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return classes;
	}
}
