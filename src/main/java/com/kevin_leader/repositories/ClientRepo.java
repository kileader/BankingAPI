package com.kevin_leader.repositories;

import java.util.List;

import com.kevin_leader.models.database.Client;

/**
 * Client repository to do CRUD operations
 * @author Kevin Leader
 * @since 07/01/2021
 */
public interface ClientRepo {
	
	/**
	 * Get client from database using id
	 * @param id the client id
	 * @return the client
	 */
	public Client getClient(int id);
	
	/**
	 * Get all clients from database
	 * @return all the clients
	 */
	public List<Client> getAllClients();
	
	/**
	 * Create a new client and add it to the database
	 * @param newClient the new client
	 * @return the new client
	 */
	public Client addClient(Client newClient);
	
	/**
	 * Update a client in the database
	 * @param changedClient the changed client
	 * @return the changed client
	 */
	public Client updateClient(Client changedClient);
	
	/**
	 * Delete a client from the database
	 * @param id the id of the client to delete
	 * @return the deleted client
	 */
	public Client deleteClient(int id);
	
}
