package com.sushil.singh.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sushil.singh.dto.CustomerDTO;

@Document(collection="Customer")
public class CustomerEntity {

	@Id
	private String id;
	private String fname;
	private String lname;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public CustomerEntity() {
		super();
	}
	
	public CustomerEntity(CustomerDTO customerDTO) {
		super();
		this.fname = customerDTO.getfName();
		this.lname = customerDTO.getlName();
	}
}
