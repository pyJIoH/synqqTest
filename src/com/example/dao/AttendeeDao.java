package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.entity.Attendee;
import com.example.model.HibernateUtil;

public class AttendeeDao {
	public List<Attendee> getAllAttendees() {
		List<Attendee> attendees = new ArrayList<Attendee>();
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			attendees = session.createQuery("from Attendee").list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			trns.commit();
			session.flush();
			session.close();
		}
		return attendees;
	}

}
