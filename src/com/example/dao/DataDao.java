package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.entity.Data;
import com.example.model.HibernateUtil;

public class DataDao {
	
	public List<Data> getAllData() {
		List<Data> data = new ArrayList<Data>();
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			data = session.createQuery("from Data").list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
//			trns.commit();
			session.flush();
			session.close();
		}
		return data;
	}
}
