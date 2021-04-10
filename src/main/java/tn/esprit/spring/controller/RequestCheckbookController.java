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
import tn.esprit.pidev.model.RequestCheckbook;

import tn.esprit.pidev.service.IRequestCheckbookService;

@RestController
public class RequestCheckbookController {
	@Autowired
IRequestCheckbookService requestservice;
 	// http://localhost:8080/SpringMVC/servlet/retrieve-all-RequestCheckbooks
	 @GetMapping("/retrieve-all-RequestCheckbooks")
	 @ResponseBody
	 public List<RequestCheckbook> getRequestCheckbooks() {
	 List<RequestCheckbook> request = requestservice.retrieveAllRequestCheckbooks();
	 return request;
	 }
	//*http://localhost:8080/SpringMVC/servlet/retrieve-RequestCheckbook/{RequestCheckbook-id}
	  @GetMapping("/retrieve-RequestCheckbook/{RequestCheckbook-id}")
	  @ResponseBody
	  public Optional<RequestCheckbook> retrieveRequestCheckbook(@PathVariable("RequestCheckbook-id") Long id_request) {
	  return requestservice.retrieveRequestCheckbook(id_request);
	  }
	  // http://localhost:8080/SpringMVC/servlet/add-RequestCheckbook
	  @PostMapping("/add-RequestCheckbook")
	  @ResponseBody
	  public RequestCheckbook addRequestCheckbook(@RequestBody RequestCheckbook request) {
	  return requestservice.addRequestCheckbook(request);
	  
	  }
	// http://localhost:8080/SpringMVC/servlet/remove-RequestCheckbook/{RequestCheckbook-id}
	   @DeleteMapping("/remove-RequestCheckbook/{RequestCheckbook-id}")
	   @ResponseBody
	   public void removeRequestCheckbook(@PathVariable("RequestCheckbook-id") RequestCheckbook id_request) {
		   requestservice.deleteRequestCheckbook(id_request);
	   }
	   // http://localhost:8080/SpringMVC/servlet/modify-RequestCheckbook
	    @PutMapping("/modify-RequestCheckbook")
	    @ResponseBody
	    public RequestCheckbook modifyRequestCheckbook(@RequestBody RequestCheckbook request){
	    return requestservice.updateRequestCheckbook(request);
	   }
	 
}
