package main.com.example.execution.asynchable.factory;

import main.com.example.execution.asynchable.AbstractAsyncWrapper;
import main.com.example.execution.asynchable.Reader;
import main.com.example.execution.asynchable.Writer;

public class AsyncWrapperFactory {
	
	private int eventsAmount;
	private int attendeeMaxRange;

	public AsyncWrapperFactory(int eventsAmount, int attendeeMaxRange) {
		this.eventsAmount = eventsAmount;
		this.attendeeMaxRange = attendeeMaxRange;
	}
	
	public AbstractAsyncWrapper createWriter() {
		return new Writer(eventsAmount, attendeeMaxRange);
	}

	public AbstractAsyncWrapper createReader() {
		return new Reader(eventsAmount, attendeeMaxRange);
	}

}
