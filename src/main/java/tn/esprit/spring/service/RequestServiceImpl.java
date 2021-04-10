package tn.esprit.pidev.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pidev.model.Request;
import tn.esprit.pidev.repository.RequestRepository;

@Service
public class RequestServiceImpl implements IRequestService{
	@Autowired
   RequestRepository requestrepo; 
	@Override
	public List<Request> retrieveAllRequests() {
		List<Request> requestlist =(List<Request>)requestrepo.findAll();
		return requestlist;
	}

	@Override
	public Request addRequest(Request request) {
		
		return requestrepo.save(request);
	}

	@Override
	public void deleteRequest(Request request) {
		requestrepo.deleteById(request.getId_request());
		
	}

	@Override
	public Request updateRequest(Request request) {
		return requestrepo.save(request);
	}

	@Override
	public Optional<Request> retrieveRequest(Long id_request) {
		return requestrepo.findById(id_request);
	}

}
