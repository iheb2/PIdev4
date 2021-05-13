package com.github.adminfaces.starter.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.github.adminfaces.starter.model.AmalWacelGhada;
import com.github.adminfaces.starter.model.Transaction;
import com.github.adminfaces.starter.model.mode;




public interface ITransactionService {

	//Transaction retrieveTransaction(String id_t);

	//public Transaction paycredit(Long id_c);
	List<Transaction> Historique(long id_c);
	List<Transaction> retrieveAllTransactions();	
	Transaction addTransaction(Transaction p);	
	void deleteTransaction(Long id);	
	Transaction updateTransaction(Transaction p);	
	Transaction retrieveTransaction(String id_t);
	//void retrieveCreditTransaction(String Id);
	void paycreditDirect(Long id_c);
	void paycreditPortaPort(Long id_c);

	void payment_auto();
	Transaction paycredit(Long id_c);
	void retrieveCreditTransaction(AmalWacelGhada a,mode m);
	void retrait(String id, double a);
	double paycreditparcart(Long id_c);

}

