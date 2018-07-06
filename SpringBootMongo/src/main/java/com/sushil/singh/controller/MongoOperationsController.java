package com.sushil.singh.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sushil.singh.dto.CustomerDTO;
import com.sushil.singh.service.MongoOperationsService;

@RestController
@RequestMapping("/mongo")
public class MongoOperationsController {
	
	@Autowired
	MongoOperationsService mongoOperationsService;
	
	private static final String SAVE_RECORD = "/record/save"; 
	private static final String GET_ALL_RECORDS = "/record/get/all"; 
	private static final String GET_RECORDS_BY_FNAME = "/record/get/byfname"; 
	private static final String GET_RECORDS_BY_FULL_NAME = "/record/get/byfullname";
	
	@RequestMapping(value=SAVE_RECORD, method=RequestMethod.POST)
	public void saveRecord(@RequestBody CustomerDTO customerDTO, HttpServletRequest req, HttpServletResponse res) {
		
		mongoOperationsService.saveRecord(customerDTO);
	}
	
	@RequestMapping(value=GET_ALL_RECORDS, method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getAllRecords(HttpServletRequest req, HttpServletResponse res) {
		
		List<CustomerDTO> customerDTOList = mongoOperationsService.getAllRecords();
		Map<String, Object> responseObj = new LinkedHashMap<>();
		responseObj.put("Records", customerDTOList.toString());
		return new ResponseEntity<Map<String, Object>>(responseObj, HttpStatus.OK);
	}
	
	@RequestMapping(value=GET_RECORDS_BY_FNAME, method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getRecordsByFName(@RequestParam String fname, HttpServletRequest req, HttpServletResponse res) {
		
		List<CustomerDTO> customerDTOList = mongoOperationsService.getRecordsByFName(fname);
		Map<String, Object> responseObj = new LinkedHashMap<>();
		responseObj.put("Records", customerDTOList.toString());
		return new ResponseEntity<Map<String, Object>>(responseObj, HttpStatus.OK);
	}
	
	@RequestMapping(value=GET_RECORDS_BY_FULL_NAME, method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getRecordsByFullName(@RequestParam String fname, @RequestParam String lname) {
		
		List<CustomerDTO> customerDTOList = mongoOperationsService.getRecordsByFullName(fname, lname);
		Map<String, Object> responseObj = new LinkedHashMap<>();
		responseObj.put("Records", customerDTOList.toString());
		return new ResponseEntity<Map<String, Object>>(responseObj, HttpStatus.OK);
	}
}
