package main.com.example.execution.asynchable;

import javax.persistence.EntityManager;

import main.com.example.execution.processor.AbstractProcessor;
import main.com.example.execution.processor.ReaderProcessor;

public class Reader extends AbstractAsyncWrapper {
	
	public Reader(int eventsAmount, int attendeeMaxRange) {
		super(eventsAmount, attendeeMaxRange);
	}

	@Override
	public AbstractProcessor createProcessor(EntityManager entityManager, int eventsAmount, int attendeeMaxRange) {
		return new ReaderProcessor(entityManager, eventsAmount, attendeeMaxRange);
	}

}
