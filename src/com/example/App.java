package com.example;

import org.hibernate.SessionFactory;

import com.example.dao.DataDao;
import com.example.entity.Data;

public class App {
	private static SessionFactory factory;
	
	public static void main(String[] args) {
		DataDao dataDao = new DataDao();
		for (Data dataRow : dataDao.getAllData()) {
			System.out.println(
			"id: " + dataRow.getId() +
			"startTime: " + dataRow.getStartTime() +
			"attendees: " + dataRow.getAttendeeses() + " ");
		}
	}
}
