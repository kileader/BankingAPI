package com.kevin_leader.repositories;

import java.util.List;

import com.kevin_leader.models.database.Account;

/**
 * Account repository to do CRUD and balance operations
 * @author Kevin
 * @since 07/01/2021
 */
public interface AccountRepo {

	/**
	 * Get account from database using id
	 * @param id the account id
	 * @return the account
	 */
	public Account getAccount(int id);
	
	/**
	 * Get all accounts from database
	 * @return all the accounts
	 */
	public List<Account> getAllAccounts();

	/**
	 * Create a new account and add it to the database
	 * @param newAccount the new account
	 * @return the new account
	 */
	public Account addAccount(Account newAccount);
	
	/**
	 * Update an account in the database
	 * @param changedAccount the changed account
	 * @return the changed account
	 */
	public Account updateAccount(Account changedAccount);

	/**
	 * Delete an account from the database
	 * @param id the id of the account to delete
	 * @return the deleted account
	 */
	public Account deleteAccount(int id);
	
	/**
	 * Withdraw money from the account's balance
	 * @param id id of the account
	 * @param withdrawalAmount the amount to withdraw
	 * @return a result message
	 */
	public Account withdraw(int id, double withdrawalAmount);
	
	/**
	 * Deposit money to the account's balance
	 * @param id id of the account
	 * @param depositAmount the amount to deposit
	 * @return a result message
	 */
	public Account deposit(int id, double depositAmount);
}
