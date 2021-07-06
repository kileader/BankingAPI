package com.kevin_leader.util;

import java.util.HashMap;
import java.util.Map;

import com.kevin_leader.models.database.Account;
import com.kevin_leader.models.database.Client;

/**
 * Fake database to be soon replaced
 * @author Kevin
 * @since 07/01/2021
 */
public class FakeDB {
	
	public static Map<Integer, Client> clients = new HashMap<Integer, Client>();
	public static Map<Integer, Account> accounts = new HashMap<Integer, Account>();
	
	public static int clientsIdCount = 0;
	public static int accountsIdCount = 0;
	
	static {
		// Dummy data generated with mockaroo.com
		Client c1 = new Client(1,"Maximilian","Donoghue","mdonoghue0@microsoft.com","0o6xQTF");
		clients.put(++clientsIdCount, c1);
		Client c2 = new Client(2,"Lucas","Moncreif","lmoncreif1@vk.com","v73mlUAImfGc");
		clients.put(++clientsIdCount, c2);
		Client c3 = new Client(3,"Tris","Cready","tcready2@kickstarter.com","JlpxGCJ9Bj");
		clients.put(++clientsIdCount, c3);
		Client c4 = new Client(4,"Rollin","Shedden","rshedden3@timesonline.co.uk","ZsXA6QicWE");
		clients.put(++clientsIdCount, c4);
		Client c5 = new Client(5,"Wolfgang","Teliga","wteliga4@ehow.com","Ua5q62N7");
		clients.put(++clientsIdCount, c5);
		Client c6 = new Client(6,"Clare","Tuckey","ctuckey5@odnoklassniki.ru","aqEM3pLpWoCx");
		clients.put(++clientsIdCount, c6);
		Client c7 = new Client(7,"Kristofor","Kopps","kkopps6@bandcamp.com","ww6u3PSqFTqZ");
		clients.put(++clientsIdCount, c7);
		Client c8 = null;
		clients.put(++clientsIdCount, c8);
		Client c9 = new Client(9,"Kayla","Hurren","khurren8@geocities.com","Y5co3w6QD");
		clients.put(++clientsIdCount, c9);
		Client c10 = new Client(10,"Muriel","Pinnijar","mpinnijar9@wordpress.org","1lfxlvDomT");
		clients.put(++clientsIdCount, c10);
		Client c11 = new Client(11,"Siobhan","Durdan","sdurdana@quantcast.com","ZyJunmNkz5C");
		clients.put(++clientsIdCount, c11);
		Client c12 = new Client(12,"Sabrina","McTrustam","smctrustamb@utexas.edu","CUKJgVQSC");
		clients.put(++clientsIdCount, c12);
		Client c13 = new Client(13,"Diego","Barbey","dbarbeyc@canalblog.com","2TrzWbdv9MZw");
		clients.put(++clientsIdCount, c13);
		Client c14 = null;
		clients.put(++clientsIdCount, c14);
		Client c15 = new Client(15,"Maddy","Trenouth","mtrenouthe@360.cn","CnvX5n");
		clients.put(++clientsIdCount, c15);
		Client c16 = new Client(16,"Alf","Cosgriff","acosgrifff@hatena.ne.jp","EvxYJO0");
		clients.put(++clientsIdCount, c16);
		
		Account a1 = new Account(1, 8, "justo", "CHECKING", 7020.68);
		accounts.put(++accountsIdCount, a1);
		Account a2 = new Account(2 , 11 , "morbi" , "SAVINGS" , 17015.68 );
		accounts.put(++accountsIdCount, a2);
		Account a3 = new Account(3 , 2 , "lobortis" , "CHECKING" , 3129.3 );
		accounts.put(++accountsIdCount, a3);
		Account a4 = new Account(4 , 1 , "duis" , "SAVINGS" , 30557.63 );
		accounts.put(++accountsIdCount, a4);
		Account a5 = new Account(5 , 12 , "nisi" , "SAVINGS" , 22532.4 );
		accounts.put(++accountsIdCount, a5);
		Account a6 = new Account(6 , 12 , "mauris" , "CHECKING" , 5748.21 );
		accounts.put(++accountsIdCount, a6);
		Account a7 = new Account(7 , 3 , "convallis" , "SAVINGS" , 17127.98 );
		accounts.put(++accountsIdCount, a7);
		Account a8 = null;
		accounts.put(++accountsIdCount, a8);
		Account a9 = new Account(9 , 16 , "vivamus" , "CHECKING" , 4118.52 );
		accounts.put(++accountsIdCount, a9);
		Account a10 = new Account(10 , 7 , "Daily Checking" , "CHECKING" , -3478.32 );
		accounts.put(++accountsIdCount, a10);
		Account a11 = new Account(11 , 7 , "Old Checking" , "CHECKING" , 2648.96 );
		accounts.put(++accountsIdCount, a11);
		Account a12 = new Account(12 , 7 , "Laura's Checking" , "CHECKING" , 1674.53 );
		accounts.put(++accountsIdCount, a12);
		Account a13 = null;
		accounts.put(++accountsIdCount, a13);
		Account a14 = new Account(14 , 7 , "New House" , "SAVINGS" , 684.26 );
		accounts.put(++accountsIdCount, a14);
		Account a15 = null;
		accounts.put(++accountsIdCount, a15);
		Account a16 = null;
		accounts.put(++accountsIdCount, a16);
		Account a17 = new Account(17 , 5 , "pretium" , "SAVINGS" , -494.33 );
		accounts.put(++accountsIdCount, a17);
		Account a18 = new Account(18 , 10 , "eleifend" , "SAVINGS" , 27796.8 );
		accounts.put(++accountsIdCount, a18);
		Account a19 = new Account(19 , 5 , "vestibulum" , "SAVINGS" , 10105.76);
		accounts.put(++accountsIdCount, a19);
		Account a20 = new Account(20 , 1 , "nibh" , "CHECKING" , 37006.7 );
		accounts.put(++accountsIdCount, a20);
	}

}
