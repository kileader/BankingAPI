package com.kevin_leader.repositories;

import java.util.List;

import com.kevin_leader.models.Account;

/**
 * Account repository to do CRUD operations
 * @author Kevin
 * @since 07/01/2021
 */
public interface AccountRepo {

	public Account getAccount(int id);

	public List<Account> getAllAccounts();

	public Account addAccount(Account newAccount);

	public Account updateAccount(Account changedAccount);

	public Account deleteAccount(int id);

}
