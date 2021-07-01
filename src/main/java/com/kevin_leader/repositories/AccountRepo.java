package com.kevin_leader.repositories;

import java.util.List;

import com.kevin_leader.models.Account;

public interface AccountRepo {
	
	public Account getAccount(int id);
	
	public List<Account> getAllAccounts();
	
	public Account addAccount(Account newAccount);
	
	public Account updateAccount(Account changedAccount);
	
	public Account deleteAccount(Account accountToDelete);
	
}
