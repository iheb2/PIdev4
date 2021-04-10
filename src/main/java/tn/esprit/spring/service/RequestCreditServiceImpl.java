package tn.esprit.pidev.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pidev.model.RequestCredit;
import tn.esprit.pidev.repository.RequestCreditRepository;

@Service
public class RequestCreditServiceImpl implements IRequestCreditService{
	@Autowired
   RequestCreditRepository requestrepo; 
	@Override
	public List<RequestCredit> retrieveAllRequestCredits() {
		List<RequestCredit> requestlist =(List<RequestCredit>)requestrepo.findAll();
		return requestlist;
	}

	@Override
	public RequestCredit addRequestCredit(RequestCredit request) {
		
		return requestrepo.save(request);
	}

	@Override
	public void deleteRequestCredit(RequestCredit request) {
		requestrepo.deleteById(request.getId_request());
		
	}

	@Override
	public RequestCredit updateRequestCredit(RequestCredit request) {
		return requestrepo.save(request);
	}

	@Override
	public Optional<RequestCredit> retrieveRequestCredit(Long id_request) {
		return requestrepo.findById(id_request);
	}

}
