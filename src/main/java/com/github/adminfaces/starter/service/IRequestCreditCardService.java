package com.github.adminfaces.starter.service;
import java.util.List;

import java.util.Optional;

import com.github.adminfaces.starter.model.RequestCreditCard;

public interface IRequestCreditCardService {
	List<RequestCreditCard> retrieveAllRequestCreditCards();
	RequestCreditCard addRequestCreditCard(RequestCreditCard r);
	void deleteRequestCreditCard(RequestCreditCard r);
	RequestCreditCard updateRequestCreditCard(RequestCreditCard r);
	Optional<RequestCreditCard> retrieveRequestCreditCard(Long id);
}
