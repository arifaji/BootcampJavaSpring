package com.bootcamp.mavenapp.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bootcamp.mavenapp.model.Account;

public interface AccountRepository extends PagingAndSortingRepository<Account, Integer> {
	Account findByAccountNumber(int accountNumber);
}
