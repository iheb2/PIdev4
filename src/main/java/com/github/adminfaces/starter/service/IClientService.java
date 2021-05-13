package com.github.adminfaces.starter.service;

import java.util.List;

import com.github.adminfaces.starter.infra.model.*;
import com.github.adminfaces.starter.model.Client;


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
