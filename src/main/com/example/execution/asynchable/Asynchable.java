package main.com.example.execution.asynchable;

import javax.persistence.EntityManager;

public interface Asynchable {

	public void startAsync(EntityManager entityManager);

	public void stopAsync();

}
