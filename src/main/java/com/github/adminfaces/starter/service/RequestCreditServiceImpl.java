package com.github.adminfaces.starter.service;

import java.util.Collections;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.adminfaces.starter.model.Request;
import com.github.adminfaces.starter.model.RequestCredit;
import com.github.adminfaces.starter.repository.RequestCreditRepository;

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
	
	@Override
	public void voidmensualiteMicroCredit(RequestCredit request, double montant, double mois) {		
		
		double mensualite = calculMensualite(montant, mois);
		double total = calculTotal(mensualite, mois);
		double interet = calculInteret(montant, total);
		
		//Update de la Base de Données
		
		request.setAmount(montant);
		
		request.setMonths(mois);
		
		request.setTotal(total);
		
		request.setInterest(interet);
		
		request.setMonthly_payment(mensualite);
		
		requestrepo.save(request);
		
		
	}

	@Override
	public String mensualiteMicroCredit(RequestCredit request, double montant, double mois) {		
		
		double mensualite = calculMensualite(montant, mois);
		double total = calculTotal(mensualite, mois);
		double interet = calculInteret(montant, total);
		
		//Update de la Base de Données
		
		request.setAmount(montant);
		
		request.setMonths(mois);
		
		request.setTotal(total);
		
		request.setInterest(interet);
		
		request.setMonthly_payment(mensualite);
		
		requestrepo.save(request);
		
		String message = "Crédit de " + (int)montant +" dinars avec un taux annuel de 5% pris avec succès:\n" + simulationMensualite(montant, mois);
		
		return message;
		
	}
	
	@Override
	public String simulationMensualite(double montant, double mois) {		
		
		double mensualite = calculMensualite(montant, mois);
		double total = calculTotal(mensualite, mois);
		double interet = calculInteret(montant, total);
		
		//Formattage de l'affichage en 3 chiffres après la virgule (millimes)

		String format_mensualite = String.format("%.3f", mensualite);
		String format_interet = String.format("%.3f", interet);
		String format_total = String.format("%.3f", total);
				
		//Affichage
				
		String mensualiteInfo = "Monthly Payment: " + format_mensualite + "\nInterests Amount: " + format_interet + "\nTotal to Pay: " + format_total + "\n";
		
		return mensualiteInfo;
		
	}
	
	@Override
	public Double calculMensualite(double montant, double mois) {		
		
		/*
		Le taux annuel est fixé à 5% par BTS pour les micro-crédits
		Sources:
		Autorité de Contrôle de la Microfinance en Tunisie, Ministère des Finances (http://www.acm.gov.tn/upload/1410792769.pdf)
		Banque Tunisienne de Solidarité (https://www.bts.com.tn/systeme-des-micro-credits/)
		*/
		
		double TA = 0.05;
		
		/*
		Calculer la mensualité
		Source: Institut National de la Consommation en France
		(https://www.inc-conso.fr/content/comment-sont-calculees-les-mensualites-de-votre-emprunt)
		*/
		
		//Le taux périodique/mensuel (12 périodes) (APPROX TA/12)
		
		double TP = Math.pow((1+TA),(1/12.0))-1;
		
		/*La mensualité de l'emprunt (mois = nombre de mensualités)*/
		
		double mensualite = (montant*TP*Math.pow((1+TP),mois))/(Math.pow((1+TP),mois)-1);
				
		return mensualite;
		
	}
	
	@Override
	public Double calculTotal(double mensualite, double mois) {		
		
		/*
		Calculer le total à payer
		Source: Institut National de la Consommation en France
		(https://www.inc-conso.fr/content/comment-sont-calculees-les-mensualites-de-votre-emprunt)
		*/
		
		double total = mensualite * mois;
				
		return total;
		
	}
	
	@Override
	public Double calculInteret(double montant, double total) {		
		
		/*
		Calculer l'intérêt
		Source: Institut National de la Consommation en France
		(https://www.inc-conso.fr/content/comment-sont-calculees-les-mensualites-de-votre-emprunt)
		*/
		
		double interet = total - montant;
				
		return interet;
		
	}

}
