package com.bootcamp.mavenapp.dao;

import java.util.List;

import com.bootcamp.mavenapp.exceptions.UniversalException;
import com.bootcamp.mavenapp.model.Customer;


public interface CustomerDao {
	Customer getById (int id) throws UniversalException;
	Customer save(Customer customer) throws UniversalException;
	void delete(Customer customer) throws UniversalException;	
	List<Customer> getList() throws UniversalException;

}
