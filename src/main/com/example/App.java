package main.com.example;

import main.com.example.beans.Data;
import main.com.example.dao.DataDao;

public class App {

	public static void main(String[] args) {
		DataDao dataDao = new DataDao();
		for (Data dataRow : dataDao.getAllData()) {
			System.out.println(
				"id: " + dataRow.getId() +
				" startTime: " + dataRow.getStartTime() +
				" attendees: " + dataRow.getAttendeeses()
			);
		}
	}
}
