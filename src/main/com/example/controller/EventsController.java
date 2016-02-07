package main.com.example.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.com.example.beans.Event;
import main.com.example.dao.EventDao;

@RestController
public class EventsController {

	@PersistenceContext
    private EntityManager entityManager;

	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public List<Event> events() {
		List<Event> events = new EventDao(entityManager).getEvents();
		return events;
	}
}