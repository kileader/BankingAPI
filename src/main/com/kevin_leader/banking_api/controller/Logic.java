package main.com.kevin_leader.banking_api.controller;

import java.util.ArrayList;

import com.kevin_leader.banking_api.model.Account;
import com.kevin_leader.banking_api.model.Client;

@SuppressWarnings("null")
public class Logic {
	
	// Create new client
	public void createClient(int id, String clientEmail, String password, String clientName) {
		Client newClient = new Client(id, clientEmail, password, clientName);
	}
	
//	// Get all clients
//	public Client[] getAllClients() {
//		return
//	}
	
	// Get 
	
	public ArrayList<Account> findAccountsBetweenValues(Client client, float lowValue, float highValue) {
		
		ArrayList<Account> clientAccounts = client.getOwnedAccounts();
		ArrayList<Account> foundAccounts = null;
		
		for (Account account : clientAccounts) {
			if (account.getBalance() > lowValue && account.getBalance() < highValue) {
				foundAccounts.add(account);
			}
		}
		
		return foundAccounts;
	}
	
	
	
}
