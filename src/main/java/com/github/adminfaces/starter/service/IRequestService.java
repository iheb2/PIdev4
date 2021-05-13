package com.github.adminfaces.starter.service;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import com.github.adminfaces.starter.model.Request;
import com.github.adminfaces.starter.model.SavedRequests;

public interface IRequestService {
	List<Request> retrieveAllRequests();
	void deleteRequest(Request request);
	Request updateRequest(Request request);
	Optional<Request> retrieveRequest(Long id_request);
	Request addRequest(Request request);
	Request acceptRequest(Request request);
	String RecommendRequest();
	Optional<SavedRequests> retrieveSavedRequest(Long id_request);
	Map<Integer, Integer> StatisticRequestByType(int type);
	List<Request> listRequest(int type);
	List<Request> listRequestByDate(int year);
	Map<Double, Double> StatisticCreatedPerMonth(int year);	
}
