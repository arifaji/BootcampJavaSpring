package com.bootcamp.mavenapp.model.dto;

import java.util.Date;

import com.bootcamp.mavenapp.model.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AccountDto {

	private int accountNumber;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date openDate;
	private String balance;
	private Customer customer;

	public AccountDto () {
		// Do nothing because of X and Y.		
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
	
	
	
	 
}
