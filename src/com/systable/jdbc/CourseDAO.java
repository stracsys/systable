package com.systable.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;

import com.systable.entities.Course;
import com.systable.exceptions.UMSException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CourseDAO {

	public static int addCourse(Course course) throws UMSException {
		int status = 0;
		try (Connection connection = DBManager.getConnection()) {

			String query = "INSERT INTO courses (start_hours, duration, date, id_module) values (?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			int index = 0;
			preparedStatement.setInt(++index, course.getStartHours());
			preparedStatement.setInt(++index, course.getDuration());
			preparedStatement.setString(++index, course.getDate().toString());
			preparedStatement.setInt(++index, course.getIdModule());

			status = preparedStatement.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			if (e.getMessage().contains("start_hours"))
				status = -1;
			else {
				System.err.println(e.getMessage());
			}
		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return status;
	}

	public static int getMaxIdByCourse() throws UMSException {
		int id = 0;
		try (Connection conn = DBManager.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT MAX( id ) FROM courses");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return id;
	}

	public static Course getCourseById(int id) throws UMSException {
		Course u = new Course();
		try (Connection conn = DBManager.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM courses WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int index = 0;
				u = new Course(rs.getInt(++index), rs.getInt(++index), rs.getInt(++index), rs.getInt(++index),
						rs.getBoolean(++index), rs.getBoolean(++index), rs.getBoolean(++index), rs.getBoolean(++index),
						rs.getString(++index), LocalDate.parse(rs.getString(++index)), rs.getInt(++index),
						rs.getInt(++index));

			}

		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return u;
	}

	public static ObservableList<Course> getCourses() throws UMSException {
		ObservableList<Course> courses = FXCollections.observableArrayList();
		try (Connection conn = DBManager.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM courses");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int index = 0;
				Course c = new Course(rs.getInt(++index), rs.getInt(++index), rs.getInt(++index), rs.getInt(++index),
						rs.getBoolean(++index), rs.getBoolean(++index), rs.getBoolean(++index), rs.getBoolean(++index),
						rs.getString(++index), LocalDate.parse(rs.getString(++index)), rs.getInt(++index),
						rs.getInt(++index));
				courses.add(c);
			}

		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return courses;
	}

	public static int deleteCourse(Course course) throws UMSException {
		int status = 0;

		try (Connection conn = DBManager.getConnection()) {
			String query = "DELETE FROM courses WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, course.getId());
			status = ps.executeUpdate();

		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return status;
	}

	public static int updateCourse(Course course) throws UMSException {
		int status = 0;
		try (Connection connection = DBManager.getConnection()) {

			String query = "UPDATE courses SET start_hours=?, duration=?, end_hours=?, is_dispense=?, is_modify=?, is_valid=?, is_payed=? content=?, date=?, id_module=?, id_timetable=? where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			int index = 0;
			preparedStatement.setInt(++index, course.getStartHours());
			preparedStatement.setInt(++index, course.getDuration());
			preparedStatement.setInt(++index, course.getEndHours());
			preparedStatement.setBoolean(++index, course.isDispense());
			preparedStatement.setBoolean(++index, course.isModify());
			preparedStatement.setBoolean(++index, course.isValid());
			preparedStatement.setBoolean(++index, course.isPayed());
			preparedStatement.setString(++index, course.getContent());
			preparedStatement.setString(++index, course.getDate().toString());
			preparedStatement.setInt(++index, course.getIdModule());
			preparedStatement.setInt(++index, course.getIdTimeTable());
			preparedStatement.setInt(++index, course.getId());

			status = preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return status;
	}
}
