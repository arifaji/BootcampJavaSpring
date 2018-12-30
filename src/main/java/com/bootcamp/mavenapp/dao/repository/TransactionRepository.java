package com.bootcamp.mavenapp.dao.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bootcamp.mavenapp.model.Account;
import com.bootcamp.mavenapp.model.Transaction;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Integer> {
	Transaction findById(int id);

	List<Transaction> findByAccount(Account account);
}
