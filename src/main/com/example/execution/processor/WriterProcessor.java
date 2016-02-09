package main.com.example.execution.processor;

import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public void run() {
		Generator gen = new Generator();
		EventDao eventDao = new EventDao(entityManager);
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
