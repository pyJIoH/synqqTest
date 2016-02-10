package main.com.example.execution.asynchable;

import javax.persistence.EntityManager;

import main.com.example.execution.processor.ReaderProcessor;

public class Reader extends AbstractAsyncWrapper {
	
	public void startAsync(EntityManager entityManager) {
		runnable = new ReaderProcessor(entityManager);
        thread = new Thread(runnable);
        thread.start();
	}

}
