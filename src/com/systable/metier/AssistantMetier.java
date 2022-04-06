package com.systable.metier;

import com.systable.data.ClasseDataSource;
import com.systable.data.CourseDataSource;
import com.systable.data.ModuleDataSource;
import com.systable.data.TimeTableDataSource;
import com.systable.data.UserDataSource;
import com.systable.data.jointures.ClasseUserDataSource;
import com.systable.entities.Classe;
import com.systable.entities.Course;
import com.systable.entities.Modules;
import com.systable.entities.TimeTable;
import com.systable.entities.User;
import com.systable.entities.jointures.ClasseUser;
import com.systable.exceptions.UMSException;

import javafx.collections.ObservableList;

public class AssistantMetier {

	private static UserDataSource userDataSource = new UserDataSource();
	private static TimeTableDataSource timeTableDataSource = new TimeTableDataSource();
	private static ModuleDataSource moduleDataSource = new ModuleDataSource();
	private static ClasseDataSource classeDataSource = new ClasseDataSource();
	private static CourseDataSource courseDataSource = new CourseDataSource();
	private static ClasseUserDataSource classeUserDataSource = new ClasseUserDataSource();

	public static ObservableList<User> listUsers() {
		return userDataSource.getUsers();
	}

	public static ObservableList<User> listUsers(String attribute, String value) throws UMSException {
		userDataSource.getUsersByAttribute(attribute, value);
		return userDataSource.getUsers();
	}

	public static ObservableList<ClasseUser> listClasseUsers() {
		return classeUserDataSource.getClassesUsers();
	}

	public static ObservableList<Course> listCourses() {
		return courseDataSource.getCourses();
	}

	public static ObservableList<TimeTable> listTimeTables() {
		return timeTableDataSource.getTimeTables();
	}

	public static ObservableList<Modules> listModule() {
		return moduleDataSource.getModules();
	}

	public static ObservableList<Classe> listClasses() {
		return classeDataSource.getClasses();
	}

	public static int addCourse(Course course) throws UMSException {
		return courseDataSource.addCourse(course);
	}

	public static int addTimeTable(TimeTable timeTable) throws UMSException {
		return timeTableDataSource.addTimeTable(timeTable);
	}

	public static int addModule(Modules module) throws UMSException {
		return moduleDataSource.addModule(module);
	}

	public static int addClasse(Classe classe) throws UMSException {
		return classeDataSource.addClasse(classe);
	}

	public static int updateClasse(Classe classe, int selectedClasseIndex) throws UMSException {
		return classeDataSource.updateClasse(classe, selectedClasseIndex);
	}

	public static int updateModules(Modules module, int selectedModuleIndex) throws UMSException {
		return moduleDataSource.updateModule(module, selectedModuleIndex);
	}
}