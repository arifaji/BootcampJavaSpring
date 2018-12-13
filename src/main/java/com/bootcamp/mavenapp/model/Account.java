package com.bootcamp.mavenapp.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column (name = "accountnumber")
	private int accountNumber;
	
	@Column (name = "opendate")
	private Date openDate;
	@Column (name = "balance")
	private String balance;
	
	@ManyToOne
	@JoinColumn (name = "customer_id")
	private Customer customer;
	
//	@Column (name = "customer_id")
//	private int customerId; 

	public Account () {}
//	public Account (int accountNumber, Date openDate, String balance, int customerId) {
//		this.accountNumber = accountNumber;
//		this.openDate = openDate;
//		this.balance = balance;
//		this.customerId = customerId;
//		//this.customer = customer;
//	}
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
//	public int getCustomerId() {
//		return customerId;
//	}
//	public void setCustomerId(int customerId) {
//		this.customerId = customerId;
//	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
	
	
	
	 
}
