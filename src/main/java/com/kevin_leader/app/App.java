package com.kevin_leader.app;

import io.javalin.Javalin;

public class App {

	public static void main(String[] args) {

		// Establish our Javalin Object
		Javalin app = Javalin.create();

		// Establish Routes/Endpoints Javalin will manage
		
		// Run Javalin
		app.start(7000);
		
	}
	
	private static void establishRoutes(Javalin app) {
//		// Here we define routes for Javalin to manage
//		ClientRepo cr = new ClientRepoImpl();
//		BankingService bs = new MovieServiceImpl(cr);
//		BankingController bc = new BankingController(bs);
		
		// Establish a route to the 'landing' page.
		app.get("/", (ctx) -> ctx.result("This is the banking app home page!"));
		app.get("/hello", (ctx) -> ctx.result("Hello World!"));
		
	}

}
