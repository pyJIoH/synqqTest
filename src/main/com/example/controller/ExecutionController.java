package main.com.example.controller;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import main.com.example.execution.Writer;
import main.com.example.execution.processor.WriterProcessor;

@RestController
public class ExecutionController {
	Map<String, Writer> writers = new ConcurrentHashMap<>();
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@RequestMapping(value = "/generate", method = RequestMethod.POST)
	public void generate(@RequestParam("count") int count) {
		WriterProcessor executor = new WriterProcessor(entityManager);
		executor.generate(count);
	}

	@Transactional
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public @ResponseBody String start() {
		Writer writer = new Writer();
		String uuid = UUID.randomUUID().toString();
		writers.put(uuid, writer);
//		writer.startAsync(entityManager);
		return "{\"uuid\": \"" + uuid  + "\"}";
	}

	@Transactional
	@RequestMapping(value = "/stop", method = RequestMethod.POST)
	public void stop(@RequestParam("uuid") String uuid) {
		Writer writer = writers.get(uuid);
		if (writer != null) {
			writer.stopAsync(entityManager);
			writers.remove(uuid);
		}
	}
}
