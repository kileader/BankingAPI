package com.kevin_leader.banking_api.model;

public class Account {
	
	private double balance;
	
	public Account() {}

	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [balance=" + balance + "]";
	}
	
}
