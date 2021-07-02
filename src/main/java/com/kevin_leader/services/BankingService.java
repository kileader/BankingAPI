package com.kevin_leader.services;

import java.util.List;

import com.kevin_leader.models.Account;

/**
 * Services for banking api
 * @author Kevin Leader
 * @since 07/02/2021
 */
public interface BankingService {
	
	// These first methods are trivial CRUD
	/**
	 * 
	 * @param newAccount
	 * @return
	 */
	public Account addAccount(Account newAccount);
	
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
	public List<Account> getAllAccountsBetweenBalances(int clientId, double lowLimit, double highLimit);
	
	public 

}
