package com.kevin_leader.models;

/**
 * Javabean representing a bank client
 * @author Kevin Leader
 * @since 07/01/2021
 */
public class Client {
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
//	private List<Account> ownedAccounts;

	/**
	 * No-args constructor
	 */
	public Client() {}
	
	/**
	 * Full constructor specifying an id
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 */
	public Client(int id, String firstName, String lastName, String email, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
//		ownedAccounts = new ArrayList<>();
	}
	
	/**
	 * Id-less constructor
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 */
	public Client(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
//		ownedAccounts = new ArrayList<>();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
//	public void putOwnedAccount(Account account) {
//		ownedAccounts.add(account);
//	}
//	
//	public void removeOwnedAccount(int index) {
//		ownedAccounts.remove(index);
//	}
//	
//	public Account getOwnedAccount(int key) {
//		return ownedAccounts.get(key);
//	}
//	
//	public List<Account> getOwnedAccounts() {
//		return ownedAccounts;
//	}
	
	@Override
	public String toString() {
		return "Client [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + "]";
	}
	
}
