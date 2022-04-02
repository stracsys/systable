package com.systable.metier;

import com.systable.data.ClasseDataSource;
import com.systable.data.ModuleDataSource;
import com.systable.data.TimeTableDataSource;
import com.systable.data.UserDataSource;
import com.systable.entities.Classe;
import com.systable.entities.Modules;
import com.systable.entities.TimeTable;
import com.systable.entities.User;
import com.systable.enumeration.Profile;
import com.systable.exceptions.UMSException;

import javafx.collections.ObservableList;

public class AssistantMetier {
	
	private static UserDataSource userDataSource = new UserDataSource();
	private static TimeTableDataSource timeTableDataSource = new TimeTableDataSource();
	private static ModuleDataSource moduleDataSource = new ModuleDataSource();
	private static ClasseDataSource classeDataSource = new ClasseDataSource();

	public static ObservableList<User> listUsers() {
		return userDataSource.getUsers();
	}

	public static void listUsersByProfile(Profile profile) throws UMSException {
		userDataSource.getUsersByProfile(profile);
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