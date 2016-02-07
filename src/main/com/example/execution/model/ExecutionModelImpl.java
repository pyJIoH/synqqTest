package main.com.example.execution.model;

import main.com.example.beans.Event;
import main.com.example.dao.EventDao;
import main.com.example.execution.Writer;

public class ExecutionModelImpl implements ExecutionModel {
	
	public void generate10() {
		Writer writer = new Writer();
		EventDao eventDao = new EventDao();
		for (int i = 0; i < 10; i++) {
			Event event = writer.getNewEvent();
			eventDao.persist(event);
		}
	}

}
