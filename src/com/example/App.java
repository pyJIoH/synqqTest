package com.example;

import com.example.dao.DataDao;
import com.example.entity.Data;

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
