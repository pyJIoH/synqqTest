package main.com.example.execution;

import javax.persistence.EntityManager;

import main.com.example.beans.Event;
import main.com.example.dao.EventDao;
import main.com.example.execution.processor.WriterProcessor;

public class Writer {

	private Thread thread = null;
	private WriterProcessor runnable = null;

	public void syncGenerate(EntityManager entityManager, int count) {
		Generator gen = new Generator();
		EventDao eventDao = new EventDao(entityManager);
		for (int i = 0; i < count; i++) {
			Event event = gen.getNewEvent();
			eventDao.saveOrUpdate(event);
		}
	}

	public void startAsync(EntityManager entityManager) {
		runnable = new WriterProcessor(entityManager);
        thread = new Thread(runnable);
        thread.start();
	}

	public void stopAsync(EntityManager entityManager) {
		if (thread != null) {
            runnable.terminate();
            try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
