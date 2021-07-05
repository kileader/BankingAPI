package com.kevin_leader.controllers;

import java.util.List;

import com.google.gson.Gson;
import com.kevin_leader.models.Account;
import com.kevin_leader.models.Client;
import com.kevin_leader.services.BankingService;

import io.javalin.http.Handler;

/**
 * Manages application logic (endpoints) for the banking api
 * @author Kevin Leader
 */
public class BankingController {

	BankingService bs;
	Gson gson = new Gson();
	
	public BankingController(BankingService bs) {
		this.bs = bs;
	}
	
	public Handler addClient = (ctx) -> {
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
		// Set input
		String input = ctx.pathParam("id");
		int id;
		// Check if input is int
		try {
			id = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			id = -1;
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
		String input = ctx.pathParam("id");
		int clientId;
		try {
			clientId = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			clientId = -1;
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
		String input = ctx.pathParam("id");
		int id;
		try {
			id = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			id = -1;
		}
		// Delete client in database and return the deleted client
		Client client = bs.deleteClient(id);
		if (client != null) {
			ctx.status(204);
		} else {
			ctx.result("{}");
			ctx.status(404);
		}
	};
	
	public Handler addAccountForClient = (ctx) -> {
		String input = ctx.pathParam("id");
		int clientId;
		try {
			clientId = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			clientId = -1;
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
	
	public Handler getAllAccountsForClient = (ctx) -> {
		String input = ctx.pathParam("id");
		
		int clientId;
		try {
			clientId = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			clientId = -1;
		}
		
		List<Account> accounts = bs.getAllAccountsForClient(clientId);
		
		if (accounts != null) {
			ctx.result(gson.toJson(accounts));
			ctx.status(200);
		} else {
			ctx.result("{}");
			ctx.status(404);
		}
	};
	
	public Handler getAllAccountsForClientBetweenBalances = (ctx) -> {
		String idIn = ctx.pathParam("id");
		String maxBalanceIn = ctx.splat(0);
		String minBalanceIn = ctx.splat(1);
		
		int clientId;
		try {
			clientId = Integer.parseInt(idIn);
		} catch (NumberFormatException e) {
			clientId = -1;
		}
		int maxBalance;
		try {
			maxBalance = Integer.parseInt(maxBalanceIn);
		} catch (NumberFormatException e) {
			maxBalance = -1;
		}
		int minBalance;
		try {
			minBalance = Integer.parseInt(minBalanceIn);
		} catch (NumberFormatException e) {
			minBalance = -1;
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
		String cIdInput = ctx.pathParam("cId");
		String aIdInput = ctx.pathParam("aId");
		
		int clientId;
		try {
			clientId = Integer.parseInt(cIdInput);
		} catch (NumberFormatException e) {
			clientId = -1;
		}
		
		int accountId;
		try {
			accountId = Integer.parseInt(aIdInput);
		} catch (NumberFormatException e) {
			accountId = -1;
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
		String cIdInput = ctx.pathParam("cId");
		String aIdInput = ctx.pathParam("aId");
		
		Account a = gson.fromJson(ctx.body(), Account.class);
		
		int clientId;
		try {
			clientId = Integer.parseInt(cIdInput);
		} catch (NumberFormatException e) {
			clientId = -1;
		}
		
		int accountId;
		try {
			accountId = Integer.parseInt(aIdInput);
		} catch (NumberFormatException e) {
			accountId = -1;
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
		String cIdInput = ctx.pathParam("cId");
		String aIdInput = ctx.pathParam("aId");
		
		int clientId;
		try {
			clientId = Integer.parseInt(cIdInput);
		} catch (NumberFormatException e) {
			clientId = -1;
		}
		
		int accountId;
		try {
			accountId = Integer.parseInt(aIdInput);
		} catch (NumberFormatException e) {
			accountId = -1;
		}
		
		Account a = bs.deleteAccountForClient(clientId, accountId);
		
		if (a != null) {	
			ctx.status(204);
		} else {
			ctx.status(404);
		}
	};
	
	public Handler withdrawOrDeposit = (ctx) -> {
		String cIdInput = ctx.pathParam("cId");
		String aIdInput = ctx.pathParam("aId");
		
		int clientId;
		try {
			clientId = Integer.parseInt(cIdInput);
		} catch (NumberFormatException e) {
			clientId = -1;
		}
		int accountId;
		try {
			accountId = Integer.parseInt(aIdInput);
		} catch (NumberFormatException e) {
			accountId = -1;
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
		String cIdIn = ctx.pathParam("cId");
		String aFromIdIn = ctx.pathParam("aFromId");
		String aToIdIn = ctx.pathParam("aToId");
		
		int clientId;
		try {
			clientId = Integer.parseInt(cIdIn);
		} catch (NumberFormatException e) {
			clientId = -1;
		}
		int accountFromId;
		try {
			accountFromId = Integer.parseInt(aFromIdIn);
		} catch (NumberFormatException e) {
			accountFromId = -1;
		}
		int accountToId;
		try {
			accountToId = Integer.parseInt(aToIdIn);
		} catch (NumberFormatException e) {
			accountToId = -1;
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


/**
 * Class for an amount of withdrawal/deposit to be mapped from JSON
 * @author Kevin Leader
 */
class WithdrawOrDeposit {
	
	private double withdraw;
	private double deposit;
	
	protected WithdrawOrDeposit(double withdraw, double deposit) {
		this.withdraw = withdraw;
		this.deposit = deposit;
	}

	protected double getWithdraw() {
		return withdraw;
	}

	protected double getDeposit() {
		return deposit;
	}
	
}

/**
 * Class for an amount to be mapped from JSON
 * @author Kevin Leader
 */
class Amount {
	
	private double amount;
	
	protected Amount(double amount) {
		this.amount = amount;
	}
	
	protected double getAmount() {
		return amount;
	}
	
}

/**
 * Transfer object to be returned after a transfer
 */
class Transfer {
	
	private double accountFromBalance;
	private double accountToBalance;
	
	protected Transfer(double accountFromBalance, double accountToBalance) {
		this.accountFromBalance = accountFromBalance;
		this.accountToBalance = accountToBalance;
	}

	protected double getAccountFromBalance() {
		return accountFromBalance;
	}

	protected double getAccountToBalance() {
		return accountToBalance;
	}
	
}
