package com.bootcamp.mavenapp.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "account")
public class Account {
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column (name = "accountnumber")
	private int accountNumber;
	
	@Column (name = "opendate")
	@Temporal(TemporalType.DATE)
//	@JsonFormat(pattern="yyyy-MM-dd")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="EST")
	private Date openDate;
	@Column (name = "balance")
	private String balance;
	
	@ManyToOne
	@JoinColumn (name = "customer_id")
	private Customer customer;

	public Account () {
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
