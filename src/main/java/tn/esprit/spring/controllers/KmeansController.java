package tn.esprit.spring.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tn.esprit.spring.models.Client;
import tn.esprit.spring.models.Cluster;
import tn.esprit.spring.models.DataPoint;
import tn.esprit.spring.models.KMeans;
import tn.esprit.spring.models.KMeansPlusPlusInitialiser;
import tn.esprit.spring.models.RandomInitialiser;
import tn.esprit.spring.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.services.IClientService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class KmeansController {

	@Autowired
	IClientService clientService;

	@Autowired
	ClientRepository rep;

	@GetMapping("/kmeansForDataanalysis")
	@ResponseBody
	public KMeans kmeans() {
		// KMeansPlusPlusInitialiser

		List<DataPoint> points = new ArrayList<>();
		List<String> listCin = new ArrayList<String>();
		List<Double> listScore = new ArrayList<Double>();

		List<Client> clients = clientService.retrieveAllClients();
		for (int i = 0; i < clients.size(); i++) {
			listCin.add(clients.get(i).getCin());
		}
		for (int i = 0; i < clients.size(); i++) {
			listScore.add(clients.get(i).getScore());
		}

		for (int i = 0; i < clients.size(); i++) {
			points.add(new DataPoint(listScore.get(i)));
		}
		KMeans kMeans = new KMeans(3, points, new RandomInitialiser());

		return kMeans;

	}

	@GetMapping("/DatapointsPerClusterr")
	@ResponseBody
	public List<Set<DataPoint>> getCopyOfDatapointsPerClusterr()

	{
		List<DataPoint> points = new ArrayList<>();
		List<String> listCin = new ArrayList<String>();
		List<Double> listScore = new ArrayList<Double>();

		List<Client> clients = clientService.retrieveAllClients();
		for (int i = 0; i < clients.size(); i++) {
			listCin.add(clients.get(i).getCin());
		}
		for (int i = 0; i < clients.size(); i++) {
			listScore.add(clients.get(i).getScore());
		}

		for (int i = 0; i < clients.size(); i++) {
			points.add(new DataPoint(listScore.get(i)));
		}

		KMeans kMeans = new KMeans(3, points, new KMeansPlusPlusInitialiser());

		return kMeans.getCopyOfDatapointsPerCluster();

	}

	@GetMapping("/simpleAgentViewbyUsername")
	@ResponseBody

	public Map<String, String> simpleAgentViewbyUsername() {

		Map<String, String> hash_map = new HashMap<>();

		List<Cluster> listOfClusters = kmeans().getClusters();
		List<String> listusername = new ArrayList<String>();

		for (int i = 0; i < kmeans().k; i++) {
			int y = i + 1;
			String groupe = "Groupe" + y;

			Cluster cluster = listOfClusters.get(i);
			Set<DataPoint> SetOfPoints = cluster.getDataPoints();
			List<DataPoint> ListOfPoints = new ArrayList<>(SetOfPoints);
			int n = ListOfPoints.size();
			for (int j = 0; j < n; j++) {
				Double score = ListOfPoints.get(j).getComponents().get(0);

				String username = clientService.retrieveClientByScore(score).get(0).getUsername();

				hash_map.put(username, groupe);

			}

		}

		return hash_map;
	}

	@GetMapping("/simpleAgentViewbyId")
	@ResponseBody
	public Map<Long, String> simpleAgentViewbyId() {

		Map<Long, String> hash_map = new HashMap<>();

		List<Cluster> listOfClusters = kmeans().getClusters();
		List<Long> listusername = new ArrayList<Long>();

		for (int i = 0; i < kmeans().k; i++) {
			int y = i + 1;
			String groupe = "Groupe" + y;

			Cluster cluster = listOfClusters.get(i);
			Set<DataPoint> SetOfPoints = cluster.getDataPoints();
			List<DataPoint> ListOfPoints = new ArrayList<>(SetOfPoints);

			int n = ListOfPoints.size();

			for (int j = 0; j < n; j++) {
				Double score = ListOfPoints.get(j).getComponents().get(0);

				Long id = clientService.retrieveClientByScore(score).get(0).getId();

				hash_map.put(id, groupe);

			}

		}

		return hash_map;
	}

	@GetMapping("/AffdecterGroupes")
	@ResponseBody
	public void AffdecterGroupes() {
		Map<Long, String> hashmap = simpleAgentViewbyId();
		for (Map.Entry m : hashmap.entrySet()) {
			Long yy = (Long) m.getKey();
			Client client = clientService.retrieveClient(yy);
			client.setGroupe((String) m.getValue());
			rep.save(client);

		}

	}

}
