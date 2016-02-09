package main.com.example.execution;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import main.com.example.beans.Attendee;
import main.com.example.beans.AttendeeId;
import main.com.example.beans.Event;

public class Generator {
	private int id;
	private int startTime;
	private Set<Integer> atendeesNumbers = new TreeSet<>();
	
	private void generateData() {
		atendeesNumbers.clear();
		id = new Random().nextInt(10000);
		startTime = new Random().nextInt(100000) + 1; //can't be 0
		int atendeesCount = new Random().nextInt(6); // [0..5] values
		for (int i = 0; i < atendeesCount; i++) {
			atendeesNumbers.add(new Random().nextInt(10) + 1); //[1..10] values, cannot duplicate
		}
	}
	
	public Event getNewEvent() {
		generateData();
		Event event = new Event(id, startTime);
		
		Set<Attendee> attendees = new HashSet<Attendee>();
		for (Integer number : atendeesNumbers) {
			attendees.add(new Attendee(new AttendeeId(id, number), event));
		}
		event.setAttendees(attendees);
		
		return event;
	}

}
