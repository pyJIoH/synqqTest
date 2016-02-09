package main.com.example.execution.processor;

import javax.persistence.EntityManager;

import main.com.example.beans.Event;
import main.com.example.dao.EventDao;
import main.com.example.execution.Generator;

public class WriterProcessor extends AbstractProcessor {
	
	public WriterProcessor(EntityManager entityManager) {
		super(entityManager);
	}

	public void generate(int count) {
		Generator gen = new Generator();
		EventDao eventDao = new EventDao(getEntityManager());
		for (int i = 0; i < count; i++) {
			Event event = gen.getNewEvent();
			eventDao.saveOrUpdate(event);
		}
	}

	@Override
	public void run() {
		Generator gen = new Generator();
		EventDao eventDao = new EventDao(getEntityManager());
		while (running) {
			try {
				Event event = gen.getNewEvent();
				eventDao.saveOrUpdate(event);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				running = false;
			}
		}
	}

}
