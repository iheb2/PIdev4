package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.models.Client;
import tn.esprit.spring.models.User;

public interface IClientService {

	List<Client> retrieveAllClients();

	String addClient(Client p);

	void deleteClient(Long id);

	Client updateClient(Client c);

	Client retrieveClient(Long id);

	List<Client> SearchClientByName(String cin);

	List<Client> retrieveClientByScore(Double score);

	public Double CalculScore(long id);

	public void CalculScoreAndAffectToUclients();

}
