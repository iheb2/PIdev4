package com.github.adminfaces.starter.service;
import java.util.List;

import java.util.Optional;

import com.github.adminfaces.starter.model.RequestCredit;

public interface IRequestCreditService {
	List<RequestCredit> retrieveAllRequestCredits();
	RequestCredit addRequestCredit(RequestCredit request);
	void deleteRequestCredit(RequestCredit request);
	RequestCredit updateRequestCredit(RequestCredit request);
	Optional<RequestCredit> retrieveRequestCredit(Long id_request);
	String mensualiteMicroCredit(RequestCredit request, double montant, double mois);
	String simulationMensualite(double montant, double mois);
	Double calculMensualite(double montant, double mois);
	Double calculTotal(double mensualite, double mois);
	Double calculInteret(double montant, double total);
	void voidmensualiteMicroCredit(RequestCredit request, double montant, double mois);
}
