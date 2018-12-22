package com.bootcamp.mavenapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.mavenapp.dao.AccountDao;
import com.bootcamp.mavenapp.dao.CustomerDao;
import com.bootcamp.mavenapp.model.Account;
import com.bootcamp.mavenapp.model.Customer;

@RestController
@RequestMapping("/test")
public class ControllerAccount {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private AccountDao accountDao;
	
	//Start /hello
		@GetMapping("/account")
		public String account(@RequestParam(value="id", defaultValue="") String id) {
			try {
				Account account = accountDao.getById(Integer.valueOf(id));
				if (account == null) {
					return "data tidak ditemukan";
				} else {
					return "hello, your account "+account.getAccountNumber();
				}
			} catch (NumberFormatException e) {
				return "salah format input";
			} catch (Exception e) {
				return String.format("terjadi kesalahan system : %s", e.getMessage());
			}
		}
		//End /hello
		
		//Get List Account
		@GetMapping("/accounts")
		public List<Account> getList() throws Exception{
			List<Account> list = accountDao.getList();
			return list;
		}
		//End Get List Account
		
//======================================================================================	
		//Start  /post Save-Create --!> 
		/* Noted : input di postman JSON harus lengkap sesuai dg database, 
		 * mulai dari : firstName, lastName, birthData, username, password, phoneNumber, phoneType
		 */	
		@PostMapping("/account")
		public Account postAccount(@RequestBody Account account) throws Exception {
			Account data = accountDao.save(account);
			return data;
		}
		//End /post Save-Create
		
		//Update Customer
		@PutMapping("/account")
		public Account accountupdate(@RequestBody Account account) throws Exception {
			Account update = accountDao.save(account);
			return update;
		}
		//EndUpdateCustomer
		
		//Delete
		@DeleteMapping("/account/{id}")
		public void accountDelete(@PathVariable ("id") Account data) throws Exception {
			accountDao.delete(data);
		}
		//End Delete
	//==== tambahan dari bapakya
		@GetMapping(value="/accountlist")
		public List<Account> getList(@RequestParam(name="customer", defaultValue="") String customer) throws NumberFormatException, Exception{
			if(!StringUtils.isEmpty(customer)) {
				Customer checkCustomer = customerDao.getById(Integer.parseInt(customer));
				if(checkCustomer==null) {
					throw new Exception("customer tidak ditemukan");
				}
				return accountDao.getListByCustomer(checkCustomer);
			}else {
				return accountDao.getList();
			}
		}


}
