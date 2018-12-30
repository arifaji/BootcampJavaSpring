package com.bootcamp.mavenapp.dao;

import java.util.List;

import com.bootcamp.mavenapp.exceptions.UniversalException;
import com.bootcamp.mavenapp.model.Account;
import com.bootcamp.mavenapp.model.Transaction;

public interface TransactionDao {
	Transaction getById (int id) throws UniversalException;
	Transaction save(Transaction transaction) throws UniversalException;
	void delete(Transaction transaction) throws UniversalException;	
	List<Transaction> getList() throws UniversalException;
	
	List<Transaction> getListByAccount(Account account);

}