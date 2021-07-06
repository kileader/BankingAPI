package com.kevin_leader.controllers;

import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.kevin_leader.models.database.Account;
import com.kevin_leader.models.database.Client;
import com.kevin_leader.models.json_objects.Amount;
import com.kevin_leader.models.json_objects.Transfer;
import com.kevin_leader.models.json_objects.WithdrawOrDeposit;
import com.kevin_leader.services.BankingService;

import io.javalin.http.Handler;

/**
 * Manages application logic (endpoints) for the banking api
 * @author Kevin Leader
 */
public class BankingController {

	final static Logger log = Logger.getLogger(BankingController.class);
	BankingService bs;
	Gson gson = new Gson();
	
	public BankingController(BankingService bs) {
		log.info("Instantiate BankingController");
		this.bs = bs;
	}
	
	public Handler addClient = (ctx) -> {
		log.info("Start Handler addClient");
		
		// Create client from JSON
		Client c = gson.fromJson(ctx.body(), Client.class);
		// Add the client to the database
		c = bs.addClient(c);
		// Set result to the client if successful,
		// otherwise set to empty object
		if (c != null) {
			ctx.result(gson.toJson(c));
			ctx.status(201);
		} else {
			ctx.result("{}");
			ctx.status(400);
		}
//		ctx.result((c != null) ? gson.toJson(c) : "{}");
//		ctx.status(201);
	};
	
	public Handler getAllClients = (context) -> {
		log.info("Start Handler getAllClients");
		
		List<Client> clients = bs.getAllClients();
		if (clients != null) {
			context.result(gson.toJson(clients));
			context.status(200);
		} else {
			context.result("{}");
			context.status(404);
		}
	};
	
	public Handler getClientById = (ctx) -> {
		log.info("Start Handler getClientById");
		
		// Set input
		String input = ctx.pathParam("id");
		int id;
		// Check if input is int
		try {
			id = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			id = -1;
			log.warn("id is not an int:", e);
		}
		// Get client by id
		Client client = bs.getClient(id);
		// Get resulting json and http status
		if (client != null) {
			ctx.result(gson.toJson(client));
			ctx.status(200);
		} else {
			ctx.result("{}");
			ctx.status(404);
		}
	};
	
	public Handler updateClientById = (ctx) -> {
		log.info("Start Handler updateClientById");
		
		String input = ctx.pathParam("id");
		int clientId;
		
		try {
			clientId = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			clientId = -1;
			log.warn("clientId is not an int:", e);
		}
		
		Client c = gson.fromJson(ctx.body(), Client.class);
		c.setId(clientId);
		
		c = bs.updateClient(c);
		if (c != null) {
			ctx.result(gson.toJson(c));
			ctx.status(200);
		} else {
			ctx.result("{}");
			ctx.status(404);	
		}
	};
	
	public Handler deleteClientById = (ctx) -> {
		log.info("Start Handler deleteClientById");
		
		String input = ctx.pathParam("id");
		int id;
		try {
			id = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			id = -1;
			log.warn("id is not an int:", e);
		}
		// Delete client in database and return the deleted client
		Client client = bs.deleteClient(id);
		if (client != null) {
			ctx.status(204);
		} else {
			ctx.status(404);
		}
	};
	
	public Handler addAccountForClient = (ctx) -> {
		log.info("Start Handler addAccountForClient");
		
		String input = ctx.pathParam("id");
		int clientId;
		try {
			clientId = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			clientId = -1;
			log.warn("clientId is not an int:", e);
		}
		// Create account from JSON
		Account a = gson.fromJson(ctx.body(), Account.class);
		a.setClientId(clientId);
		a = bs.addAccountForClient(a);
		// Set result to the account if successful,
		// otherwise set to empty object
		if (a != null) {
			ctx.result(gson.toJson(a));
			ctx.status(201);
		} else {
			ctx.result("{}");
			ctx.status(400);
		}
	};
	
