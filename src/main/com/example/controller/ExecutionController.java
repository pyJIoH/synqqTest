package main.com.example.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.com.example.execution.Writer;
import main.com.example.execution.processor.WriterProcessor;

@RestController
public class ExecutionController {
	
	private Writer writer = new Writer();

	@PersistenceContext
	private EntityManager entityManager;

	@RequestMapping(value = "/generate", method = RequestMethod.POST)
	@Transactional
	public void generate(@RequestParam("count") int count) {
		WriterProcessor executor = new WriterProcessor(entityManager);
		executor.generate(count);
	}

	@RequestMapping(value = "/start", method = RequestMethod.POST)
	@Transactional
	public void start() {
		writer.startAsync(entityManager);
	}

	@RequestMapping(value = "/stop", method = RequestMethod.POST)
	@Transactional
	public void stop() {
		writer.stopAsync(entityManager);
	}
}
