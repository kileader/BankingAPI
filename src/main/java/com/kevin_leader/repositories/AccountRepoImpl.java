package com.kevin_leader.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.kevin_leader.models.database.Account;
import com.kevin_leader.util.JDBCConnection;

/**
 * Account repository implementation to do CRUD operations
 * @author Kevin Leader
 */
public class AccountRepoImpl implements AccountRepo {
	
	private static final Logger log = 
			Logger.getLogger(AccountRepoImpl.class);
	public static Connection conn = JDBCConnection.getConnection();
	
	/**
	 * Build an Account from ResultSet
	 * @param rs result set from executing sql
	 * @return the built account
	 * @throws SQLException
	 */
	private Account buildAccount(ResultSet rs) throws SQLException {
		log.trace("Build Account from ResultSet");
		Account account = new Account(
				rs.getInt("id"),
				rs.getInt("client_id"),
				rs.getString("account_name"),
				rs.getString("account_type"),
				rs.getDouble("balance")
				);
		return account;
	}
			
	@Override
	public Account getAccount(int id) {
		log.info("Run getAccount(id)");
		
		String sql = "SELECT * FROM accounts WHERE id = ?";
		
		try {
			// Set up prepared statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			// Execute query, store results
			ResultSet rs = ps.executeQuery();
			
			// Extract the first result
			if (rs.next()) {
				return buildAccount(rs);
			}
		} catch (SQLException e) {
			log.warn("SQL did not run properly:", e);
		}
		return null;
	}

	@Override
	public List<Account> getAllAccounts() {
		log.info("Run getAllAccounts()");
		
		String sql = "SELECT * FROM accounts ORDER BY id";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			List<Account> accounts = new ArrayList<>();
			while (rs.next()) {
				accounts.add(buildAccount(rs));
			}
			return accounts;
			
		} catch (SQLException e) {
			log.warn("SQL did not run properly:", e);
		}
		return null;
	}

	@Override
	public Account addAccount(Account newAccount) {
		log.info("Run addAccount(newAccount)");

		String sql = "INSERT INTO accounts VALUES "
				+ "(default,?,?,?,?) RETURNING *";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// Set values for the ?s
			ps.setInt(1, newAccount.getClientId());
			ps.setString(2, newAccount.getAccountName());
			ps.setString(3, newAccount.getAccountType());
			ps.setDouble(4, newAccount.getBalance());
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return buildAccount(rs);
			}
		} catch (SQLException e) {
			log.warn("SQL did not run properly:", e);
		}
		return null;
	}

	@Override
	public Account updateAccount(Account changedAccount) {
		log.info("Run updateAccount(changedAccount)");
		
		String sql = "UPDATE accounts SET client_id = ?, account_name = ?,"
				+ " account_type = ?, balance = ? WHERE id = ? RETURNING *";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, changedAccount.getClientId());
			ps.setString(2, changedAccount.getAccountName());
			ps.setString(3, changedAccount.getAccountType());
			ps.setDouble(4, changedAccount.getBalance());
			ps.setInt(5, changedAccount.getId());
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return buildAccount(rs);
			}
		} catch (SQLException e) {
			log.warn("SQL did not run properly:", e);
		}
		return null;
	}

	@Override
	public Account deleteAccount(int id) {
		log.info("Run deleteAccount(id)");
		
		String sql = "DELETE FROM accounts WHERE id = ? RETURNING *";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return buildAccount(rs);
			}
		} catch (SQLException e) {
			log.warn("SQL did not run properly:", e);
		}
		return null;
	}

	@Override
	public Account withdraw(int id, double withdrawalAmount) {
		log.info("Run withdraw(id, withdrawalAmount)");
		
		String sql = "UPDATE accounts SET balance = ? WHERE id = ?"
				+ " RETURNING *";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			try {
				ps.setDouble(1, getAccount(id).getBalance() - withdrawalAmount);
			} catch (NullPointerException e) {
				log.warn("id doesn't match any account:", e);
				return null;
			}
			
			ps.setInt(2, id);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return buildAccount(rs);
			}
			
		} catch (SQLException e) {
			log.warn("SQL did not run properly:", e);
		}
		return null;
	}

	@Override
	public Account deposit(int id, double depositAmount) {
		log.info("Run deleteAccount{id, depositAmount)");
		
		String sql = "UPDATE accounts SET balance = ? WHERE id = ?"
				+ " RETURNING *";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			try {
				ps.setDouble(1, getAccount(id).getBalance() + depositAmount);
			} catch (NullPointerException e) {
				log.warn("id doesn't match any account:", e);
				return null;
			}
			
			ps.setInt(2, id);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return buildAccount(rs);
			}
			
		} catch (SQLException e) {
			log.warn("SQL did not run properly:", e);
		}
		return null;
	}

// FAKE DB STUFF BELOW
//
//	@Override
//	public Account getAccount(int id) {
//		return FakeDB.accounts.get(id);
//	}
//
//	@Override
//	public List<Account> getAllAccounts() {
//		List<Account> accountList = new ArrayList<Account>();
//		Set<Integer> keys = FakeDB.accounts.keySet();
//		
//		for(Integer key : keys) {
//			accountList.add(FakeDB.accounts.get(key));
//		}
//		return accountList;
//	}
//
//	@Override
//	public Account addAccount(Account newAccount) {
//		// Make sure the id is right and update the count
//		newAccount.setId(++FakeDB.accountsIdCount);
//		
//		FakeDB.accounts.put(newAccount.getId(), newAccount);
//		return newAccount;
//	}
//
//	@Override
//	public Account updateAccount(Account changedAccount) {
//		FakeDB.accounts.replace(changedAccount.getId(), changedAccount);
//		return changedAccount;
//	}
//
//	@Override
//	public Account deleteAccount(int id) {
//		return FakeDB.accounts.remove(id);
//	}
//
//	@Override
//	public String withdraw(int id, double withdrawalAmount) {
//		Account account = FakeDB.accounts.get(id);
//		String result;
//		if (withdrawalAmount < 0) {
//			
//		}
//		else if (account.getBalance() > withdrawalAmount) {
//			account.setBalance(account.getBalance() - withdrawalAmount);
//			result = "Successfully withdrew $" + withdrawalAmount;
//		} else {
//			result = "Your account's balance is too low to withdraw.";
//		}
//	}
//
//	@Override
//	public String deposit(int id, double depositAmount) {
//		return null;
//	}
	
}
