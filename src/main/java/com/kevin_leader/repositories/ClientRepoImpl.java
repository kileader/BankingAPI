package com.kevin_leader.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.kevin_leader.models.Client;
import com.kevin_leader.util.FakeDB;

/**
 * Client repository implementation to do CRUD operations
 * @author Kevin Leader
 * @since 07/01/2021
 */
public class ClientRepoImpl implements ClientRepo {

	@Override
	public Client getClient(int id) {
		return FakeDB.clients.get(id);
	}

	@Override
	public List<Client> getAllClients() {
		List<Client> clientList = new ArrayList<Client>();
		Set<Integer> keys = FakeDB.clients.keySet();
		
		for(Integer key : keys) {
			clientList.add(FakeDB.clients.get(key));
		}
		return clientList;
	}

	@Override
	public Client addClient(Client newClient) {
		// Make sure the id is right and update the count
		newClient.setId(++FakeDB.clientsIdCount);
		
		FakeDB.clients.put(newClient.getId(), newClient);
		return newClient;
	}

	@Override
	public Client updateClient(Client changedClient) {
		FakeDB.clients.replace(changedClient.getId(), changedClient);
		return changedClient;
	}

	@Override
	public Client deleteClient(int id) {
		return FakeDB.clients.remove(id);
	}

}
