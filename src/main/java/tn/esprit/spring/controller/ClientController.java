package tn.esprit.spring.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import tn.esprit.spring.model.Client;
import tn.esprit.spring.service.IClientService;

@RestController
public class ClientController {

	@Autowired
	IClientService clientService;
	
			@GetMapping("/retrieve-all-Clients")
			@ResponseBody
			public List<Client> getClients() {
				List<Client> list = clientService.retrieveAllClients();
				return list;
			}
		
			@GetMapping("/retrieve-Client/{client-id}")
			@ResponseBody
			public Client retrieveClient(@PathVariable("client-id") String id_client) {
				return clientService.retrieveClient(id_client);
			}	
			
			@PostMapping("/add-Client")
			@ResponseBody
			public Client addClient(@RequestBody Client c) {
				Client client = clientService.addClient(c);
				return client;
			}
			
				@DeleteMapping("/remove-Client/{client-id}")
				@ResponseBody
				public void removeClient (@PathVariable("client-id") Long id_client) {
					clientService.deleteClient(id_client);;
				}
				
				@PutMapping("/modify-Client")
				@ResponseBody
				public Client modifyClient(@RequestBody Client c) {
					return clientService.updateClient(c);
				}
				
	
}
