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
import tn.esprit.pidev.model.Request;

import tn.esprit.pidev.service.IRequestService;

@RestController
public class RequestController {
	@Autowired
IRequestService requestservice;
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
	 
}
