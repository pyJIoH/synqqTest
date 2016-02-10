package main.com.example.execution;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import main.com.example.beans.Attendee;
import main.com.example.beans.AttendeeId;
import main.com.example.beans.Event;

public class EventGenerator {
	
	public int getNewId() {
		return new Random().nextInt(10000);
	}  
	
	public int getNewStartTime() {
		return new Random().nextInt(100000) + 1; // can't be 0
	}
	
	public Set<Integer> getNewAttendees() {
		Set<Integer> attendeesNumbers = new TreeSet<>();
		int atendeesCount = new Random().nextInt(6); 			// [0..5] values
		for (int i = 0; i < atendeesCount; i++) {
			attendeesNumbers.add(new Random().nextInt(10) + 1); 	// [1..10] values, cannot duplicate
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
