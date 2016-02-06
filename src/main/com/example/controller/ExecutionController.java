package main.com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.com.example.execution.ExecutionModel;
import main.com.example.execution.ExecutionModelImpl;

@RestController
public class ExecutionController {
	
	@RequestMapping(value = "/generate10", method = RequestMethod.POST)
	public void generate10() {
		ExecutionModel executor = new ExecutionModelImpl();
		executor.generate10();
	}
}
