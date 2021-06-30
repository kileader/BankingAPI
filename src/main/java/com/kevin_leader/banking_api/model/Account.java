package com.kevin_leader.banking_api.model;

public class Account {
	
	private int id;
	private String accountType;
	private float balance;
	
	public Account() {}
	
	public Account(int id, String accountType) {
		this.id = id;
		this.accountType = accountType;
		balance = 0f;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	
}
