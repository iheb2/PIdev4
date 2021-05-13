package com.github.adminfaces.starter.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.github.adminfaces.starter.model.Client;
import com.github.adminfaces.starter.model.Request;
import com.github.adminfaces.starter.model.SavedRequests;
import com.github.adminfaces.starter.service.IRequestService;

@Scope(value = "request")
@RestController(value = "RequestController") // Name of the bean in Spring IoC
@ELBeanName(value = "RequestController") // Name of the bean used by JSF
@Join(path = "/", to = "/request.jsf")
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
		 client.setId((long)1);
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
	    
	 // http://localhost:8080/SpringMVC/servlet/accept-Request
		  @PutMapping("/accept-Request")
		  @ResponseBody
		  public Request acceptRequest(@RequestBody Request request) {
			  //Optional<SavedRequests>  SavedRequests =   requestservice.retrieveSavedRequest(id_request.getId_request());
			 // Request request= new Request(SavedRequests.getEmail());
		  return requestservice.acceptRequest(request);
		  
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

		// http://localhost:8080/SpringMVC/servlet/stat-type/{type}
		  @GetMapping("/stat-type/{type}")
			public String statisticRequestByType(@PathVariable("type") int type) throws IOException {
			  Map<Integer, Integer> MapRequest = new HashMap<Integer, Integer>();
			  MapRequest = requestservice.StatisticRequestByType(type);
				String json = new ObjectMapper().writeValueAsString(MapRequest);
				return json;
				
			}
		  
		// http://localhost:8080/SpringMVC/servlet/stat-year/{year}
		  @GetMapping("/stat-year/{year}")
			public String statisticByMonthYear(@PathVariable("year") int year) throws IOException {
				Map<Double, Double> MA = new HashMap<Double, Double>();
			    MA = requestservice.StatisticCreatedPerMonth(year);	
				String json = new ObjectMapper().writeValueAsString(MA);
				return json;
			}
}
