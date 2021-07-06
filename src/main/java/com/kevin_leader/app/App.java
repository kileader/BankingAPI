package com.kevin_leader.app;

import org.apache.log4j.Logger;

import com.kevin_leader.controllers.BankingController;
import com.kevin_leader.repositories.AccountRepo;
import com.kevin_leader.repositories.AccountRepoImpl;
import com.kevin_leader.repositories.ClientRepo;
import com.kevin_leader.repositories.ClientRepoImpl;
import com.kevin_leader.services.BankingService;
import com.kevin_leader.services.BankingServiceImpl;

import io.javalin.Javalin;

public class App {

	private static final Logger log = Logger.getLogger(App.class);
	
	public static void main(String[] args) {
		log.info("Start main method to establishRoutes and start app on 7000");
		
		// Establish our Javalin Object
		Javalin app = Javalin.create();

		// Establish Routes/Endpoints Javalin will manage
		establishRoutes(app);
		
		// Run Javalin
		app.start(7000);
		
	}
	
	// Establish routes (endpoints) for Javalin to manage
	private static void establishRoutes(Javalin app) {		
		log.info("Establish routes");
		
		// Instantiate banking controller
		ClientRepo cr = new ClientRepoImpl();
		AccountRepo ar = new AccountRepoImpl();
		BankingService bs = new BankingServiceImpl(cr, ar);
		BankingController bc = new BankingController(bs);
		
		// Establish a route to the 'landing' page.
		app.get("/", (ctx) -> ctx.result("This is the banking app home page!"));
		app.get("/hello", (ctx) -> ctx.result("Hello World!"));
		
		// Establish routes for clients endpoints
		app.post("/clients", bc.addClient);
		// body: firstName, lastName, email, and password
		app.get("/clients", bc.getAllClients);
		app.get("/clients/:id", bc.getClientById);
		app.put("/clients/:id", bc.updateClientById);
		// body: firstName, lastName, email, and password
		app.delete("/clients/:id", bc.deleteClientById);
		
		// Establish routes for accounts endpoint
		app.post("/clients/:id/accounts", bc.addAccountForClient);
		// body: accountName, accountType, balance
		app.get("/clients/:id/accounts", bc.getAccountsForClient);
//		app.get("/clients/:id/accounts?amountLessThan=*&amountGreaterThan=*",
//				bc.getAllAccountsForClientBetweenBalances); // TODO: Fix This
		app.get("/clients/:cId/accounts/:aId", bc.getAccountForClient);
		app.put("/clients/:cId/accounts/:aId", bc.updateAccountForClient);
		// body: accountName, accountType, balance
		app.delete("/clients/:cId/accounts/:aId", bc.deleteAccountForClient);
		app.patch("/clients/:cId/accounts/:aId", bc.withdrawOrDeposit);
		app.patch("/clients/:cId/accounts/:aFromId/transfer/:aToId",
				bc.transferBetweenAccounts); // body: transferAmount
		
	}

}
