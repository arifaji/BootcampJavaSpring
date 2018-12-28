package com.bootcamp.mavenapp.dao.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bootcamp.mavenapp.model.Account;
import com.bootcamp.mavenapp.model.Customer;

public interface AccountRepository extends PagingAndSortingRepository<Account, Integer> {
	Account findByAccountNumber(int accountNumber);
	
	List<Account> findByCustomer(Customer customer);

}
