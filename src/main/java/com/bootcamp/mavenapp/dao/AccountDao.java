package com.bootcamp.mavenapp.dao;
import java.util.List;

import com.bootcamp.mavenapp.model.Account;

public interface AccountDao {
	Account getById (int account_id) throws Exception;
	Account save(Account account) throws Exception;
	void delete(Account account) throws Exception;	
	List<Account> getList() throws Exception;

}