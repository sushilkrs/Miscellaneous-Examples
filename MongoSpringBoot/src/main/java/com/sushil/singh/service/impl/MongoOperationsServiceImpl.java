package com.sushil.singh.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushil.singh.Model.CustomerEntity;
import com.sushil.singh.dto.CustomerDTO;
import com.sushil.singh.repository.MongoOperationsRepository;
import com.sushil.singh.service.MongoOperationsService;

@Service
public class MongoOperationsServiceImpl implements MongoOperationsService{

	@Autowired
	MongoOperationsRepository mongoOperationsRepository;
	
	public void saveRecord(CustomerDTO cutomerDTO) {
		
		CustomerEntity customerEntity = new CustomerEntity(cutomerDTO);
		mongoOperationsRepository.save(customerEntity);
	}

	@Override
	public List<CustomerDTO> getAllRecords() {

		List<CustomerEntity> customerEntityList = mongoOperationsRepository.findAll();
		
		List<CustomerDTO> customerDTOList = new ArrayList<>();
		for(CustomerEntity entity : customerEntityList) {
			customerDTOList.add(convertToDTO(entity));
		}
		return customerDTOList;
	}
	
	@Override
	public List<CustomerDTO> getRecordsByFName(String fname) {

		List<CustomerEntity> customerEntityList = mongoOperationsRepository.findByfname(fname);
		
		List<CustomerDTO> customerDTOList = new ArrayList<>();
		for(CustomerEntity entity : customerEntityList) {
			customerDTOList.add(convertToDTO(entity));
		}
		return customerDTOList;
	}
	
	private CustomerDTO convertToDTO(CustomerEntity customerEntity) {
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setfName(customerEntity.getFname());
		customerDTO.setlName(customerEntity.getLname());
		return customerDTO;
	}

	@Override
	public List<CustomerDTO> getRecordsByFullName(String fname, String lname) {
		
		List<CustomerEntity> customerEntityList = mongoOperationsRepository.findByFnameAndLname(fname, lname);
		
		List<CustomerDTO> customerDTOList = new ArrayList<>();
		for(CustomerEntity entity : customerEntityList) {
			customerDTOList.add(convertToDTO(entity));
		}
		return customerDTOList;
	}

}
