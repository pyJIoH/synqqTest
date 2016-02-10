package main.com.example.execution.processor;

import javax.persistence.EntityManager;

import main.com.example.beans.Event;
import main.com.example.dao.EventDao;
import main.com.example.execution.EventGenerator;

public class WriterProcessor extends AbstractProcessor {
	
	public WriterProcessor(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void run() {
		EventGenerator gen = new EventGenerator();
		EventDao eventDao = new EventDao(getEntityManager());
		while (running) {
			try {
				Event event = gen.getNewEvent();
				eventDao.saveOrUpdate(event);
				Thread.sleep(READ_WRITE_DELAY);
			} catch (InterruptedException e) {
				running = false;
			}
		}
	}

}
