package com.kevin_leader.models.json_objects;

import org.apache.log4j.Logger;

/**
 * Class for an amount to be mapped from JSON
 * @author Kevin Leader
 */
public class Amount {
	
	private static final Logger log = Logger.getLogger(Amount.class);
	private double amount;
	
	public Amount(double amount) {
		log.info("Instantiate Amount(amount)");
		this.amount = amount;
	}
	
	public double getAmount() {
		log.info("Get amount");
		return amount;
	}
	
}