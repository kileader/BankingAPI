package com.kevin_leader.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kevin_leader.models.Client;
import com.kevin_leader.util.JDBCConnection;

/**
 * Client repository implementation to do CRUD operations
 * @author Kevin Leader
 */
public class ClientRepoImpl implements ClientRepo {

	public static Connection conn = JDBCConnection.getConnection();
	
	/**
	 * Build a Client from ResultSet
	 * @param rs result set from executing sql
	 * @return the built Client
	 * @throws SQLException
	 */
	private Client buildClient(ResultSet rs) throws SQLException {
		Client client = new Client(
				rs.getInt("id"),
				rs.getString("first_name"),
				rs.getString("last_name"),
				rs.getString("email"),
				rs.getString("password")
				);
		return client;
	}

	@Override
	public Client getClient(int id) {
		
		String sql = "SELECT * FROM clients WHERE id = ?";
		
		try {
			// Set up prepared statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			// Execute query, store results
			ResultSet rs = ps.executeQuery();
			
			// Extract the first result
			if (rs.next()) {
				return buildClient(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Client> getAllClients() {
		
		String sql = "SELECT * FROM clients";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			List<Client> clients = new ArrayList<>();
			while (rs.next()) {
				clients.add(buildClient(rs));
			}	
			return clients;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Client addClient(Client newClient) {
		String sql = "INSERT INTO clients VALUES "
				+ "(default,?,?,?,?) RETURNING *";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// Set values for the ?s
			ps.setString(1, newClient.getFirstName());
			ps.setString(2, newClient.getLastName());
			ps.setString(3, newClient.getEmail());
			ps.setString(4, newClient.getPassword());
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return buildClient(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Client updateClient(Client changedClient) {

		String sql = "UPDATE clients SET first_name = ?, last_name = ?,"
				+ " email = ?, password = ? WHERE id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, changedClient.getFirstName());
			ps.setString(2, changedClient.getLastName());
			ps.setString(3, changedClient.getEmail());
			ps.setString(4, changedClient.getPassword());
			ps.setInt(5, changedClient.getId());
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return buildClient(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Client deleteClient(int id) {
		
		String sql = "DELETE FROM clients WHERE id = ? RETURNING *";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return buildClient(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	@Override
//	public Client getClient(int id) {
//		return FakeDB.clients.get(id);
//	}
//
//	@Override
//	public List<Client> getAllClients() {
//		List<Client> clientList = new ArrayList<Client>();
//		Set<Integer> keys = FakeDB.clients.keySet();
//		
//		for(Integer key : keys) {
//			clientList.add(FakeDB.clients.get(key));
//		}
//		return clientList;
//	}
//
//	@Override
//	public Client addClient(Client newClient) {
//		// Make sure the id is right and update the count
//		newClient.setId(++FakeDB.clientsIdCount);
//		
//		FakeDB.clients.put(newClient.getId(), newClient);
//		return newClient;
//	}
//
//	@Override
//	public Client updateClient(Client changedClient) {
//		FakeDB.clients.replace(changedClient.getId(), changedClient);
//		return changedClient;
//	}
//
//	@Override
//	public Client deleteClient(int id) {
//		return FakeDB.clients.remove(id);
//	}

}
