package tn.esprit.pidev.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pidev.model.Request;
import tn.esprit.pidev.model.SavedRequests;
import tn.esprit.pidev.repository.RequestRepository;
import tn.esprit.pidev.repository.SavedRequestsRepository;

@Service
public class RequestServiceImpl implements IRequestService{
	@Autowired
   RequestRepository requestrepo;
	@Autowired
	   SavedRequestsRepository savedrequestrepo; 

	
	@Autowired
	private static final Logger LOG = LoggerFactory.getLogger(RequestServiceImpl.class);
	@Override
	public List<Request> retrieveAllRequests() {
		List<Request> requestlist =(List<Request>)requestrepo.findAll();
		return requestlist;
	}

	@Override
	public Request addRequest(Request request ) {
		if(request.getClient().getScore()>50){
			return requestrepo.save(request);

		}
		else 
		{SavedRequests savedrequest;
		SavedRequests	savedrequest1=new SavedRequests(request.getId_request());
		LOG.info("we're handling your request");
		 savedrequestrepo.save(savedrequest1);
		 return request;
		}
	
		
		
		
	}

	@Override
	public void deleteRequest(Request request) {
		requestrepo.deleteById(request.getId_request());
		
	}

	
	@Override
	public Request updateRequest(Request request) {
		return requestrepo.save(request);
	}

	@Override
	public Optional<Request> retrieveRequest(Long id_request) {
		return requestrepo.findById(id_request);
	}
	@Override
	public Optional<SavedRequests> retrieveSavedRequest(Long id_request) {
		return savedrequestrepo.findById(id_request);
	}
	@Override
	public Request acceptRequest(Request request) {
		
		return requestrepo.save(request) ;
	}

	@Override
	public String RecommendRequest() {
	long  a ,b ,c ;
		a=requestrepo.countcreditrequest("request_credit", 1);
	

		b=requestrepo.countcreditrequestcard("request_credit_card",1);
		c=requestrepo.countcheckbookrequest("request_checkbook",1);
		
		if(a>b && a>c) {
			return "request_credit";
			
		}
		else if (b>a && b>c) {
			return "request_credit_card";
			
		}else if (c>b && c>a) {
			return "request_checkbook";

		}
		return null;
	}
		

		@Override
		public Map<String,Integer> StatisticRequestByType(String type) {
			Map<String, Integer> MapRequest = new HashMap<String, Integer>();
			int checkbook_c = 0;
			int creditcard_c = 0;
			int credit_c = 0;
			List<Request> ListRequests = retrieveAllRequests();
			for (int i = 0; i < ListRequests.size(); i++) {
				if (type == "Checkbook")
				{
					MapRequest.put("Checkbook", checkbook_c + 1);
				}
				else if (type == "CreditCard")
				{
					MapRequest.put("CreditCard", creditcard_c + 1);
				}
				else if (type == "Credit")
				{
					MapRequest.put("Credit", credit_c + 1);
				}
			}
			return MapRequest;
		}
	}

