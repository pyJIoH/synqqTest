package main.com.example.execution.processor;

import javax.persistence.EntityManager;

import main.com.example.beans.Event;
import main.com.example.dao.EventDao;
import main.com.example.execution.Generator;

public class WriterProcessor implements Runnable {
	private volatile boolean running = true;
	private volatile EntityManager entityManager;

	public WriterProcessor(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void terminate() {
        running = false;
    }

	public void generate(int count) {
		Generator gen = new Generator();
		EventDao eventDao = new EventDao(entityManager);
		for (int i = 0; i < count; i++) {
			Event event = gen.getNewEvent();
			eventDao.saveOrUpdate(event);
		}
	}

	@Override
	public void run() {
		Generator gen = new Generator();
		EventDao eventDao = new EventDao(entityManager);
		while (running) {
			try {
				Event event = gen.getNewEvent();
				eventDao.saveOrUpdate(event);
				Thread.sleep(500);
			} catch (InterruptedException e) {
				running = false;
			}
		}
	}

}