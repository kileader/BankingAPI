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
	// Create static instance of allAccounts to use when
	// querying the database is unnecessary
	private static List<Account> allAccounts;
	private static boolean shouldRefreshAllAccounts;
	
	public BankingServiceImpl(ClientRepo cr, AccountRepo ar) {
		log.info("Instantiate BankingServiceImpl");
		this.cr = cr;
		this.ar = ar;
		allAccounts = new ArrayList<>();
		shouldRefreshAllAccounts = true;
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
		
		Client deletedClient = cr.deleteClient(id);
		
		if (deletedClient == null) {
			return null;
		} else {
			shouldRefreshAllAccounts = true;
			return deletedClient;
		}
	}
	
	@Override
	public Account withdraw(int id, double withdrawalAmount) {
		log.info("Run withdraw(id, withdrawalAmount)");
		
		double balance = ar.getAccount(id).getBalance();
		if (balance < withdrawalAmount) {
			return null;
		}
		
		Account accountAfterWithdraw = ar.withdraw(id, withdrawalAmount);
		
		if (accountAfterWithdraw == null) {
			return null;
		} else {
			shouldRefreshAllAccounts = true;
			return accountAfterWithdraw;
		}
	}

	@Override
	public Account deposit(int id, double depositAmount) {
		log.debug("Run deposit(id, depositAmount");
		
		Account accountAfterDeposit = ar.deposit(id, depositAmount);

		if (accountAfterDeposit == null) {
			return null;
		} else {
			shouldRefreshAllAccounts = true;
			return accountAfterDeposit;
		}
	}
	
	@Override
	public List<Account> getAllAccountsForClient(int clientId) {
		log.info("Run getAllAccountsForClient(clientId)");
		
		List<Account> clientAccounts = new ArrayList<>();
		
		if (shouldRefreshAllAccounts) {
			allAccounts = ar.getAllAccounts();
			shouldRefreshAllAccounts = false;
		}
		
		for (Account account : allAccounts) {
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
		
		if (shouldRefreshAllAccounts) {
			allAccounts = ar.getAllAccounts();
			shouldRefreshAllAccounts = false;
		}
		
		// Find accounts matching the clientId and between the limits
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
		log.info("Run getAccountForClient(clientId, accountId)");
		
		if (shouldRefreshAllAccounts) {
			allAccounts = ar.getAllAccounts();
			shouldRefreshAllAccounts = false;
		}
		
		// Find the account matching the clientId and accountId
		for (Account account : allAccounts) {
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
		
		Account addedAccount = null;
		for (Client client : cr.getAllClients()) {
			if (accountToAdd.getClientId() == client.getId()) {
				addedAccount = ar.addAccount(accountToAdd);
				break;
			}
		}
		
		if (addedAccount == null) {
			return null;
		} else {
			shouldRefreshAllAccounts = true;
			return addedAccount;
		}
	}
	
	@Override
	public Account updateAccountForClient(Account accountToUpdate) {
		log.info("Run updateAccountForClient(accountToUpdate)");
		
		if (shouldRefreshAllAccounts) {
			allAccounts = ar.getAllAccounts();
			shouldRefreshAllAccounts = false;
		}
		
		// Find the account matching the clientId and accountId
		for (Account account : allAccounts) {
			if (account.getClientId() == accountToUpdate.getClientId() 
					&& account.getId() == accountToUpdate.getId()) {
				
				Account updatedAccount = ar.updateAccount(accountToUpdate);
				
				if (updatedAccount == null) {
					return null;
				} else {
					shouldRefreshAllAccounts = true;
					return updatedAccount;
				}
				
			}
		}
		return null;		
	}

	@Override
	public Account deleteAccountForClient(int clientId, int accountId) {
		log.info("Run deleteAccountForClient(clientId, accountId)");
		
		if (shouldRefreshAllAccounts) {
			allAccounts = ar.getAllAccounts();
			shouldRefreshAllAccounts = false;
		}
		
		for (Account account : allAccounts) {
			if (account.getClientId() == clientId 
					&& account.getId() == accountId) {
				Account deletedAccount = ar.deleteAccount(account.getId());
				if (deletedAccount == null) {
					return null;
				} else {
					shouldRefreshAllAccounts = true;
					return deletedAccount;
				}				
			}
		}
		return null;
	}
	
}
