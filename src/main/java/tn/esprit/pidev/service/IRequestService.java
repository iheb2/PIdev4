package tn.esprit.pidev.service;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import tn.esprit.pidev.model.Request;
import tn.esprit.pidev.model.SavedRequests;

public interface IRequestService {
	List<Request> retrieveAllRequests();
	void deleteRequest(Request request);
	Request updateRequest(Request request);
	Optional<Request> retrieveRequest(Long id_request);
	Request addRequest(Request request);
	Request acceptRequest(Request request);
	String RecommendRequest();
	Optional<SavedRequests> retrieveSavedRequest(Long id_request);
	Map<String, Integer> StatisticRequestByType(String type);
	
}
