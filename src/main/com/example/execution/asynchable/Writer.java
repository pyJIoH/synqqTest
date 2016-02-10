package main.com.example.execution.asynchable;

import javax.persistence.EntityManager;

import main.com.example.execution.processor.WriterProcessor;

public class Writer extends AbstractAsyncWrapper {

	public void startAsync(EntityManager entityManager) {
		runnable = new WriterProcessor(entityManager);
        thread = new Thread(runnable);
        thread.start();
	}

}
