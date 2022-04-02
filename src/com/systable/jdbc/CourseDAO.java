package com.systable.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

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

	public static Course getCourseById(int id) throws UMSException {
		Course u = new Course();
		try (Connection conn = DBManager.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM courses WHERE login = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int index = 0;
				u = new Course(
						rs.getInt(++index),
						rs.getString(++index),
						rs.getString(++index),
						rs.getString(++index),
						rs.getString(++index),
						rs.getInt(++index),
						rs.getInt(++index),
						rs.getString(++index),
						rs.getString(++index), rs.getString(++index));
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
				Course u = new Course(rs.getInt(++index), rs.getString(++index), rs.getString(++index),
						rs.getString(++index), rs.getString(++index), rs.getInt(++index), rs.getInt(++index),
						rs.getString(++index), rs.getString(++index), rs.getString(++index));
				courses.add(u);
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

			String query = "UPDATE courses SET login=?, password=?, first_name=?, last_name=?, phone_code=?, phone_number=?, email=?, address=? where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			int index = 0;
			preparedStatement.setString(++index, course.getLogin());
			preparedStatement.setString(++index, course.getPassword());
			preparedStatement.setString(++index, course.getFirstName());
			preparedStatement.setString(++index, course.getLastName());
			preparedStatement.setInt(++index, course.getPhoneCode());
			preparedStatement.setInt(++index, course.getPhoneNumber());
			preparedStatement.setString(++index, course.getEmail());
			preparedStatement.setString(++index, course.getAddress());
			preparedStatement.setInt(++index, course.getId());

			status = preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw new UMSException(e.getClass() + ";" + e.getMessage());
		}

		return status;
	}
}
