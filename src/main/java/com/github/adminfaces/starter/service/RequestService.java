/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.infra.model.Filter;
import com.github.adminfaces.starter.infra.model.SortOrder;
import com.github.adminfaces.starter.model.Request;
import com.github.adminfaces.starter.model.Client;
import com.github.adminfaces.starter.model.Request;
import com.github.adminfaces.starter.repository.RequestRepository;
import com.github.adminfaces.starter.model.Request;
import com.github.adminfaces.template.exception.BusinessException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.github.adminfaces.template.util.Assert.has;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author rmpestano
 *         Request Business logic
 */
@Component
public class RequestService implements Serializable {

   
	@Autowired
	RequestRepository rep;

	List<Request> allRequests;


    public void setAllRequests(List<Request> allRequests) {
		this.allRequests = allRequests;
	}



	@PostConstruct
    public void init() {
    	allRequests =(List<Request>) rep.findAll();
    	
    }

	
	
	public List<Request> getAllRequests() {
		allRequests = (List<Request>) rep.findAll();
		return allRequests;
	}

    public List<Request> listByModel(String model) {
        return allRequests.stream()
                .filter(c -> c.getType().equalsIgnoreCase(model))
                .collect(Collectors.toList());

    }

    public List<Request> paginate(Filter<Request> filter) {
        List<Request> prequestdRequests = new ArrayList<>();
        if(has(filter.getSortOrder()) && !SortOrder.UNSORTED.equals(filter.getSortOrder())) {
                prequestdRequests = allRequests.stream().
                    sorted((c1, c2) -> {
                        if (filter.getSortOrder().isAscending()) {
                            return c1.getId_request().compareTo(c2.getId_request());
                        } else {
                            return c2.getId_request().compareTo(c1.getId_request());
                        }
                    })
                    .collect(Collectors.toList());
            }

        int prequest = filter.getFirst() + filter.getPageSize();
        if (filter.getParams().isEmpty()) {
            prequestdRequests = prequestdRequests.subList(filter.getFirst(), prequest > allRequests.size() ? allRequests.size() : prequest);
            return prequestdRequests;
        }

        List<Predicate<Request>> predicates = configFilter(filter);

        List<Request> prequestdList = allRequests.stream().filter(predicates
                .stream().reduce(Predicate::or).orElse(t -> true))
                .collect(Collectors.toList());

        if (prequest < prequestdList.size()) {
            prequestdList = prequestdList.subList(filter.getFirst(), prequest);
        }

        if (has(filter.getSortField())) {
            prequestdList = prequestdList.stream().
                    sorted((c1, c2) -> {
                        boolean asc = SortOrder.ASCENDING.equals(filter.getSortOrder());
                        if (asc) {
                            return c1.getId_request().compareTo(c2.getId_request());
                        } else {
                            return c2.getId_request().compareTo(c1.getId_request());
                        }
                    })
                    .collect(Collectors.toList());
        }
        return prequestdList;
    }

    private List<Predicate<Request>> configFilter(Filter<Request> filter) {
        List<Predicate<Request>> predicates = new ArrayList<>();
        if (filter.hasParam("id")) {
            Predicate<Request> idPredicate = (Request c) -> c.getId_request().equals(filter.getParam("id"));
            predicates.add(idPredicate);
        }

        
        if (has(filter.getEntity())) {
            Request filterEntity = filter.getEntity();
            if (has(filterEntity.getStatus())) {
                Predicate<Request> countryPredicate = (Request c) -> c.getStatus().toLowerCase().contains(filterEntity.getStatus().toLowerCase());
                predicates.add(countryPredicate);
            }


            
            if (has(filterEntity.getType())) {
                Predicate<Request> cityPredicate = (Request c) -> c.getType().toLowerCase().contains(filterEntity.getType().toLowerCase());
                predicates.add(cityPredicate);
            }
            
        }
        return predicates;
    }

    public List<String> getModels(String query) {
        return allRequests.stream().filter(c -> c.getType()
                .toLowerCase().contains(query.toLowerCase()))
                .map(Request::getType)
                .collect(Collectors.toList());
    }

    public void insert(Request request) {
    	if (allRequests.size() > 0) {
	        request.setId_request(allRequests.stream()
	                .mapToLong(c -> c.getId_request())
	                .max()
	                .getAsLong()+1);
    	}
        allRequests.add(request);
        rep.save(request);
    }




    public void remove(Request request) {
    	if (rep.findById(request.getId_request()).isPresent()) {
    		rep.deleteById(request.getId_request());
    	}
        allRequests.remove(request);
    }

    public long count(Filter<Request> filter) {
        return allRequests.stream()
                .filter(configFilter(filter).stream()
                        .reduce(Predicate::or).orElse(t -> true))
                .count();
    }

	public Request findById_request(Long id) {
		return allRequests.stream().filter(c -> c.getId_request().equals(id)).findFirst()
				.orElseThrow(() -> new BusinessException("Request not found with id " + id));
	}

    public void update(Request request) {
        allRequests.remove(allRequests.indexOf(request));
        allRequests.add(request);
        rep.save(request);
    }



}
