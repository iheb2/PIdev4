package com.github.adminfaces.starter.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.adminfaces.starter.model.Transaction;
import com.github.adminfaces.starter.service.ITransactionService;


@Named
@ViewScoped
public class TransactionList implements Serializable{
	@Autowired
	ITransactionService transactionservice;
	private List<Transaction> transactions;
	public List<Transaction> getTransactions() {
		transactions= transactionservice.retrieveAllTransactions();
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
	
	
	
}
