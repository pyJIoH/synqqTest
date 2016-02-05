package main.com.example.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

	private static EntityManagerFactory sf;

	private static synchronized EntityManagerFactory getEntityManagerFactory() {
		if (sf == null) {
			sf = Persistence.createEntityManagerFactory("com.example");
		}
		return sf;
	}

	public static EntityManager createEntityManager() {
		return HibernateUtil.getEntityManagerFactory().createEntityManager();
	}

}
