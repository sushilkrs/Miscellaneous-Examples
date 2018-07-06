package com.blogspot.sonevalley.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class CustomerEntity {

	@Id
	@GeneratedValue
	private long customerId;
	private String customerName;
	private String customerPhone;

	public CustomerEntity() {
		super();
	}
	public CustomerEntity(String customerName, String customerPhone) {
		super();
		this.customerName = customerName;
		this.customerPhone = customerPhone;
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
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinTable(joinColumns = @JoinColumn(name = "customerId"), inverseJoinColumns = @JoinColumn(name = "orderId"))
	private Set<OrderEntity> orders = new HashSet<>();

	public Set<OrderEntity> getOrders() {
		return orders;
	}
	public void setOrders(Set<OrderEntity> orders) {
		this.orders = orders;
	}
}
