package main.com.example.execution.model;

import javax.persistence.EntityManager;

import main.com.example.beans.Event;
import main.com.example.dao.EventDao;
import main.com.example.execution.Generator;

public class ExecutionModelImpl implements ExecutionModel {

	@Override
	public void generate10(EntityManager entityManager) {
		Generator gen = new Generator();
		EventDao eventDao = new EventDao(entityManager);
		for (int i = 0; i < 10; i++) {
			Event event = gen.getNewEvent();
			eventDao.saveOrUpdate(event);
		}
	}

}
