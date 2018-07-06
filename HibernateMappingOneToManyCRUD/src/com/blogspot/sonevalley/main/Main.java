package com.blogspot.sonevalley.main;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.blogspot.sonevalley.model.CustomerEntity;
import com.blogspot.sonevalley.model.OrderEntity;
import com.blogspot.sonevalley.util.HibernateUtil;

public class Main {
	
	private static SessionFactory sessionFactory;
	public static void main(String[] args) {
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
		} catch(Exception e) {
			e.printStackTrace();
		}
		Main mainApp = new Main();
		
		Set<OrderEntity> orders = new HashSet<>();
		orders.add(new OrderEntity("Pizza"));
		orders.add(new OrderEntity("Coke"));
		orders.add(new OrderEntity("Snacks"));
		
		Set<OrderEntity> orders1 = new HashSet<>();
		orders1.add(new OrderEntity("Egg"));
		orders1.add(new OrderEntity("Bread"));
		orders1.add(new OrderEntity("Snacks"));
		
		System.out.println("Saving Recordings... ");
		long customerId1 = mainApp.saveRecords("Sushil Kumar", "1234567890", orders);
		long customerId2 = mainApp.saveRecords("Vijay Kumar", "1234567891", orders1);
		
		System.out.println("Displaying Records...");
		mainApp.getRecords();
		
		System.out.println("Updating Recordings... ");
		mainApp.updateRecord(customerId1, "9876543210");
		
		System.out.println("Displaying Records...");
		mainApp.getRecords();
		
		System.out.println("Deleting 'Vijay Kumar' Record...");
		mainApp.deleteRecord(customerId2);
		
		System.out.println("Displaying Records...");
		mainApp.getRecords();
	}
	
	private long saveRecords(String customerName, String customerPhone, Set<OrderEntity> orders) {

		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		long customerId = 0;
		
		try {
			transaction = session.beginTransaction();
			CustomerEntity customer = new CustomerEntity(customerName, customerPhone);
			customer.setOrders(orders);
			customerId = (long) session.save(customer);
			
			transaction.commit();
			
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return customerId;
	}
		
	private void getRecords() {
		
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			
			transaction = session.beginTransaction();
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<CustomerEntity> criteria = builder.createQuery(CustomerEntity.class);
			Root<CustomerEntity> customerRoot = criteria.from(CustomerEntity.class);
			List<CustomerEntity> customers = session.createQuery(criteria.select(customerRoot)).getResultList();
			
			// Printing Records to Console
			for(CustomerEntity customer : customers) {
				for(OrderEntity order : customer.getOrders()) {
					System.out.println(customer.getCustomerId()+" | "+customer.getCustomerName()+" | "+customer.getCustomerPhone()+" | "+order.getOrderName());
				}
			}
			transaction.commit();
			
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	private void updateRecord(long customerId, String customerPhone) {

		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		
		try {
			transaction = session.beginTransaction();
			CustomerEntity customer = session.get(CustomerEntity.class, customerId);
			customer.setCustomerPhone(customerPhone);
			
			session.update(customer);
			transaction.commit();
			
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	private void deleteRecord(long customerId) {

		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		
		try {
			transaction = session.beginTransaction();
			CustomerEntity customer = session.get(CustomerEntity.class, customerId);
			session.delete(customer);
			transaction.commit();
			
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
