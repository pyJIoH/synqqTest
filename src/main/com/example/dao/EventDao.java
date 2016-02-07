package main.com.example.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import main.com.example.beans.Event;

/**
 * Home object for domain model class Event.
 * 
 * @see main.com.example.beans.Event
 * @author Hibernate Tools
 */
@Stateless
public class EventDao {

	public EventDao(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}

    private EntityManager entityManager;
	
	public void persist(Event transientInstance) {
		try {
			entityManager.persist(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void remove(Event persistentInstance) {
		try {
			entityManager.remove(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Event merge(Event detachedInstance) {
		try {
			Event result = entityManager.merge(detachedInstance);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Event findById(int id) {
		try {
			Event instance = entityManager.find(Event.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List<Event> getEvents() {
		List<Event> events = new ArrayList<Event>();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		try {
			events = entityManager.createQuery("from Event").getResultList();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return events;
	}

}
