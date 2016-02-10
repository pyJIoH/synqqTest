package main.com.example.execution;

import javax.persistence.EntityManager;

import main.com.example.execution.processor.AbstractProcessor;
import main.com.example.execution.processor.WriterProcessor;

public class Writer {

	private Thread thread = null;
	private AbstractProcessor runnable = null;

	public void startAsync(EntityManager entityManager) {
		runnable = new WriterProcessor(entityManager);
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
