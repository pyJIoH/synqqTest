package com.example.model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

	private static EntityManagerFactory sf;

	public static synchronized EntityManagerFactory getSessionFactory() {
		if (sf == null) {
			sf = Persistence.createEntityManagerFactory("com.example");
		}
		return sf;
	}

}
