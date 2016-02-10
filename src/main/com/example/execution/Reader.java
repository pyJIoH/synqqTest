package main.com.example.execution;

import javax.persistence.EntityManager;

import main.com.example.execution.processor.AbstractProcessor;
import main.com.example.execution.processor.ReaderProcessor;

public class Reader {
	
	private Thread thread = null;
	private AbstractProcessor runnable = null;

	public void startAsync(EntityManager entityManager) {
		runnable = new ReaderProcessor(entityManager);
        thread = new Thread(runnable);
        thread.start();
	}

	public void stopAsync() {
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
