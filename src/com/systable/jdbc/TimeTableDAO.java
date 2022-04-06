package com.systable.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;

import com.systable.entities.TimeTable;
import com.systable.exceptions.UMSException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TimeTableDAO {
	public static int addTimeTable(TimeTable timeTable) throws UMSException {
		int status = 0;
		try (Connection conn = DBManager.getConnection()) {

			String query = "INSERT INTO timetables (start, end) values (?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);

			int index = 0;
			ps.setString(++index, timeTable.getStart().toString());
			ps.setString(++index, timeTable.getEnd().toString());

			status = ps.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			if (e.getMessage().contains("start"))
				status = -1;
			else if (e.getMessage().contains("end"))
				status = -2;
			else
				System.err.println(e.getMessage());
		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return status;
	}

	public static int getMaxIdByTimeTable() throws UMSException {
		int id = 0;
		try (Connection conn = DBManager.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT MAX( id ) FROM timetables");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return id;
	}

	public static TimeTable getTimeTableById(int id) throws UMSException {
		TimeTable t = new TimeTable();
		try (Connection conn = DBManager.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM timeTables WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int index = 0;
				t = new TimeTable(rs.getInt(++index), LocalDate.parse(rs.getString(++index)),
						LocalDate.parse(rs.getString(++index)));
			}

		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return t;
	}

	public static ObservableList<TimeTable> getTimeTables() throws UMSException {
		ObservableList<TimeTable> timetables = FXCollections.observableArrayList();
		try (Connection conn = DBManager.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM timetables");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int index = 0;

				int id = rs.getInt(++index);
				LocalDate startDate = LocalDate.parse(rs.getString(++index));
				LocalDate endDate = LocalDate.parse(rs.getString(++index));

				TimeTable t = new TimeTable(id, startDate, endDate);
				timetables.add(t);
			}

		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return timetables;
	}

}
