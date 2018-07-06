package com.blogspot.sonevalley.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class CustomerEntity {
	
	@Id
	@GeneratedValue
	private long customerId;
	private String customerName;
	
	
	public CustomerEntity(String customerName) {
		super();
		this.customerName = customerName;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	@OneToMany(targetEntity = OrderEntity.class, mappedBy = "customer", fetch=FetchType.EAGER)
	private Set<OrderEntity> orders = new HashSet<>();
	public Set<OrderEntity> getOrders() {
		return orders;
	}
	public void setOrders(Set<OrderEntity> orders) {
		this.orders = orders;
	}
	
	
	

}
