package com.bootcamp.mavenapp.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bootcamp.mavenapp.model.Transaction;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Integer> {
	Transaction findById(int id);
}
