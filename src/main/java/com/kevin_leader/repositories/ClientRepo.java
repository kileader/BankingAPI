package com.kevin_leader.repositories;

import java.util.List;

import com.kevin_leader.models.Client;

public interface ClientRepo {
	
	public Client getClient(int id);
	
	public List<Client> getAllClients();
	
	public Client addClient(Client newAccount);
	
	public Client updateClient(Client changedAccount);
	
	public Client deleteClient(Client accountToDelete);
	
}
