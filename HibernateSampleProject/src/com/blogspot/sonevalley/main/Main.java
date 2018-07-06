package com.blogspot.sonevalley.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.blogspot.sonevalley.model.CustomerEntity;
import com.blogspot.sonevalley.util.HibernateUtil;

public class Main {
	
	public static void main(String[] args) {
		
		Transaction transaction = null;
		SessionFactory sessionFactory = null;
		CustomerEntity customer1 = new CustomerEntity("Sushil Kumar");
		CustomerEntity customer2 = new CustomerEntity("Vijay Pandey");
		
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.save(customer1);
			session.save(customer2);
			transaction.commit();
			System.out.println("Customer Record Saved!");
		} catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			sessionFactory.close();
		}
	}
}
