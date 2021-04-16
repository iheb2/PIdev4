package tn.esprit.spring.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import tn.esprit.spring.model.AmalWacelGhada;
import tn.esprit.spring.model.Transaction;
import tn.esprit.spring.model.mode;



public interface ITransactionService {

	//Transaction retrieveTransaction(String id_t);

	//public Transaction paycredit(Long id_c);
	List<Transaction> Historique(String id_c);
	List<Transaction> retrieveAllTransactions();	
	Transaction addTransaction(Transaction p);	
	void deleteTransaction(Long id);	
	Transaction updateTransaction(Transaction p);	
	Transaction retrieveTransaction(String id_t);
	//void retrieveCreditTransaction(String Id);
	String paycreditDirect(Long id_c);
	String paycreditPortaPort(Long id_c);

	void payment_auto();
	Transaction paycredit(Long id_c);
	void retrieveCreditTransaction(AmalWacelGhada a,mode m);
	void retrait(String id, double a);
	double paycreditparcart(Long id_c);

}

