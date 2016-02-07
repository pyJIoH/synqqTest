package main.com.example.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.com.example.execution.model.ExecutionModel;
import main.com.example.execution.model.ExecutionModelImpl;

@RestController
public class ExecutionController {

	@PersistenceContext
    private EntityManager entityManager;

	@RequestMapping(value = "/generate10", method = RequestMethod.POST)
	@Transactional
	public void generate10() {
		ExecutionModel executor = new ExecutionModelImpl();
		executor.generate10(entityManager);
	}
}
