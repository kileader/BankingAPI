package com.kevin_leader.app;
/*
package com.kevin_leader.control;

import java.util.List;

import com.kevin_leader.models.Account;
import com.kevin_leader.models.Client;

public abstract class Logic {
	
	public Logic() {

	}
	
	public static void main(String[] args) {

	}
	
	// Create new client
	public void createClient(int id, String clientEmail, String password, String clientName) {

		Client newClient = new Client(id, clientEmail, password, clientName);
	}
	
	public List<Account> findAccountsBetweenValues(Client client, float lowValue, float highValue) {
		
		List<Account> clientAccounts = client.getOwnedAccounts();
		List<Account> foundAccounts = null;
		
		for (Account account : clientAccounts) {
			if (account.getBalance() > lowValue && account.getBalance() < highValue) {
				foundAccounts.add(account);
			}
		}
		
		return foundAccounts;
	}
	
	
	
}
*/