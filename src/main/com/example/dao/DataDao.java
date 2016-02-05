package main.com.example.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import main.com.example.beans.Data;
import main.com.example.util.HibernateUtil;

/**
 * Home object for domain model class Data.
 * 
 * @see main.com.example.beans.Data
 * @author Hibernate Tools
 */
@Stateless
public class DataDao {

	@PersistenceContext
	private EntityManager entityManager = HibernateUtil.createEntityManager();

	public void persist(Data transientInstance) {
		try {
			entityManager.persist(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void remove(Data persistentInstance) {
		try {
			entityManager.remove(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Data merge(Data detachedInstance) {
		try {
			Data result = entityManager.merge(detachedInstance);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Data findById(int id) {
		try {
			Data instance = entityManager.find(Data.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List<Data> getAllData() {
		List<Data> data = new ArrayList<Data>();
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			data = entityManager.createQuery("from Data").getResultList();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return data;
	}
}
