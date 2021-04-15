package tn.esprit.pidev.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import tn.esprit.pidev.model.Client;
import tn.esprit.pidev.model.Request;
import tn.esprit.pidev.model.SavedRequests;
import tn.esprit.pidev.service.IRequestService;

@RestController
public class RequestController {
	@Autowired
IRequestService requestservice;
	Long  savedrequest ;
	
 	// http://localhost:8080/SpringMVC/servlet/retrieve-all-Requests
	 @GetMapping("/retrieve-all-Requests")
	 @ResponseBody
	 public List<Request> getRequests() {
	 List<Request> request = requestservice.retrieveAllRequests();
	 return request;
	 }
	//*http://localhost:8080/SpringMVC/servlet/retrieve-Request/{Request-id}
	  @GetMapping("/retrieve-Request/{Request-id}")
	  @ResponseBody
	  public Optional<Request> retrieveRequest(@PathVariable("Request-id") Long id_request) {
	  return requestservice.retrieveRequest(id_request);
	  }
	  // http://localhost:8080/SpringMVC/servlet/add-Request
	  @PostMapping("/add-Request")
	  @ResponseBody
	  public Request addRequest(@RequestBody Request request) {
		
		    Client client=new Client();
		 client.setId_client(1);
		  request.setClient(client);
		  return requestservice.addRequest(request);
		  
		  
	  }
	// http://localhost:8080/SpringMVC/servlet/remove-Request/{Request-id}
	   @DeleteMapping("/remove-Request/{Request-id}")
	   @ResponseBody
	   public void removeRequest(@PathVariable("Request-id") Request id_request) {
		   requestservice.deleteRequest(id_request);
	   }
	   
	   // http://localhost:8080/SpringMVC/servlet/modify-Request
	    @PutMapping("/modify-Request")
	    @ResponseBody
	    public Request modifyRequest(@RequestBody Request request){
	    return requestservice.updateRequest(request);
	   }
	/* // http://localhost:8080/SpringMVC/servlet/accept-Request/{Request-id}
		  @PostMapping("/accept-Request/{Request-id}")
		  @ResponseBody
		  public Request acceptRequest(@PathVariable("Request-id") SavedRequests id_request, Request request) {
			  Optional<SavedRequests>  SavedRequests=   requestservice.retrieveSavedRequest(id_request.getId_request());
			 // Request request= new Request(SavedRequests.getEmail());
		  return requestservice.acceptRequest(request);
		  
		  }*/
	    
	 // http://localhost:8080/SpringMVC/servlet/recommend
		  @GetMapping("/recommend")
			 @ResponseBody
			 public String recommend() {
			  String recommendation =requestservice.RecommendRequest();
			 return recommendation;
			 }

		// http://localhost:8080/SpringMVC/servlet/StatRequestByType
			 @GetMapping("/StatRequestByType")
			 @ResponseBody
			 public Map<String,Integer> StatisticRequestByType(String type) {
				 Map<String, Integer> MapRequest = requestservice.StatisticRequestByType(type);
			 return MapRequest;
			 }
}
