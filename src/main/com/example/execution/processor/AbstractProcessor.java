package main.com.example.execution.processor;

import javax.persistence.EntityManager;

public abstract class AbstractProcessor implements Runnable {
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
