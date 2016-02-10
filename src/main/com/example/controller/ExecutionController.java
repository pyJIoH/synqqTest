package main.com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import main.com.example.execution.asynchable.AbstractAsyncWrapper;
import main.com.example.execution.asynchable.Reader;
import main.com.example.execution.asynchable.Writer;

@RestController
public class ExecutionController {
	private static final int THREADS_COUNT = 10;
	
	Map<String, List<? extends AbstractAsyncWrapper>> writersThreads = new ConcurrentHashMap<>();
	Map<String, List<? extends AbstractAsyncWrapper>> readersThreads = new ConcurrentHashMap<>();
	
    @Autowired
    EntityManagerFactory entityManagerFactory;
    
    @Transactional
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public @ResponseBody String start() {
		
		String uuid = UUID.randomUUID().toString();
		List<? extends AbstractAsyncWrapper> writers = createWriters();
		writersThreads.put(uuid, writers);
		
		List<? extends AbstractAsyncWrapper> readers = createReaders();
		readersThreads.put(uuid, readers);
		
		startThread(writers);
		startThread(readers);
		
		return "{\"uuid\": \"" + uuid  + "\"}";
	}


	private void stopThread(List<? extends AbstractAsyncWrapper> writers) {
		for (AbstractAsyncWrapper wrapper : writers) {
			wrapper.stopAsync();
		}
	}

	private void startThread(List<? extends AbstractAsyncWrapper> writers) {
		for (AbstractAsyncWrapper wrapper : writers) {
			wrapper.startAsync(entityManagerFactory.createEntityManager());
		}
	}

	private List<? extends AbstractAsyncWrapper> createWriters() {
		List<Writer> writers = new ArrayList<>();
		for (int i = 0; i < THREADS_COUNT; i++) {
			writers.add(new Writer());
		}
		return writers;
	}

	private List<? extends AbstractAsyncWrapper> createReaders() {
		List<Reader> readers = new ArrayList<>();
		for (int i = 0; i < THREADS_COUNT; i++) {
			readers.add(new Reader());
		}
		return readers;
	}

	@Transactional
	@RequestMapping(value = "/stop", method = RequestMethod.POST)
	public void stop(@RequestParam("uuid") String uuid) {
		List<? extends AbstractAsyncWrapper> writers = writersThreads.get(uuid);
		if (writers != null) {
			stopThread(writers);
			writersThreads.remove(uuid);
		}
		
		List<? extends AbstractAsyncWrapper> readers = readersThreads.get(uuid);
		if (readers != null) {
			stopThread(readers);
			readersThreads.remove(uuid);
		}
	}
}
