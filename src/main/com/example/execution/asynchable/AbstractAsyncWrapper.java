package main.com.example.execution.asynchable;

import javax.persistence.EntityManager;

import main.com.example.execution.processor.AbstractProcessor;

public abstract class AbstractAsyncWrapper {

	protected Thread thread = null;
	protected AbstractProcessor runnable = null;

	abstract public void startAsync(EntityManager entityManager);

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
