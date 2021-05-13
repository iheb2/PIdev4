package com.github.adminfaces.starter.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.adminfaces.starter.model.Request;
import com.github.adminfaces.starter.model.SavedRequests;
import com.github.adminfaces.starter.repository.RequestRepository;
import com.github.adminfaces.starter.repository.SavedRequestsRepository;


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
		/*if(request.getClient().getScore()>50){
			request.setStatus("Validée");
		
			return requestrepo.save(request);

		}
		else 
		{*/request.setStatus("Encours");
		 return requestrepo.save(request);
		//}		
		
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
		
		//return requestrepo.save(request) ;
		if(request.getClient().getScore()>50){
			request.setStatus("Validée");
		
			return requestrepo.save(request);

		}
		
		else 
		{request.setStatus("Encours");
		 return requestrepo.save(request);
		}
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
		public Map<Integer,Integer> StatisticRequestByType(int type) {
			Map<Integer, Integer> MapRequest = new HashMap<Integer, Integer>();
			int checkbook_c = 0;
			int creditcard_c = 0;
			int credit_c = 0;
				
			List<Request> ListRequests = listRequest(type)  ;
			for (int i = 0; i < ListRequests.size(); i++) {
				if (type == 1)
				{
					checkbook_c += 1;
					MapRequest.put(1, checkbook_c);
				}
				else if (type == 2)
				{
					creditcard_c += 1;
					MapRequest.put(2, creditcard_c);
				}
				else if (type == 3)
				{
					credit_c += 1;
					MapRequest.put(3,  credit_c);
				}
			}
			return MapRequest;
		}
		
		@SuppressWarnings({ "null", "deprecation" })
		@Override
		public List<Request> listRequest(int type) {

			List<Request> LR = (List<Request>)requestrepo.findAll();
			List<Request> ListR = new ArrayList<Request>();
			for (int i = 0; i < LR.size(); i++) {
				System.out.print("Type " + LR.get(i).getType() + "\n");

				if (LR.get(i).getType() == "credit" || LR.get(i).getType() == "credit_card" || LR.get(i).getType() == "checkbook") {

					ListR.add(LR.get(i));

					System.out.print("Add Type " + LR.get(i).getType());

				}
			}
			return ListR;
		}
		
		@Override
		public Map<Double, Double> StatisticCreatedPerMonth(int year) {
			Map<Double, Double> MA = new HashMap<Double, Double>();
			double m1 = 0;
			double m2 = 0;
			double m3 = 0;
			double m4 = 0;
			double m5 = 0;
			double m6 = 0;
			double m7 = 0;
			double m8 = 0;
			double m9 = 0;
			double m10 = 0;
			double m11 = 0;
			double m12 = 0;
			List<Request> L = listRequestByDate(year);
			for (int i = 0; i < L.size(); i++) {
				String dateToStr = String.format("%1$tY-%1$tm-%1$td", L.get(i).getCr_date());
				String[] dateParts = dateToStr.split("-");
				String month = dateParts[1];
				if (month.equals("01"))

				{
					m1 += 1;
					MA.put((double) 1, m1);
				}

				else if (month.equals("02")) {
					m2 += 1;
					MA.put((double) 2, m2);
				}
				
				else if (month.equals("03")) {
					m3 += 1;
					MA.put((double) 3, m3);
				}

				else if (month.equals("04")) {
					m4 += 1;
					MA.put((double) 4, m4);
				}

				else if (month.equals("05")) {
					m5 += 1;
					MA.put((double) 5, m5);
				}
			
				
				else if (month.equals("06")) {
					m6 += 1;
					MA.put((double) 6, m6);
				}
				
				else if (month.equals("07")) {
					m7 += 1;
					MA.put((double) 7, m7);
				}
				
				else if (month.equals("08")) {
					m8 += 1;
					MA.put((double) 8, m8);
				}
				
				else if (month.equals("09")) {
					m9 += 1;
					MA.put((double) 9, m9);
				}
				
				else if (month.equals("10")) {
					m10 += 1;
					MA.put((double) 10, m10);
				}
				
				else if (month.equals("11")) {
					m11 += 1;
					MA.put((double) 11, m11);
				}
				
				else if (month.equals("12")) {
					m12 += 1;
					MA.put((double) 12, m12);
				}
				
			}

			return MA;
		}
		
		@SuppressWarnings({ "null", "deprecation" })
		@Override
		public List<Request> listRequestByDate(int year) {

			List<Request> L = (List<Request>) requestrepo.findAll();
			List<Request> ListR = new ArrayList<Request>();
			for (int i = 0; i < L.size(); i++) {
				System.out.print("YEAR " + Integer.parseInt(new SimpleDateFormat("yyyy").format(L.get(i).getCr_date())) + "\n");

				if (Integer.parseInt(new SimpleDateFormat("yyyy").format(L.get(i).getCr_date())) == year) {

					String dateToStr = String.format("%1$tY-%1$tm-%1$td", L.get(i).getCr_date());

					ListR.add(L.get(i));

					System.out.print("Add year  "	+ Integer.parseInt(new SimpleDateFormat("yyyy").format(L.get(i).getCr_date())));

				}
			}
			return ListR;
		}
	}

