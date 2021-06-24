package com.kevin_leader.banking_api.model;

import java.util.ArrayList;

public class Client {
	
	private int id;
	private String clientEmail;
	private String password;
	private String clientName;
	private ArrayList<Account> ownedAccounts;
	
	public Client() {
		
	}
	public Client(int id, String clientEmail, String password, String clientName) {
		this.id = id;
		this.clientEmail = clientEmail;
		this.password = password;
		this.clientName = clientName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClientEmail() {
		return clientEmail;
	}
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public ArrayList<Account> getOwnedAccounts() {
		return ownedAccounts;
	}
	public void setOwnedAccounts(ArrayList<Account> ownedAccounts) {
		this.ownedAccounts = ownedAccounts;
	}
	
}
