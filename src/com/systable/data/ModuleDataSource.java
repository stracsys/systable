package com.systable.data;

import com.systable.entities.Modules;
import com.systable.exceptions.UMSException;
import com.systable.jdbc.ModuleDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModuleDataSource {

	private ObservableList<Modules> allModules;

	public ModuleDataSource() {
		this.allModules = FXCollections.observableArrayList();
		
		try {
			listAllModules();
			int lastId = -1;

			int lastIndex = allModules.size() - 1;
			lastId = allModules.get(lastIndex).getId();
			Modules.setIdAssigner(lastId);

		} catch (UMSException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Modules> getModules() {
		return allModules;
	}

	private void listAllModules() throws UMSException {
		allModules = ModuleDAO.getModules();
	}

	public int addModule(Modules modules) throws UMSException {

		int status = ModuleDAO.addModule(modules);

		if (status > 0) {

			int id = Modules.getIdAssigner() + 1;
			Modules.setIdAssigner(id);

			modules.setId(id);
			this.allModules.add(modules);
		}

		return status;
	}

	public int updateModule(Modules modules, int selectedModuleIndex) throws UMSException {
		int status = ModuleDAO.updateModule(modules);

		if (status > 0) {
			this.allModules.remove(selectedModuleIndex);
			this.allModules.add(selectedModuleIndex, modules);
		}

		return status;
	}
}
