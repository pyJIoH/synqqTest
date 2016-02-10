package main.com.example.controller;

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

import main.com.example.execution.Writer;

@RestController
public class ExecutionController {
	Map<String, Writer> writers = new ConcurrentHashMap<>();
	
    @Autowired
    EntityManagerFactory entityManagerFactory;
    
	@Transactional
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public @ResponseBody String start() {
		Writer writer = new Writer();
		String uuid = UUID.randomUUID().toString();
		writers.put(uuid, writer);
		writer.startAsync(entityManagerFactory.createEntityManager());
		return "{\"uuid\": \"" + uuid  + "\"}";
	}

	@Transactional
	@RequestMapping(value = "/stop", method = RequestMethod.POST)
	public void stop(@RequestParam("uuid") String uuid) {
		Writer writer = writers.get(uuid);
		if (writer != null) {
			writer.stopAsync();
			writers.remove(uuid);
		}
	}
}
