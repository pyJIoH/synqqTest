package test.com.example.execution;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import main.com.example.beans.Attendee;
import main.com.example.beans.Event;
import main.com.example.execution.EventGenerator;

public class EventGeneratorTest {

	private final int eventMaxId = 10000;
	private final int attendeeMaxRange = 10;
	private EventGenerator generator;

	@Before
	public void init() {
		generator = new EventGenerator(eventMaxId, attendeeMaxRange);
	}

	@Test
	public void creation() {
		assertNotNull(generator);
		assertNotNull(generator.getNewAttendees());
	}

	@Test
	public void attendeeMaxRange() {
		Set<Integer> attendees = generator.getNewAttendees();
		assertNotNull(attendees);
		
		for (Integer attendeeNumber : attendees) {
			assertTrue(attendeeNumber > 0);
			assertTrue(attendeeNumber < attendeeMaxRange + 1);
		}
	}

	@Test
	public void attendeesCount() {
		Set<Integer> attendees = generator.getNewAttendees();
		assertTrue(attendees.size() >= 0);
		assertTrue(attendees.size() <= 5);
	}

	@Test
	public void eventMaxId() {
		assertTrue(generator.getNewId() > -1);
		assertTrue(generator.getNewId() < eventMaxId);
	}

	@Test
	public void eventCreation() {
		Event event = generator.getNewEvent();
		assertNotNull(event);
		
		Set<Attendee> attendees = event.getAttendees();
		assertNotNull(attendees);
		
		assertTrue(attendees.size() >= 0);
		assertTrue(attendees.size() <= 5);
		
		for (Attendee attendee : attendees) {
			assertTrue(attendee.getId().getNumber() > 0);
			assertTrue(attendee.getId().getNumber() < attendeeMaxRange + 1);
		}
		
		assertTrue(event.getId() > -1);
		assertTrue(event.getId() < eventMaxId);
	}

}
