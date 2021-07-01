package com.kevin_leader.repositories;

import java.util.List;

import com.kevin_leader.models.Account;

public class GenericRepoImpl<T> implements AccountRepo, ClientRepo {
	
	private Class<T> type;
	
	public GenericRepoImpl(Class<T> type) {
		this.type = type;
	}
	
	public Account getAccount(int id);
	
	public List<Account> getAllAccounts();
	
	public Account addAccount(Account newAccount)(T entity) {
		
	}
	
	public Account updateAccount(Account changedAccount);
	
	public Account deleteAccount(Account accountToDelete);
	
}
