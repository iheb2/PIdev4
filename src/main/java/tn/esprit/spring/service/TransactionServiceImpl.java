package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.model.Transaction;
import tn.esprit.spring.repository.TransactionRepository;;


@Service
public class TransactionServiceImpl implements ITransactionService{

	@Autowired
	TransactionRepository trsansactionRepository ;
	
	
	@Override
	public List<Transaction> retrieveAllTransactions() {
		return (List<Transaction>) trsansactionRepository.findAll();
	}

	
	@Override
	public Transaction addTransaction(Transaction t) {
		return trsansactionRepository.save(t);
	}

	@Override
	public void deleteTransaction(Long id_t) {
		trsansactionRepository.deleteById((long) id_t);
	}

	@Override
	public Transaction updateTransaction(Transaction t) {
		return trsansactionRepository.save(t);
	}
	@Override
	public Transaction retrieveTransaction(String id_t) {
		return trsansactionRepository.findById(Long.parseLong(id_t)).orElse(null);
	}

}
