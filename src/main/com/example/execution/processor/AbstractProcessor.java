package main.com.example.execution.processor;

import javax.persistence.EntityManager;

public abstract class AbstractProcessor implements Runnable {
	private volatile EntityManager entityManager;
	protected static final int READ_WRITE_DELAY = 100; 
	protected volatile boolean running = true;
	protected volatile int eventsAmount;
	protected volatile int attendeeMaxRange;

	public AbstractProcessor(EntityManager entityManager, int eventsAmount, int attendeeMaxRange) {
		this.entityManager = entityManager;
		this.eventsAmount = eventsAmount;
		this.attendeeMaxRange = attendeeMaxRange;
	}
	
	public void terminate() {
        running = false;
    }

	public EntityManager getEntityManager() {
		return entityManager;
	}

}
