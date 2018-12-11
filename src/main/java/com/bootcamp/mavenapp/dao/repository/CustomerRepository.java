package com.bootcamp.mavenapp.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bootcamp.mavenapp.model.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer>{

}
