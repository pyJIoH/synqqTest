package main.com.example.execution.processor;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import main.com.example.beans.Event;
import main.com.example.dao.EventDao;
import main.com.example.execution.EventGenerator;

public class ReaderProcessor extends AbstractProcessor {

	public ReaderProcessor(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void run() {
		EventGenerator gen = new EventGenerator();
		EventDao eventDao = new EventDao(getEntityManager());
		while (running) {
			try {
				int startTime = gen.getNewStartTime();
				int anotherStartTime = gen.getNewStartTime();
				int[] sortedTime = new int[] {startTime, anotherStartTime};
				java.util.Arrays.sort(sortedTime);
				Set<Integer> attendeesRange = gen.getNewAttendees();
				
				List<Event> list = eventDao.getEvents(sortedTime[0], sortedTime[1], attendeesRange);
//				System.out.println(list.size() + " record(s) has been found");
				
				Thread.sleep(READ_WRITE_DELAY);
			} catch (InterruptedException e) {
				running = false;
			}
		}
	}
}
