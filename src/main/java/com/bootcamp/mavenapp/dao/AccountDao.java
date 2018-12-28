package com.bootcamp.mavenapp.dao;
import java.util.List;

import com.bootcamp.mavenapp.exceptions.UniversalException;
import com.bootcamp.mavenapp.model.Account;
import com.bootcamp.mavenapp.model.Customer;

public interface AccountDao {
	Account getById (int account) throws UniversalException;
	Account save(Account account) throws UniversalException;
	void delete(Account account) throws UniversalException;	
	List<Account> getList() throws UniversalException;
	
	List<Account> getListByCustomer(Customer customer) throws UniversalException;


}