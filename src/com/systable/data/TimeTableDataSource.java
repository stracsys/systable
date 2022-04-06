package com.systable.data;

import com.systable.entities.TimeTable;
import com.systable.exceptions.UMSException;
import com.systable.jdbc.TimeTableDAO;
import com.systable.jdbc.TimeTableDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TimeTableDataSource {

	private ObservableList<TimeTable> timeTables;

	public TimeTableDataSource() {
		
		try {
			listTimeTables();
		} catch (UMSException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<TimeTable> getTimeTables() {
		return timeTables;
	}

	private void listTimeTables() throws UMSException {
		timeTables = FXCollections.observableArrayList();
		timeTables = TimeTableDAO.getTimeTables();
	}

	public int addTimeTable(TimeTable timeTable) throws UMSException {

		int status = TimeTableDAO.addTimeTable(timeTable);

		if (status > 0) {

			int id = TimeTableDAO.getMaxIdByTimeTable();
			timeTable.setId(id);

//			timeTables.add(timeTable);
		}

		return status;
	}
}
