package main.com.example.execution.processor;

import javax.persistence.EntityManager;

public abstract class AbstractProcessor implements Runnable {
	protected static final int READ_WRITE_DELAY = 100; 
	protected volatile boolean running = true;
	private volatile EntityManager entityManager;

	public AbstractProcessor(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void terminate() {
        running = false;
    }

	public EntityManager getEntityManager() {
		return entityManager;
	}

}
