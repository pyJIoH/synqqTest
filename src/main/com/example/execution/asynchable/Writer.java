package main.com.example.execution.asynchable;

import javax.persistence.EntityManager;

import main.com.example.execution.processor.AbstractProcessor;
import main.com.example.execution.processor.WriterProcessor;

public class Writer extends AbstractAsyncWrapper {

	public Writer(int eventsAmount, int attendeeMaxRange) {
		super(eventsAmount, attendeeMaxRange);
	}

	@Override
	public AbstractProcessor createProcessor(EntityManager entityManager, int eventsAmount, int attendeeMaxRange) {
		return new WriterProcessor(entityManager, eventsAmount, attendeeMaxRange);
	}

}
