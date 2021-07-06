package com.kevin_leader.models.json_objects;

import org.apache.log4j.Logger;

/**
 * Transfer object to be returned after a transfer
 */
public class Transfer {
	
	private static final Logger log = Logger.getLogger(Transfer.class);
	private double accountFromBalance;
	private double accountToBalance;
	
	public Transfer(double accountFromBalance, double accountToBalance) {
		log.info("Instantiate Transfer(accountFromBalance, accountToBalance)");
		this.accountFromBalance = accountFromBalance;
		this.accountToBalance = accountToBalance;
	}

	public double getAccountFromBalance() {
		log.info("Get balance of account to transfer from");
		return accountFromBalance;
	}

	public double getAccountToBalance() {
		log.info("Get balance of account to transfer to");
		return accountToBalance;
	}
	
}