	public Handler getAccountsForClient = (ctx) -> {
		log.info("Start Handler getAccountsForClient");
		
		String idIn = ctx.pathParam("id");
		String maxBalanceIn = ctx.queryParam("amountLessThan");
		String minBalanceIn = ctx.queryParam("amountGreaterThan");
		
		int clientId;
		try {
			clientId = Integer.parseInt(idIn);
		} catch (NumberFormatException e) {
			clientId = -1;
			log.warn("clientId is not an int:", e);
		}
		
		int maxBalance;
		int minBalance;
		if (maxBalanceIn != null && minBalanceIn != null) {
			try {
				maxBalance = Integer.parseInt(maxBalanceIn);
			} catch (NumberFormatException e) {
				maxBalance = -1;
				log.warn("maxBalance is not an int:", e);
			}
			try {
				minBalance = Integer.parseInt(minBalanceIn);
			} catch (NumberFormatException e) {
				minBalance = -1;
				log.warn("minBalance is not an int:", e);
			}
			List<Account> accounts = bs.getAllAccountsForClientBetweenBalances(
					clientId, minBalance, maxBalance);
			if (accounts != null && !accounts.isEmpty()) {
				ctx.result(gson.toJson(accounts));
				ctx.status(200);
			} else {
				ctx.result("{}");
				ctx.status(404);
			}
		} else if (clientId == -1) {
			ctx.result("{}");
			ctx.status(404);
		} else {
			List<Account> accounts = bs.getAllAccountsForClient(clientId);
			
			if (accounts != null) {
				ctx.result(gson.toJson(accounts));
				ctx.status(200);
			} else {
				ctx.result("{}");
				ctx.status(404);
			}
		}
		
	};
	
	public Handler getAllAccountsForClientBetweenBalances = (ctx) -> {
		log.info("Start Handler getAllAccountsForClientBetweenBalances");
		
		String idIn = ctx.pathParam("id");
		String maxBalanceIn = ctx.splat(0);
		String minBalanceIn = ctx.splat(1);
		
		int clientId;
		try {
			clientId = Integer.parseInt(idIn);
		} catch (NumberFormatException e) {
			clientId = -1;
			log.warn("clientId is not an int:", e);
		}
		int maxBalance;
		try {
			maxBalance = Integer.parseInt(maxBalanceIn);
		} catch (NumberFormatException e) {
			maxBalance = -1;
			log.warn("maxBalance is not an int:", e);
		}
		int minBalance;
		try {
			minBalance = Integer.parseInt(minBalanceIn);
		} catch (NumberFormatException e) {
			minBalance = -1;
			log.warn("minBalance is not an int:", e);
		}
		
		List<Account> accounts = bs.getAllAccountsForClientBetweenBalances(
				clientId, minBalance, maxBalance);
		if (accounts != null) {
			ctx.result(gson.toJson(accounts));
			ctx.status(200);
		} else {
			ctx.result("{}");
			ctx.status(404);
		}
	};
	
	public Handler getAccountForClient = (ctx) -> {
		log.info("Start Handler getAccountForClient");
		
		String cIdInput = ctx.pathParam("cId");
		String aIdInput = ctx.pathParam("aId");
		
		int clientId;
		try {
			clientId = Integer.parseInt(cIdInput);
		} catch (NumberFormatException e) {
			clientId = -1;
			log.warn("clientId is not an int:", e);
		}
		
		int accountId;
		try {
			accountId = Integer.parseInt(aIdInput);
		} catch (NumberFormatException e) {
			accountId = -1;
			log.warn("accountId is not an int:", e);
		}
		
		Account a = bs.getAccountForClient(clientId, accountId);
		if (a != null) {
			ctx.result(gson.toJson(a));
			ctx.status(200);
		} else {
			ctx.result("{}");
			ctx.status(404);
		}
	};
	
	public Handler updateAccountForClient = (ctx) -> {
		log.info("Start Handler updateAccountForClient");
		
		String cIdInput = ctx.pathParam("cId");
		String aIdInput = ctx.pathParam("aId");
		
		Account a = gson.fromJson(ctx.body(), Account.class);
		
		int clientId;
		try {
			clientId = Integer.parseInt(cIdInput);
		} catch (NumberFormatException e) {
			clientId = -1;
			log.warn("clientId is not an int:", e);
		}
		
		int accountId;
		try {
			accountId = Integer.parseInt(aIdInput);
		} catch (NumberFormatException e) {
			accountId = -1;
			log.warn("accountId is not an int:", e);
		}

		a.setId(accountId);
		a.setClientId(clientId);
		
		a = bs.updateAccountForClient(a);
		
		if (a != null) {
			ctx.result(gson.toJson(a));
			ctx.status(200);
		} else {
			ctx.result("{}");
			ctx.status(404);	
		}
	};
	
