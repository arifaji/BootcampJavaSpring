package com.bootcamp.mavenapp.dao;

import java.util.List;

import com.bootcamp.mavenapp.model.Transaction;

public interface TransactionDao {
	Transaction getById (int id) throws Exception;
	Transaction save(Transaction transaction) throws Exception;
	void delete(Transaction transaction) throws Exception;	
	List<Transaction> getList() throws Exception;

}