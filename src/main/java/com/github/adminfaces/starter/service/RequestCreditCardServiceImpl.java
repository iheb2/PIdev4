package com.github.adminfaces.starter.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.adminfaces.starter.model.RequestCreditCard;
import com.github.adminfaces.starter.repository.RequestCreditCardRepository;

@Service
public class RequestCreditCardServiceImpl implements IRequestCreditCardService{
	@Autowired
   RequestCreditCardRepository requestrepo; 
	@Override
	public List<RequestCreditCard> retrieveAllRequestCreditCards() {
		List<RequestCreditCard> requestlist =(List<RequestCreditCard>)requestrepo.findAll();
		return requestlist;
	}

	@Override
	public RequestCreditCard addRequestCreditCard(RequestCreditCard request) {
		
		return requestrepo.save(request);
	}

	@Override
	public void deleteRequestCreditCard(RequestCreditCard request) {
		requestrepo.deleteById(request.getId_request());
		
	}

	@Override
	public RequestCreditCard updateRequestCreditCard(RequestCreditCard request) {
		return requestrepo.save(request);
	}

	@Override
	public Optional<RequestCreditCard> retrieveRequestCreditCard(Long id_request) {
		return requestrepo.findById(id_request);
	}

}
