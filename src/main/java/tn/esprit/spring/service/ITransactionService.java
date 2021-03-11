package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.model.Transaction;

public interface ITransactionService {
	List<Transaction> retrieveAllTransactions();	
	Transaction addTransaction(Transaction p);	
	void deleteTransaction(Long id);	
	Transaction updateTransaction(Transaction p);	
	Transaction retrieveTransaction(String id_t);
}
