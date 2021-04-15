package tn.esprit.spring.controllers;

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

import tn.esprit.spring.models.Client;
import tn.esprit.spring.models.Statistic;
import tn.esprit.spring.services.IClientService;
import tn.esprit.spring.services.ServiceStatistic;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
public class ClientController {
	@Autowired
	ServiceStatistic statService;
	@Autowired
	IClientService clientService;

	@Scheduled(cron = "0 0 1 * * ?")
	@GetMapping("/CalculScoreAndAffectToUclients")
	@ResponseBody
	public void CalculScoreAndAffectToUclients() {
		clientService.CalculScoreAndAffectToUclients();
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/retrieve-all-Clients")
	@ResponseBody
	public List<Client> getClients() {
		List<Client> list = clientService.retrieveAllClients();
		return list;
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/retrieve-Client/{client-id}")
	@ResponseBody
	public Client retrieveClient(@PathVariable("client-id") Long id_client) {
		return clientService.retrieveClient(id_client);
	}

	@PostMapping("/add-Client")
	@ResponseBody
	public String addClient(@RequestBody Client c) {
		String client = clientService.addClient(c);
		return client;
	}

	
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@DeleteMapping("/remove-Client/{client-id}")
	@ResponseBody
	public void removeClient(@PathVariable("client-id") Long id_client) {
		clientService.deleteClient(id_client);
		;
	}

	@PutMapping("/modify-Client")
	@ResponseBody
	public Client modifyClient(@RequestBody Client c) {
		return clientService.updateClient(c);
	}
	
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/search-Client/{Client-cin}")
	@ResponseBody
	public List<Client> SearchProductByName(@PathVariable("Client-cin") String cin) {
		return clientService.SearchClientByName(cin);
	}
	
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/search-Client-by-score/{Client-score}")
	@ResponseBody
	public List<Client> SearchProductByScore(@PathVariable("Client-score") Double score) {
		return clientService.retrieveClientByScore(score);
	}
	
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/statisticScore")
	@ResponseBody
	public ResponseEntity<Statistic> getStatistic() {
		List<Client> sc = clientService.retrieveAllClients();
		Statistic statistics = statService.CreateStatistic(sc);

		return ResponseEntity.ok(statistics);
	}
}