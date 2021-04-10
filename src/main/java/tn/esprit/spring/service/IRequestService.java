package tn.esprit.pidev.service;
import java.util.List;

import java.util.Optional;

import tn.esprit.pidev.model.Request;

public interface IRequestService {
	List<Request> retrieveAllRequests();
	Request addRequest(Request request);
	void deleteRequest(Request request);
	Request updateRequest(Request request);
	Optional<Request> retrieveRequest(Long id_request);
}
