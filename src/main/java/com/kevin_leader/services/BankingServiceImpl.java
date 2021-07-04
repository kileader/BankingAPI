package com.kevin_leader.services;

import java.util.ArrayList;
import java.util.List;

import com.kevin_leader.models.Account;
import com.kevin_leader.models.Client;
import com.kevin_leader.repositories.AccountRepo;
import com.kevin_leader.repositories.ClientRepo;

/**
 * Service operations implemented for banking api
 * @author Kevin Leader
 */
public class BankingServiceImpl implements BankingService {
	
	public ClientRepo cr;
	public AccountRepo ar;
	
	public BankingServiceImpl(ClientRepo cr, AccountRepo ar) {
		this.cr = cr;
		this.ar = ar;
	}

	@Override
	public Client getClient(int id) {
		return cr.getClient(id);
	}

	@Override
	public List<Client> getAllClients() {
		return cr.getAllClients();
	}

	@Override
	public Client addClient(Client newClient) {
		return cr.addClient(newClient);
	}

	@Override
	public Client updateClient(Client changedClient) {
		return cr.updateClient(changedClient);
	}

	@Override
	public Client deleteClient(int id) {
		return cr.deleteClient(id);
	}

	@Override
	public Account getAccount(int id) {
		return ar.getAccount(id);
	}

	@Override
	public List<Account> getAllAccounts() {
		return ar.getAllAccounts();
	}

	@Override
	public Account addAccount(Account newAccount) {
		return ar.addAccount(newAccount);
	}

	@Override
	public Account updateAccount(Account changedAccount) {
		return ar.updateAccount(changedAccount);
	}

	@Override
	public Account deleteAccount(int id) {
		return ar.deleteAccount(id);
	}
	
	@Override
	public Account withdraw(int id, double withdrawalAmount) {
		return ar.withdraw(id, withdrawalAmount);
	}

	@Override
	public Account deposit(int id, double depositAmount) {
		return ar.deposit(id, depositAmount);
	}

	@Override
	public List<Account> getAllAccountsForClient(int clientId) {
		List<Account> allAccounts = ar.getAllAccounts();
		List<Account> clientAccounts = new ArrayList<>();
		
		// Find the user's accounts
		for (Account account : allAccounts) {
			if (account.getClientId() == clientId) {
				clientAccounts.add(account);
			}
		}
		return clientAccounts;
	}
	
	@Override
	public List<Account> getAllAccountsForClientBetweenBalances(
			int clientId, int lowLimit, int highLimit) { //TODO: Fix this
		List<Account> allAccounts = ar.getAllAccounts();
		List<Account> clientAccountsBetweenLimits = new ArrayList<>();
		
		// Find the user's accounts between the limits
		for (Account account : allAccounts) {
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
		List<Account> allAccounts = ar.getAllAccounts();
		Account foundAccount = null;
		
		// Find the account matching the clientId and accountId
		for (Account account : allAccounts) {
			if (account.getClientId() == clientId 
					&& account.getId() == accountId) {
				foundAccount = account;
				break;
			}
		}
		return foundAccount;
	}

	@Override
	public Account deleteAccountForClient(int clientId, int accountId) {
		List<Account> allAccounts = ar.getAllAccounts();
		Account accountToDelete = null;
		
		for (Account account : allAccounts) {
			if (account.getClientId() == clientId 
					&& account.getId() == accountId) {
				accountToDelete = account;
				break;
			}
		}
		return ar.deleteAccount(accountToDelete.getId());
	}
	
	@Override
	public boolean transferBetweenAccounts(int clientId, int accountToSendId,
			int accountToReceiveId, double transferAmount) {
		List<Account> allAccounts = ar.getAllAccounts();
		Account accountToSend = null;
		Account accountToReceive = null;
		
		// Find the account to send and account to receive
		for (Account account : allAccounts) {
			if (clientId == account.getClientId()) {
				if (accountToSendId == account.getId()) {
					accountToSend = account;
				} else if (accountToReceiveId == account.getId()) {
					accountToReceive = account;
				} else if (accountToSend != null
						&& accountToReceive != null) {
					break;
				}
			}
		}
		
		// If at least one wasn't found, return false
		if (accountToSend == null || accountToReceive == null) {
			return false;
		}
		
		// Do withdraw and deposit methods to transfer
		ar.withdraw(accountToSendId, transferAmount);
		ar.deposit(accountToReceiveId, transferAmount);
		return true;
	}

}
