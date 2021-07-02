package com.kevin_leader.services;

import java.util.List;

import com.kevin_leader.models.Account;
import com.kevin_leader.models.Client;

/**
 * Service operations for banking api
 * @author Kevin Leader
 * @since 07/02/2021
 */
public interface BankingService {
	
	// These first 12 methods are the same as in the repositories package
	public Client getClient(int id);
	public List<Client> getAllClients();
	public Client addClient(Client newClient);
	public Client updateClient(Client changedClient);
	public Client deleteClient(int id);
	
	public Account getAccount(int id);
	public List<Account> getAllAccounts();
	public Account addAccount(Account newAccount);
	public Account updateAccount(Account changedAccount);
	public Account deleteAccount(int id);
	
	public double withdraw(double withdrawalAmount);
	public double deposit(double depositAmount);
	
	// These last methods use more than one model
	/**
	 * Get all of a client's accounts
	 * @param clientId the client's id
	 * @return the client's accounts
	 */
	public List<Account> getAllAccountsForClient(int clientId);
	
	/**
	 * Get all accounts for a client between two balance values
	 * @param clientId	the client's id
	 * @param lowLimit	the lowest balance for an account
	 * @param highLimit the highest balance for an account
	 * @return client's accounts between the low and high limits
	 */
	public List<Account> getAllAccountsForClientBetweenBalances(int clientId, double lowLimit, double highLimit);
	
	/**
	 * Get a specific account given the client id and account id
	 * @param clientId	the client's id
	 * @param accountId	the account's id
	 * @return the account
	 */
	public Account getAccountForClientGivenAccountId(int clientId, int accountId);
	
	/**
	 * Transfer funds between two of a client's accounts
	 * @param clientId
	 * @param accountToSendId
	 * @param accountToReceiveId
	 * @param transferAmount
	 * @return transferAmount
	 */
	public double transferBetweenAccounts(int clientId, int accountToSendId,
			int accountToReceiveId, double transferAmount);

}
