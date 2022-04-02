package com.systable.data;

import com.systable.entities.Classe;
import com.systable.exceptions.UMSException;
import com.systable.jdbc.ClasseDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClasseDataSource {

	private ObservableList<Classe> allClasses;

	public ClasseDataSource() {
		this.allClasses = FXCollections.observableArrayList();
		
		try {
			listAllClasses();
			int lastId = -1;

			int lastIndex = allClasses.size() - 1;
			lastId = allClasses.get(lastIndex).getId();
			Classe.setIdAssigner(lastId);

		} catch (UMSException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Classe> getClasses() {
		return allClasses;
	}

	private void listAllClasses() throws UMSException {
		allClasses = ClasseDAO.getClasses();
	}

	public int addClasse(Classe classe) throws UMSException {

		int status = ClasseDAO.addClasse(classe);

		if (status > 0) {

			int id = Classe.getIdAssigner() + 1;
			Classe.setIdAssigner(id);

			classe.setId(id);
			this.allClasses.add(classe);
		}

		return status;
	}

	public int updateClasse(Classe classe, int selectedClasseIndex) throws UMSException {
		int status = ClasseDAO.updateClasse(classe);

		if (status > 0) {
			this.allClasses.remove(selectedClasseIndex);
			this.allClasses.add(selectedClasseIndex, classe);
		}

		return status;
	}
}
