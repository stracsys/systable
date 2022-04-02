package com.systable.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import com.systable.entities.Modules;
import com.systable.exceptions.UMSException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModuleDAO {

	public static int addModule(Modules modules) throws UMSException {
		int status = 0;
		try (Connection connection = DBManager.getConnection()) {

			String query = "INSERT INTO modules (name, hours_total, id_class) values (?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			int index = 0;
			preparedStatement.setString(++index, modules.getName());
			preparedStatement.setInt(++index, modules.getHoursTotal());
			preparedStatement.setInt(++index, modules.getIdClasse());

			status = preparedStatement.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			if (e.getMessage().contains("name"))
				status = -1;
			else {
				System.err.println(e.getMessage());
			}
		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return status;
	}

	public static ObservableList<Modules> getModules() throws UMSException {
		ObservableList<Modules> modules = FXCollections.observableArrayList();
		try (Connection conn = DBManager.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM modules");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int index = 0;
				Modules m = new Modules(
						rs.getInt(++index),
						rs.getString(++index),
						rs.getInt(++index),
						rs.getInt(++index),
						rs.getInt(++index),
						rs.getBoolean(++index),
						rs.getInt(++index),
						rs.getInt(++index)
						);
				modules.add(m);
			}

		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return modules;
	}

	public static int updateModule(Modules modules) throws UMSException {
		int status = 0;
		try (Connection connection = DBManager.getConnection()) {

			String query = "UPDATE modules SET name=?, hours_dispense=?, hours_remaining=?, hours_total=?, is_close=?, id_class, id_teacher=?, where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			int index = 0;
			preparedStatement.setString(++index, modules.getName());
			preparedStatement.setInt(++index, modules.getHoursDispense());
			preparedStatement.setInt(++index, modules.getHoursRemaining());
			preparedStatement.setInt(++index, modules.getHoursTotal());
			preparedStatement.setBoolean(++index, modules.isClose());
			preparedStatement.setInt(++index, modules.getIdClasse());
			preparedStatement.setInt(++index, modules.getIdTeacher());
			preparedStatement.setInt(++index, modules.getId());

			status = preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return status;
	}
}
