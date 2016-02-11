package main.com.example.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import main.com.example.beans.Event;

/**
 * Home object for domain model class Event.
 * 
 * @see main.com.example.beans.Event
 * @author Hibernate Tools
 */
@Stateless
public class EventDao {

	private EntityManager entityManager;

	public static volatile int READS = 0;
	public static volatile int WRITES = 0; 

	public EventDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

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

	public void saveOrUpdate(Event detachedInstance) {
		entityManager.getTransaction().begin();
		try {
			Event existingEvent = findById(detachedInstance.getId());
			if (existingEvent != null) {
				entityManager.remove(existingEvent);
				entityManager.flush();
			}
			entityManager.persist(detachedInstance);
			entityManager.getTransaction().commit();
			WRITES++;
		} catch (RuntimeException re) {
			if (entityManager.getTransaction().isActive())
				entityManager.getTransaction().rollback();
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

	public List<Event> getEvents(int minStartTime, int maxStartTime, Set<Integer> attendeesRange) {
		List<Event> events = new ArrayList<Event>();
		try {
			String hql = generateHql(minStartTime, maxStartTime, attendeesRange);
			events = entityManager.createQuery(hql).getResultList();
			READS++;
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return events;
	}

	private synchronized String generateHql(int minStartTime, int maxStartTime, Set<Integer> attendeesRange) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT e.id FROM Event as e ");
		hql.append("LEFT JOIN e.attendees as a WHERE a.id.eventId = e.id ");
		hql.append("AND e.startTime > " + minStartTime + " AND e.startTime < " + maxStartTime);

		int attendeesRangeSize = attendeesRange.size();
		if (attendeesRangeSize > 0) {
			hql.append(" AND ");
			Iterator<Integer> iterator = attendeesRange.iterator();
			while (iterator.hasNext()) {
				hql.append("a.id.number = " + iterator.next());
				if (iterator.hasNext())
					hql.append(" OR ");
			}
			hql.append(" GROUP BY a.id.eventId");
			hql.append(" HAVING count(e.id) = " + attendeesRangeSize);
		}
		return hql.toString();
	}

}
