package tn.esprit.spring.service;


import java.util.List;


import tn.esprit.spring.model.Client;


public interface IClientService {

List<Client> retrieveAllClients();
	
	Client addClient(Client p);
	
	void deleteClient(Long id);
	
	Client updateClient(Client c);
	
	Client retrieveClient(String id);
	
	
	
}
