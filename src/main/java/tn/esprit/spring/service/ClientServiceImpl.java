package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.esprit.spring.model.Client;
import tn.esprit.spring.repository.ClientRepository;;



@Service
public class ClientServiceImpl implements IClientService{

	@Autowired
	ClientRepository ClientRepository ;

	@Override
	public List<Client> retrieveAllClients() {
		return (List<Client>) ClientRepository.findAll();
	}

	@Override
	public Client addClient(Client p) {
		return ClientRepository.save(p);
	}

	@Override
	public void deleteClient(Long id) {
		ClientRepository.deleteById((long) id);
	}

	@Override
	public Client updateClient(Client p) {
		return ClientRepository.save(p);
	}

	@Override
	public Client retrieveClient(String id) {
		return ClientRepository.findById(Long.parseLong(id)).orElse(null);
	}	
	
}
