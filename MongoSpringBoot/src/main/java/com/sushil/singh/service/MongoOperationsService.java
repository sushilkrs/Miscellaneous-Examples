package com.sushil.singh.service;

import java.util.List;

import com.sushil.singh.dto.CustomerDTO;

public interface MongoOperationsService {

	public void saveRecord(CustomerDTO customerDTO);
	
	public List<CustomerDTO> getAllRecords();
	
	public List<CustomerDTO> getRecordsByFName(String fname);
	
	public List<CustomerDTO> getRecordsByFullName(String fname, String lname);
}
