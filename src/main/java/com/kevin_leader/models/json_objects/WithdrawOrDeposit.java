package com.kevin_leader.models.json_objects;

import org.apache.log4j.Logger;

/**
 * Class for an amount of withdrawal/deposit to be mapped from JSON
 * @author Kevin Leader
 */
public class WithdrawOrDeposit {
	
	private static final Logger log = 
			Logger.getLogger(WithdrawOrDeposit.class);
	private double withdraw;
	private double deposit;
	
	public WithdrawOrDeposit(double withdraw, double deposit) {
		log.info("Instantiate WithdrawOrDeposit(withdraw, deposit)");
		this.withdraw = withdraw;
		this.deposit = deposit;
	}

	public double getWithdraw() {
		log.info("Get withdrawal amount");
		return withdraw;
	}

	public double getDeposit() {
		log.info("Get deposit amount");
		return deposit;
	}
	
}