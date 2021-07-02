package com.kevin_leader.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.kevin_leader.models.Account;
import com.kevin_leader.util.FakeDB;

/**
 * Account repository implementation to do CRUD operations
 * @author Kevin
 * @since 07/03/2021
 */
public class AccountRepoImpl implements AccountRepo {

	@Override
	public Account getAccount(int id) {
		return FakeDB.accounts.get(id);
	}

	@Override
	public List<Account> getAllAccounts() {
		List<Account> accountList = new ArrayList<Account>();
		Set<Integer> keys = FakeDB.accounts.keySet();
		
		for(Integer key : keys) {
			accountList.add(FakeDB.accounts.get(key));
		}
		return accountList;
	}

	@Override
	public Account addAccount(Account newAccount) {
		// Make sure the id is right and update the count
		newAccount.setId(++FakeDB.accountsIdCount);
		
		FakeDB.accounts.put(newAccount.getId(), newAccount);
		return newAccount;
	}

	@Override
	public Account updateAccount(Account changedAccount) {
		FakeDB.accounts.replace(changedAccount.getId(), changedAccount);
		return changedAccount;
	}

	@Override
	public Account deleteAccount(int id) {
		return FakeDB.accounts.remove(id);
	}

}
