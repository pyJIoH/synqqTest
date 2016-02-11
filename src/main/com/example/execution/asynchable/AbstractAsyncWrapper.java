package main.com.example.execution.asynchable;

import javax.persistence.EntityManager;

import main.com.example.execution.processor.AbstractProcessor;

public abstract class AbstractAsyncWrapper {
	protected int eventsAmount;
	protected int attendeeMaxRange;

	private Thread thread = null;
	private AbstractProcessor runnable = null;

	public AbstractAsyncWrapper(int eventsAmount, int attendeeMaxRange) {
		this.eventsAmount = eventsAmount;
		this.attendeeMaxRange = attendeeMaxRange;
	}

	abstract public AbstractProcessor createProcessor(EntityManager entityManager, int eventsAmount, int attendeeMaxRange);

	public void startAsync(EntityManager entityManager) {
		runnable = createProcessor(entityManager, eventsAmount, attendeeMaxRange);
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
