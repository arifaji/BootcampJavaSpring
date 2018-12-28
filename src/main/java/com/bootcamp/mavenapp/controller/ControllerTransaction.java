package com.bootcamp.mavenapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.mavenapp.dao.TransactionDao;
import com.bootcamp.mavenapp.exceptions.UniversalException;
import com.bootcamp.mavenapp.model.Transaction;

@RestController
@RequestMapping("/test")
public class ControllerTransaction {
	
	@Autowired
	private TransactionDao transactionDao;
	
	//Start /hello
			@GetMapping("/transaction")
			public String transactionview(@RequestParam(value="id", defaultValue="") String id) {
				try {
					Transaction transaction = transactionDao.getById(Integer.valueOf(id));
					if (transaction == null) {
						return "data tidak ditemukan";
					} else {
						return "hello, your amount "+transaction.getAmount();
					}
				} catch (NumberFormatException e) {
					return "salah format input";
				} catch (Exception e) {
					return String.format("terjadi kesalahan system : %s", e.getMessage());
				}
			}
			//End /hello
	
	//Get List Transaction
	@GetMapping("/transactions")
	public List<Transaction> getList() throws UniversalException{
		
		return transactionDao.getList();
	}
//End Get List Account
//======================================================================================	
	//Start  /post Save-Create --!> 
	/* Noted : input di postman JSON harus lengkap sesuai dg database, 
	 * mulai dari : firstName, lastName, birthData, username, password, phoneNumber, phoneType
	 */	
	@PostMapping("/transaction")
	public Transaction postTransaction(@RequestBody Transaction transaction) throws UniversalException {
		return  transactionDao.save(transaction);
	}
	//End /post Save-Create
	
	//Delete
	@DeleteMapping("/transaction/{id}")
	public void transactionDelete(@PathVariable ("id") Transaction data) throws UniversalException {
		transactionDao.delete(data);
	}
	//End Delete
	
	//Update Customer
	@PutMapping("/transaction")
	public Transaction transactionupdate(@RequestBody Transaction transaction) throws UniversalException {
		
		return transactionDao.save(transaction);
	}
	//EndUpdateCustomer

}
