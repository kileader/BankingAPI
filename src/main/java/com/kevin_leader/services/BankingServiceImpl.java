package com.kevin_leader.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.kevin_leader.models.database.Account;
import com.kevin_leader.models.database.Client;
import com.kevin_leader.repositories.AccountRepo;
import com.kevin_leader.repositories.ClientRepo;

/**
 * Service operations implemented for banking api
 * @author Kevin Leader
 */
public class BankingServiceImpl implements BankingService {
	
	private static final Logger log =
			Logger.getLogger(BankingServiceImpl.class);
	public ClientRepo cr;
	public AccountRepo ar;
	
	public BankingServiceImpl(ClientRepo cr, AccountRepo ar) {
		log.info("Instantiate BankingServiceImpl");
		this.cr = cr;
		this.ar = ar;
	}

	@Override
	public Client getClient(int id) {
		log.debug("Run getClient(id)");
		return cr.getClient(id);
	}

	@Override
	public List<Client> getAllClients() {
		log.debug("Run getAllClients()");
		return cr.getAllClients();
	}

	@Override
	public Client addClient(Client newClient) {
		log.debug("Run addClient(newClient)");
		return cr.addClient(newClient);
	}

	@Override
	public Client updateClient(Client changedClient) {
		log.debug("Run updateClient(changedClient)");
		return cr.updateClient(changedClient);
	}

	@Override
	public Client deleteClient(int id) {
		log.debug("Run deleteClient(id)");
		return cr.deleteClient(id);
	}
	
	@Override
	public Account withdraw(int id, double withdrawalAmount) {
		log.info("Run withdraw(id, withdrawalAmount)");
		double balance = ar.getAccount(id).getBalance();
		if (balance < withdrawalAmount) {
			return null;
		}
		return ar.withdraw(id, withdrawalAmount);
	}

	@Override
	public Account deposit(int id, double depositAmount) {
		log.debug("Run deposit(id, depositAmount");
		return ar.deposit(id, depositAmount);
	}

	@Override
	public List<Account> getAllAccountsForClient(int clientId) {
		log.info("Run getAllAccountsForClient(clientId)");
		List<Account> clientAccounts = new ArrayList<>();
		// Find the user's accounts
		for (Account account : ar.getAllAccounts()) {
			if (account.getClientId() == clientId) {
				clientAccounts.add(account);
			}
		}
		return clientAccounts;
	}
	
	@Override
	public List<Account> getAllAccountsForClientBetweenBalances(
			int clientId, int lowLimit, int highLimit) {
		log.info("Run getAllAccountsForClientBetweenBalances("
				+ "clientId, lowLimit, highLimit)");
		List<Account> clientAccountsBetweenLimits = new ArrayList<>();
		// Find the user's accounts between the limits
		for (Account account : ar.getAllAccounts()) {
			if (account.getClientId() == clientId
					&& account.getBalance() < (double) highLimit
					&& account.getBalance() > (double) lowLimit) {
				clientAccountsBetweenLimits.add(account);
			}
		}
		return clientAccountsBetweenLimits;
	}
	
	@Override
	public Account getAccountForClient(int clientId, int accountId) {
		log.info("Run getAccountForClient(clientId, accountId)");
		// Find the account matching the clientId and accountId
		for (Account account : ar.getAllAccounts()) {
			if (account.getClientId() == clientId 
					&& account.getId() == accountId) {
				 return account;
			}
		}
		return null;
	}
	
	@Override
	public Account addAccountForClient(Account accountToAdd) {
		log.info("Run getAccountForClient(accountToAdd)");
		for (Client client : cr.getAllClients()) {
			if (accountToAdd.getClientId() == client.getId()) {
				return ar.addAccount(accountToAdd);
			}
		}
		return null;
	}
	
	@Override
	public Account updateAccountForClient(Account accountToUpdate) {
		log.info("Run updateAccountForClient(accountToUpdate)");
		// Find the account matching the clientId and accountId
		for (Account account : ar.getAllAccounts()) {
			if (account.getClientId() == accountToUpdate.getClientId() 
					&& account.getId() == accountToUpdate.getId()) {
				return ar.updateAccount(accountToUpdate);
			}
		}
		return null;		
	}

	@Override
	public Account deleteAccountForClient(int clientId, int accountId) {
		log.info("Run deleteAccountForClient(clientId, accountId)");
		List<Account> allAccounts = ar.getAllAccounts();
		Account accountToDelete = null;
		
		for (Account account : allAccounts) {
			if (account.getClientId() == clientId 
					&& account.getId() == accountId) {
				accountToDelete = account;
				return ar.deleteAccount(accountToDelete.getId());
			}
		}
		return null;
	}
	
}
