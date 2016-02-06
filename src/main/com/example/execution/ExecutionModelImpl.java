package main.com.example.execution;

import main.com.example.beans.Event;
import main.com.example.dao.EventDao;

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
