package com.sushil.singh.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sushil.singh.Model.CustomerEntity;

public interface MongoOperationsRepository extends MongoRepository<CustomerEntity, String>{
	
	CustomerEntity save(CustomerEntity customerEntity);
	
	List<CustomerEntity> findAll();
	
	List<CustomerEntity> findByfname(String fname);
	
	List<CustomerEntity> findByFnameAndLname(String fname, String lname);
}
