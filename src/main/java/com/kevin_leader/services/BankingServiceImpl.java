package com.kevin_leader.services;

import java.util.ArrayList;
import java.util.List;

import com.kevin_leader.models.Account;
import com.kevin_leader.models.Client;
import com.kevin_leader.repositories.AccountRepo;
import com.kevin_leader.repositories.ClientRepo;

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
	public List<Account> getAllAccountsForClientBetweenBalances(
			int clientId, double lowLimit, double highLimit) {
		List<Account> allAccounts = ar.getAllAccounts();
		List<Account> clientAccountsBetweenLimits = new ArrayList<>();
		for (Account account : allAccounts) {
			if (account.getClientId() == clientId && account.getBalance() < highLimit
					&& account.getBalance() > highLimit) {
				clientAccountsBetweenLimits.add(account);
			}
		}
		return clientAccountsBetweenLimits;
		
	}

	@Override
	public Account getAccountForClientGivenAccountId(int clientId, int accountId) {
		List<Account> allAccounts = ar.getAllAccounts();
		Account foundAccount = null;
		for (Account account : allAccounts) {
			if (account.getClientId() == clientId && account.getId() == accountId) {
				foundAccount = account;
				break;
			}
		}
		return foundAccount;
	}

	@Override
	public double transferBetweenAccounts(int clientId, int accountToSendId,
			int accountToReceiveId, double transferAmount) {
		List<Account> allAccounts = ar.getAllAccounts();
		Account accountToSend = null;
		Account accountToReceive = null;
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
		if (accountToSend.getBalance() > transferAmount) {
			ar.withdraw(transferAmount);
			ar.deposit()
		} // TODO: design some exception handling
		return -1;
	}

}
