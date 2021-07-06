package com.kevin_leader.services;

import java.util.List;

import com.kevin_leader.models.database.Account;
import com.kevin_leader.models.database.Client;

/**
 * Service operations for banking api
 * @author Kevin Leader
 * @since 07/02/2021
 */
public interface BankingService {
	
	public Client getClient(int id);
	public List<Client> getAllClients();
	public Client addClient(Client newClient);
	public Client updateClient(Client changedClient);
	public Client deleteClient(int id);
	public Account deposit(int id, double depositAmount);
	
	/**
	 * Check for insufficient funds then withdraw
	 * @param id
	 * @param withdrawalAmount
	 * @return
	 */
	public Account withdraw(int id, double withdrawalAmount);
	
	/**
	 * Check for client existence then add an account
	 * @param accountToAdd
	 * @return the account added
	 */
	public Account addAccountForClient(Account accountToAdd);
	
	/**
	 * Get all accounts for a client
	 * @param clientId	the client's id
	 * @return client's accounts
	 */
	public List<Account> getAllAccountsForClient(int clientId);
	
	/**
	 * Get all accounts for a client between two balance values
	 * @param clientId	the client's id
	 * @param lowLimit	the lowest balance for an account
	 * @param highLimit the highest balance for an account
	 * @return client's accounts between the low and high limits
	 */
	public List<Account> getAllAccountsForClientBetweenBalances(
			int clientId, int lowLimit, int highLimit);
	
	/**
	 * Get a specific account given the client id and account id
	 * @param clientId	the client's id
	 * @param accountId	the account's id
	 * @return the account
	 */
	public Account getAccountForClient(int clientId, int accountId);
	
	/**
	 * Check for id existence then update an account
	 * @param accountToUpdate the changed account to update
	 * @return the updated account
	 */
	public Account updateAccountForClient(Account accountToUpdate);
	
	/**
	 * Delete a specific account given the client id and account id
	 * @param clientId
	 * @param accountId
	 * @return the account
	 */
	public Account deleteAccountForClient(int clientId, int accountId);

}
