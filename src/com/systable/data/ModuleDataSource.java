package com.systable.data;

import com.systable.entities.Modules;
import com.systable.exceptions.UMSException;
import com.systable.jdbc.ModuleDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModuleDataSource {

	private ObservableList<Modules> modules;

	public ModuleDataSource() {
		
		try {
			listModules();
		} catch (UMSException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Modules> getModules() {
		return modules;
	}

	private void listModules() throws UMSException {
		modules = FXCollections.observableArrayList();
		modules = ModuleDAO.getModules();
	}

	public int addModule(Modules modules) throws UMSException {

		int status = ModuleDAO.addModule(modules);

		if (status > 0) {

			int id = ModuleDAO.getMaxIdByModule();
			modules.setId(id);

//			modules.add(modules);
		}

		return status;
	}

	public int updateModule(Modules modules, int selectedModuleIndex) throws UMSException {
		int status = ModuleDAO.updateModule(modules);

		if (status > 0) {
			this.modules.remove(selectedModuleIndex);
			this.modules.add(selectedModuleIndex, modules);
		}

		return status;
	}
}
