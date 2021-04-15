package tn.esprit.pidev.service;
import java.util.List;

import java.util.Optional;

import tn.esprit.pidev.model.RequestCredit;

public interface IRequestCreditService {
	List<RequestCredit> retrieveAllRequestCredits();
	RequestCredit addRequestCredit(RequestCredit request);
	void deleteRequestCredit(RequestCredit request);
	RequestCredit updateRequestCredit(RequestCredit request);
	Optional<RequestCredit> retrieveRequestCredit(Long id_request);
	float montantarembourserparmois(RequestCredit request, double montant, Integer duree);
}