	public Handler deleteAccountForClient = (ctx) -> {
		log.info("Start Handler deleteAccountForClient");
		
		String cIdInput = ctx.pathParam("cId");
		String aIdInput = ctx.pathParam("aId");
		
		int clientId;
		try {
			clientId = Integer.parseInt(cIdInput);
		} catch (NumberFormatException e) {
			clientId = -1;
			log.warn("clientId is not an int:", e);
		}
		
		int accountId;
		try {
			accountId = Integer.parseInt(aIdInput);
		} catch (NumberFormatException e) {
			accountId = -1;
			log.warn("accountId is not an int:", e);
		}
		
		Account a = bs.deleteAccountForClient(clientId, accountId);
		
		if (a != null) {	
			ctx.status(204);
		} else {
			ctx.status(404);
		}
	};
	
	public Handler withdrawOrDeposit = (ctx) -> {
		log.info("Start Handler withdrawOrDeposit");
		
		String cIdInput = ctx.pathParam("cId");
		String aIdInput = ctx.pathParam("aId");
		
		int clientId;
		try {
			clientId = Integer.parseInt(cIdInput);
		} catch (NumberFormatException e) {
			clientId = -1;
			log.warn("Clientd is not an int:", e);
		}
		int accountId;
		try {
			accountId = Integer.parseInt(aIdInput);
		} catch (NumberFormatException e) {
			accountId = -1;
			log.warn("accountId is not an int:", e);
		}
		
		Account a = bs.getAccountForClient(clientId, accountId);
		
		if (a != null) {
			
			WithdrawOrDeposit wd = gson.fromJson(
					ctx.body(), WithdrawOrDeposit.class);
			
			if (wd != null) {
				if (wd.getDeposit() == 0) {
					a = bs.withdraw(accountId, wd.getWithdraw());
				} else {
					a = bs.deposit(accountId, wd.getDeposit());
				}
			}
			
			if (a != null) {
				ctx.result(gson.toJson(a));
				ctx.status(200);
			} else {
				ctx.result("{}");
				ctx.status(422);
			}
			
		} else {
			ctx.result("{}");
			ctx.status(404);
		}
		
	};
	
	public Handler transferBetweenAccounts = (ctx) -> {
		log.info("Start Handler transferBetweenAccounts");
		
		String cIdIn = ctx.pathParam("cId");
		String aFromIdIn = ctx.pathParam("aFromId");
		String aToIdIn = ctx.pathParam("aToId");
		
		int clientId;
		try {
			clientId = Integer.parseInt(cIdIn);
		} catch (NumberFormatException e) {
			clientId = -1;
			log.warn("clientId is not an int:", e);
		}
		int accountFromId;
		try {
			accountFromId = Integer.parseInt(aFromIdIn);
		} catch (NumberFormatException e) {
			accountFromId = -1;
			log.warn("accountFromId is not an int:", e);
		}
		int accountToId;
		try {
			accountToId = Integer.parseInt(aToIdIn);
		} catch (NumberFormatException e) {
			accountToId = -1;
			log.warn("accountToId is not an int:", e);
		}
		
		Account accountFrom = bs.getAccountForClient(clientId, accountFromId);
		Account accountTo = bs.getAccountForClient(clientId, accountToId);
		
		if (accountFrom != null && accountTo != null) {
			
			Amount amountObject = gson.fromJson(ctx.body(), Amount.class);
			double amount = amountObject.getAmount();
			
			if (amount > 0) {
				
				Account withdrawnAccount = bs.withdraw(accountFromId, amount);
				
				if (withdrawnAccount != null) {
					
					accountTo = bs.deposit(accountToId, amount);
					
					Transfer transfer = new Transfer(
							withdrawnAccount.getBalance(),
							accountTo.getBalance());
					
					ctx.result(gson.toJson(transfer));
					ctx.status(200);
					
				} else {
					
					Transfer transfer = new Transfer(
							accountFrom.getBalance(),
							accountTo.getBalance());
					
					ctx.result(gson.toJson(transfer));
					ctx.status(422);
					
				}
				
			} else {
				ctx.result("{}");
				ctx.status(422);
			}
		} else {
			ctx.result("{}");
			ctx.status(404);
		}
	};

}