package tn.esprit.spring.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.model.Transaction;
import tn.esprit.spring.service.ITransactionService;

@Controller
public class TransactionController {

	@Autowired
	ITransactionService transactionService;
	
	@GetMapping("/retrieve-all-Transactions")
	@ResponseBody
	public List<Transaction> getTransactions() {
		List<Transaction> list = transactionService.retrieveAllTransactions();
		return list;
		
	}

	@PostMapping("/add-Transaction")
	@ResponseBody
	public Transaction addTransaction(@RequestBody Transaction c) {
		Transaction Transaction = transactionService.addTransaction(c);
		return Transaction;
	}
	
	@DeleteMapping("/remove-Transaction/{Transaction-id}")
	@ResponseBody
	public void removeTransaction (@PathVariable("Transaction-id") Long id_Transaction) {
		transactionService.deleteTransaction(id_Transaction);;
	}
	
	@PutMapping("/modify-Transaction")
	@ResponseBody
	public Transaction modifyTransaction(@RequestBody Transaction c) {
		return transactionService.updateTransaction(c);
	}
	@GetMapping("/retrieve-Transaction/{Transaction-id}")
	@ResponseBody
	public Transaction retrieveTransaction(@PathVariable("Transaction-id") String  id_t) {
		return transactionService.retrieveTransaction(id_t);
	}
}
