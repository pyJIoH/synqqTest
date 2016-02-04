package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import com.example.beans.Attendee;
import com.example.beans.AttendeeId;
import com.example.util.HibernateUtil;

/**
 * Home object for domain model class Attendee.
 * 
 * @see com.example.beans.Attendee
 * @author Hibernate Tools
 */
@Stateless
public class AttendeeDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Attendee transientInstance) {
		try {
			entityManager.persist(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void remove(Attendee persistentInstance) {
		try {
			entityManager.remove(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Attendee merge(Attendee detachedInstance) {
		try {
			Attendee result = entityManager.merge(detachedInstance);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Attendee findById(AttendeeId id) {
		try {
			Attendee instance = entityManager.find(Attendee.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List<Attendee> getAllAttendees() {
		entityManager = HibernateUtil.getSessionFactory().createEntityManager();

		List<Attendee> attendees = new ArrayList<Attendee>();
		EntityTransaction tx =  entityManager.getTransaction();
		try {
			tx.begin();
			attendees = entityManager.createQuery("from Attendee").getResultList();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return attendees;
	}

}
