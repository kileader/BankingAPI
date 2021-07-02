package com.kevin_leader.models;

/**
 * Javabean representing a bank account
 * @author Kevin Leader
 * @since 07/01/2021
 */
public class Account {
	
	private int id;
	private int clientId;
	private String accountName;
	private String accountType;
	private double balance;
	
	/**
	 * No-args constructor
	 */
	public Account() {}
	
	/**
	 * Full constructor
	 * @param id			unique integer for each entry
	 * @param clientId		id for the owner of the account
	 * @param accountName	name for the account
	 * @param accountType	type of account (checking, savings, etc.)
	 * @param balance		current amount of funds in USD
	 */
	public Account(int id, int clientId, String accountName, String accountType, double balance) {
		this.id = id;
		this.clientId = clientId;
		this.accountName = accountName;
		this.accountType = accountType;
		this.balance = balance;
	}
	
	/**
	 * Id-less and balance-less constructor
	 * @param clientId		id for the owner of the account
	 * @param accountName	name for the account
	 * @param accountType	type of account (checking, savings, etc.)
	 */
	public Account(int clientId, String accountName, String accountType) {
		this.clientId = clientId;
		this.accountName = accountName;
		this.accountType = accountType;
		balance = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
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

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", clientId=" + clientId + ", accountName=" + accountName + ", accountType="
				+ accountType + ", balance=" + balance + "]";
	}
	
	
}
