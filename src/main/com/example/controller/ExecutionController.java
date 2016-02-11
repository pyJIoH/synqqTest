package main.com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import main.com.example.dao.EventDao;
import main.com.example.execution.asynchable.AbstractAsyncWrapper;
import main.com.example.execution.asynchable.factory.AsyncWrapperFactory;

@RestController
public class ExecutionController {
	private static final int THREADS_COUNT = 10;

	Map<String, List<AbstractAsyncWrapper>> writersThreads = new ConcurrentHashMap<>();
	Map<String, List<AbstractAsyncWrapper>> readersThreads = new ConcurrentHashMap<>();

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public @ResponseBody String start(
				@RequestParam(value = "eventsAmount", required = false, defaultValue = "10000") int eventsAmount,
				@RequestParam(value = "attendeeMaxRange", required = false, defaultValue = "10") int attendeeMaxRange) {
		
		AsyncWrapperFactory factory = new AsyncWrapperFactory(eventsAmount, attendeeMaxRange);
		String uuid = UUID.randomUUID().toString();
		List<AbstractAsyncWrapper> writers = new ArrayList<>();
		for (int i = 0; i < THREADS_COUNT; i++) {
			writers.add(factory.createWriter());
		}
		writersThreads.put(uuid, writers);

		List<AbstractAsyncWrapper> readers = new ArrayList<>();
		for (int i = 0; i < THREADS_COUNT; i++) {
			writers.add(factory.createReader());
		}
		readersThreads.put(uuid, readers);

		startThread(writers);
		startThread(readers);

		return "{\"uuid\": \"" + uuid + "\"}";
	}

	private void stopThread(List<AbstractAsyncWrapper> writers) {
		for (AbstractAsyncWrapper wrapper : writers) {
			wrapper.stopAsync();
		}
	}

	private void startThread(List<AbstractAsyncWrapper> writers) {
		for (AbstractAsyncWrapper wrapper : writers) {
			wrapper.startAsync(entityManagerFactory.createEntityManager());
		}
	}

	@RequestMapping(value = "/stop", method = RequestMethod.POST)
	public void stop(@RequestParam("uuid") String uuid) {
		List<AbstractAsyncWrapper> writers = writersThreads.get(uuid);
		if (writers != null) {
			stopThread(writers);
			writersThreads.remove(uuid);
		}

		List<AbstractAsyncWrapper> readers = readersThreads.get(uuid);
		if (readers != null) {
			stopThread(readers);
			readersThreads.remove(uuid);
		}
	}
	
	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public @ResponseBody String statistics() {
		synchronized (this) {
			int reads = EventDao.READS;
			EventDao.READS = 0;
			int writes = EventDao.WRITES;
			EventDao.WRITES = 0;
			return "{\"reads\": \"" + reads + "\", \"writes\": \"" + writes + "\"}";
		}
	}
	
}
