package tn.esprit.pidev.controller;

import java.util.List;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pidev.model.RequestCredit;

import tn.esprit.pidev.service.IRequestCreditService;

@RestController
public class RequestCreditController {
	@Autowired
	private static final Logger LOG = LoggerFactory.getLogger(AgencyController.class);
	@Autowired
IRequestCreditService requestservice;
 	// http://localhost:8080/SpringMVC/servlet/retrieve-all-RequestCredits
	 @GetMapping("/retrieve-all-RequestCredits")
	 @ResponseBody
	 public List<RequestCredit> getRequestCredits() {
	 List<RequestCredit> request = requestservice.retrieveAllRequestCredits();
	 return request;
	 }
	//*http://localhost:8080/SpringMVC/servlet/retrieve-RequestCredit/{RequestCredit-id}
	  @GetMapping("/retrieve-RequestCredit/{RequestCredit-id}")
	  @ResponseBody
	  public Optional<RequestCredit> retrieveRequestCredit(@PathVariable("RequestCredit-id") Long id_request) {
	  return requestservice.retrieveRequestCredit(id_request);
	  }
	  // http://localhost:8080/SpringMVC/servlet/add-RequestCredit
	  @PostMapping("/add-RequestCredit")
	  @ResponseBody
	  public RequestCredit addRequestCredit(@RequestBody RequestCredit request) {
	  return requestservice.addRequestCredit(request);
	  
	  }
	// http://localhost:8080/SpringMVC/servlet/remove-RequestCredit/{RequestCredit-id}
	   @DeleteMapping("/remove-RequestCredit/{RequestCredit-id}")
	   @ResponseBody
	   public void removeRequestCredit(@PathVariable("RequestCredit-id") RequestCredit id_request) {
		   requestservice.deleteRequestCredit(id_request);
	   }
	   // http://localhost:8080/SpringMVC/servlet/modify-RequestCredit
	    @PutMapping("/modify-RequestCredit")
	    @ResponseBody
	    public RequestCredit modifyRequestCredit(@RequestBody RequestCredit request){
	    return requestservice.updateRequestCredit(request);
	   }
	    
	    // http://localhost:8080/SpringMVC/servlet/montantarembourser
		  @PostMapping("/montantarembourser")
		  @ResponseBody
		  public float montantarembourser(RequestCredit request, @RequestParam Integer duree,@RequestParam double montant ) {
		  return requestservice.montantarembourserparmois(request,montant, duree);
		  }
	    
	    
}
