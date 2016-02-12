package main.com.example.execution;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import main.com.example.beans.Attendee;
import main.com.example.beans.AttendeeId;
import main.com.example.beans.Event;

public class EventGenerator {
	
	private int eventMaxId;
	private int attendeeMaxRange;
	private final int startTimeMaxRange = 100000;
	private final int attendeesAmount = 6;
	
	public EventGenerator(int eventMaxId, int attendeeMaxRange) {
		this.eventMaxId = eventMaxId;
		this.attendeeMaxRange = attendeeMaxRange;
	}
	
	public int getNewId() {
		return new Random().nextInt(eventMaxId);
	}  
	
	public int getNewStartTime() {
		return new Random().nextInt(startTimeMaxRange) + 1; // can't be 0
	}
	
	public Set<Integer> getNewAttendees() {
		Set<Integer> attendeesNumbers = new TreeSet<>();
		int attendeesCount = new Random().nextInt(attendeesAmount);				// [0..5] values
		for (int i = 0; i < attendeesCount; i++) {
			attendeesNumbers.add(new Random().nextInt(attendeeMaxRange) + 1); 	// [1..maxRange] values, cannot duplicate
		}
		return attendeesNumbers;
	}
	
	public Event getNewEvent() {
		int id = getNewId();
		int startTime = getNewStartTime();
		Set<Integer> attendeesNumbers = getNewAttendees();
		
		Event event = new Event(id, startTime);
		Set<Attendee> attendees = new HashSet<Attendee>();
		for (Integer number : attendeesNumbers) {
			attendees.add(new Attendee(new AttendeeId(id, number), event));
		}
		event.setAttendees(attendees);
		
		return event;
	}

}
