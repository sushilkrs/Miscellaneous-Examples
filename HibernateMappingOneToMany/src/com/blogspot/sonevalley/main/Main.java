package com.blogspot.sonevalley.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.blogspot.sonevalley.model.CustomerEntity;
import com.blogspot.sonevalley.model.OrderEntity;
import com.blogspot.sonevalley.util.HibernateUtil;

public class Main {
	
	public static void main(String[] args) {
		
		Transaction transaction = null;
		SessionFactory sessionFactory = null;
		CustomerEntity customer1 = new CustomerEntity("Sushil Kumar");
		CustomerEntity customer2 = new CustomerEntity("Vijay Pandey");
		OrderEntity order1 = new OrderEntity("Pizza");
		OrderEntity order2 = new OrderEntity("Coke");
		
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			
			session.save(customer1);
			session.save(customer2);
			
			order1.setCustomer(customer1);
			order2.setCustomer(customer1);
			session.save(order1);
			session.save(order2);
			
			transaction.commit();
			System.out.println("Record(s) Saved!");
			
		} catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			sessionFactory.close();
		}
	}
}
