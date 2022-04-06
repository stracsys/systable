package com.systable.data;

import com.systable.entities.Course;
import com.systable.enumeration.Profile;
import com.systable.exceptions.UMSException;
import com.systable.jdbc.CourseDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CourseDataSource {
	private ObservableList<Course> courses;

	public CourseDataSource() {

		try {
			listCourses();
		} catch (UMSException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Course> getCourses() {
		return courses;
	}

	private void listCourses() throws UMSException {
		courses = FXCollections.observableArrayList();
		courses = CourseDAO.getCourses();
	}

	public int getMaxIdByCourse() throws UMSException {
		return CourseDAO.getMaxIdByCourse();
	}

//	public void getCoursesByAttribute(String attribute, String value) throws UMSException {
//		courses = CourseDAO.getCoursesByAttribute(attribute, value);
//	}

	public int addCourse(Course course) throws UMSException {

		int status = CourseDAO.addCourse(course);

		if (status > 0) {
			int id = CourseDAO.getMaxIdByCourse();
			course.setId(id);
		}

		return status;
	}

	public int deleteCourse(Course course) throws UMSException {
		int status = CourseDAO.deleteCourse(course);

		if (status > 0)
			courses.remove(course);

		return status;
	}

//	public int updateCourse(Course course) throws UMSException {
//		return CourseDAO.updateCourse(course);
//
//	}

	public int updateCourse(Course course, int selectedCourseIndex) throws UMSException {
		int status = CourseDAO.updateCourse(course);

		if (status > 0) {
			courses.remove(selectedCourseIndex);
			courses.add(selectedCourseIndex, course);
		}

		return status;
	}

}
