package tn.esprit.spring.controller;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.lowagie.text.DocumentException;

import tn.esprit.spring.model.AmalWacelGhada;
import tn.esprit.spring.model.Transaction;
import tn.esprit.spring.model.mode;
import tn.esprit.spring.service.ITransactionService;
import tn.esprit.spring.service.UserPDFExporter;

import java.time.Duration;
import java.time.temporal.Temporal;
import java.util.Date;





@Controller
public class TransactionController {

	@Autowired
	ITransactionService transactionService;
	
 @GetMapping("/users/export/pdf/{id}")
	   public void exportToPDF(@PathVariable("id") String id,HttpServletResponse response) throws DocumentException, IOException {
	
		 response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	       String currentDateTime = dateFormatter.format(new Date());
	        
	       String headerKey = "Content-Disposition";
	       String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
	       response.setHeader(headerKey, headerValue);
	        
	       List<Transaction> listTransactions = transactionService.Historique(id);
	        
	       UserPDFExporter exporter = new UserPDFExporter(listTransactions);
	       exporter.export(response);
	        
	   }
	

 @PutMapping("/pay-Transaction-auto/{Transaction-id}")
	@ResponseBody
	public void payTransactionauto(@PathVariable("Transaction-id") Long id_Transaction) {
		 transactionService. payment_auto();
	}
	@GetMapping("/retrieve-all-Transactions")
	@ResponseBody
	public List<Transaction> getTransactions() {
		List<Transaction> list = transactionService.retrieveAllTransactions();
		return list;
		
	}
	@GetMapping("/retrieve-Historique/{id}")
	@ResponseBody
	public List<Transaction> getHistorique(@PathVariable("id") String id_c)  {
		List<Transaction> list = transactionService.Historique(id_c);
		return list;
		
	}
	@PostMapping("/retrieve-Transactions-ac/{mode}")
	@ResponseBody
	public void getTransactionsac(@RequestBody AmalWacelGhada  a,@PathVariable("mode") mode m) {
		 transactionService.retrieveCreditTransaction(a, m);}
		
	
	
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
	@PutMapping("/pay-Transaction/{Transaction-id}")
	@ResponseBody
	public String payTransaction(@PathVariable("Transaction-id") Long id_Transaction) {
		return transactionService.paycreditDirect(id_Transaction);
	}
	@GetMapping("/retrieve-Transaction/{Transaction-id}")
	@ResponseBody
	public Transaction retrieveTransaction(@PathVariable("Transaction-id") String  id_t) {
		return transactionService.retrieveTransaction(id_t);
	}
	@PostMapping("/debit/{id}")
	@ResponseBody
	public void getTransactionsac(@RequestBody double  a,@PathVariable("id") String id) {
		 transactionService.retrait( id, a);}
		

}
