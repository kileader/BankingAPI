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
		Client c = gson.fromJson(ctx.body(), Client.class);
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
			ctx.result(gson.toJson(client));
//			ctx.status(204);
			ctx.status(200);
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
		a = bs.addAccount(a);
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
	
	public Handler updateAccount = (ctx) -> {
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
		
		if (a != null) {
			bs.updateAccount(a);
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
			ctx.result(gson.toJson(a));
			ctx.status(200);
		} else {
			ctx.result("{}");
			ctx.status(404);
		}
	};
	
	public Handler withdrawOrDeposit = (ctx) -> {
		// TODO: Finish This
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
		
//		Account a = bs.withdraw(clientId, accountId);
	};
	
	public Handler transferBetweenAccounts = (ctx) -> {
		String cIdInput = ctx.pathParam("cId");
		String a1IdInput = ctx.pathParam("a1Id");
		String a2IdInput = ctx.pathParam("a2Id"); //TODO: finish this
		
		int clientId;
		try {
			clientId = Integer.parseInt(cIdInput);
		} catch (NumberFormatException e) {
			clientId = -1;
		}
		
		int account1Id;
		try {
			account1Id = Integer.parseInt(a1IdInput);
		} catch (NumberFormatException e) {
			account1Id = -1;
		}
		
		int account2Id;
		try {
			account2Id = Integer.parseInt(a2IdInput);
		} catch (NumberFormatException e) {
			account2Id = -1;
		}
		
		double amount = (double) gson.fromJson(ctx.body(), Object.class);
		
		boolean success = bs.transferBetweenAccounts(
				clientId, account1Id, account2Id, amount);
		
		if (success) {	
			ctx.status(200);
		} else {
			ctx.status(404);
		}
	};

}
