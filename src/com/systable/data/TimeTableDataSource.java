package com.systable.data;

import com.systable.entities.TimeTable;
import com.systable.exceptions.UMSException;
import com.systable.jdbc.TimeTableDAO;
import com.systable.jdbc.TimeTableDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TimeTableDataSource {

	private ObservableList<TimeTable> allTimeTables;

	public TimeTableDataSource() {
		this.allTimeTables = FXCollections.observableArrayList();
		
		try {
			listAllTimeTables();
			int lastId = -1;

			int lastIndex = allTimeTables.size() - 1;
			lastId = allTimeTables.get(lastIndex).getId();
			TimeTable.setIdAssigner(lastId);

		} catch (UMSException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<TimeTable> getTimeTables() {
		return allTimeTables;
	}

	private void listAllTimeTables() throws UMSException {
		allTimeTables = TimeTableDAO.getTimeTables();
	}

	public int addTimeTable(TimeTable timeTable) throws UMSException {

		int status = TimeTableDAO.addTimeTable(timeTable);

		if (status > 0) {

			int id = TimeTable.getIdAssigner() + 1;
			TimeTable.setIdAssigner(id);

			timeTable.setId(id);
			this.allTimeTables.add(timeTable);
		}

		return status;
	}
}
