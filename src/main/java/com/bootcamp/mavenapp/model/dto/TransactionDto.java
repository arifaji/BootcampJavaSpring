package com.bootcamp.mavenapp.model.dto;

import com.bootcamp.mavenapp.model.Account;

public class TransactionDto {

	private int id;
	private String type;
	private String amount;
	private String amountsign;
	private Account account;
	
	public TransactionDto () {
		// Do nothing because of X and Y.
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAmountsign() {
		return amountsign;
	}

	public void setAmountsign(String amountsign) {
		this.amountsign = amountsign;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	
}
