package tn.esprit.pidev.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pidev.model.RequestCreditCard;

import tn.esprit.pidev.service.IRequestCreditCardService;

@RestController
public class RequestCreditCardController {
	@Autowired
IRequestCreditCardService requestservice;
 	// http://localhost:8080/SpringMVC/servlet/retrieve-all-RequestCreditCards
	 @GetMapping("/retrieve-all-RequestCreditCards")
	 @ResponseBody
	 public List<RequestCreditCard> getRequestCreditCards() {
	 List<RequestCreditCard> request = requestservice.retrieveAllRequestCreditCards();
	 return request;
	 }
	//*http://localhost:8080/SpringMVC/servlet/retrieve-RequestCreditCard/{RequestCreditCard-id}
	  @GetMapping("/retrieve-RequestCreditCard/{RequestCreditCard-id}")
	  @ResponseBody
	  public Optional<RequestCreditCard> retrieveRequestCreditCard(@PathVariable("RequestCreditCard-id") Long id_request) {
	  return requestservice.retrieveRequestCreditCard(id_request);
	  }
	  // http://localhost:8080/SpringMVC/servlet/add-RequestCreditCard
	  @PostMapping("/add-RequestCreditCard")
	  @ResponseBody
	  public RequestCreditCard addRequestCreditCard(@RequestBody RequestCreditCard request) {
	  return requestservice.addRequestCreditCard(request);
	  
	  }
	// http://localhost:8080/SpringMVC/servlet/remove-RequestCreditCard/{RequestCreditCard-id}
	   @DeleteMapping("/remove-RequestCreditCard/{RequestCreditCard-id}")
	   @ResponseBody
	   public void removeRequestCreditCard(@PathVariable("RequestCreditCard-id") RequestCreditCard id_request) {
		   requestservice.deleteRequestCreditCard(id_request);
	   }
	   // http://localhost:8080/SpringMVC/servlet/modify-RequestCreditCard
	    @PutMapping("/modify-RequestCreditCard")
	    @ResponseBody
	    public RequestCreditCard modifyRequestCreditCard(@RequestBody RequestCreditCard request){
	    return requestservice.updateRequestCreditCard(request);
	   }
	 
}
