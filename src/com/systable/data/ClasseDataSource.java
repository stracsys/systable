package com.systable.data;

import com.systable.entities.Classe;
import com.systable.exceptions.UMSException;
import com.systable.jdbc.ClasseDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClasseDataSource {

	private ObservableList<Classe> classes;

	public ClasseDataSource() {
		
		try {
			listClasses();
		} catch (UMSException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Classe> getClasses() {
		return classes;
	}

	private void listClasses() throws UMSException {
		classes = FXCollections.observableArrayList();
		classes = ClasseDAO.getClasses();
	}

	public int addClasse(Classe classe) throws UMSException {

		int status = ClasseDAO.addClasse(classe);

		if (status > 0) {

			int id = ClasseDAO.getMaxIdByClasse();
			classe.setId(id);
			classes.add(classe);
		}

		return status;
	}

	public int updateClasse(Classe classe, int selectedClasseIndex) throws UMSException {
		int status = ClasseDAO.updateClasse(classe);

		if (status > 0) {
			classes.remove(selectedClasseIndex);
			classes.add(selectedClasseIndex, classe);
		}

		return status;
	}
}
