/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.infra.model.Filter;
import com.github.adminfaces.starter.infra.model.SortOrder;
import com.github.adminfaces.starter.model.RequestCredit;
import com.github.adminfaces.starter.model.Client;
import com.github.adminfaces.starter.model.RequestCheckbook;
import com.github.adminfaces.starter.model.RequestCredit;
import com.github.adminfaces.starter.model.RequestCredit;
import com.github.adminfaces.starter.repository.RequestCheckbookRepository;
import com.github.adminfaces.starter.repository.RequestCreditRepository;
import com.github.adminfaces.starter.model.RequestCredit;
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
 *         RequestCredit Business logic
 */
@Component
public class RequestCheckBookService implements Serializable {

   
	@Autowired
	RequestCheckbookRepository rep;

	List<RequestCheckbook> allRequestCredits;


    public void setAllRequestCredits(List<RequestCheckbook> allRequestCredits) {
		this.allRequestCredits = allRequestCredits;
	}



	@PostConstruct
    public void init() {
    	allRequestCredits =(List<RequestCheckbook>) rep.findAll();
    	
    }

	
	
	public List<RequestCheckbook> getAllRequestCredits() {
		allRequestCredits = (List<RequestCheckbook>) rep.findAll();
		return allRequestCredits;
	}

   /* public List<RequestCredit> listByModel(String model) {
        return allRequestCredits.stream()
                .filter(c -> c.getCountry().equalsIgnoreCase(model))
                .collect(Collectors.toList());

    }*/

    public List<RequestCheckbook> paginate(Filter<RequestCheckbook> filter) {
        List<RequestCheckbook> prequestCreditdRequestCredits = new ArrayList<>();
        if(has(filter.getSortOrder()) && !SortOrder.UNSORTED.equals(filter.getSortOrder())) {
                prequestCreditdRequestCredits = allRequestCredits.stream().
                    sorted((c1, c2) -> {
                        if (filter.getSortOrder().isAscending()) {
                            return c1.getId_request().compareTo(c2.getId_request());
                        } else {
                            return c2.getId_request().compareTo(c1.getId_request());
                        }
                    })
                    .collect(Collectors.toList());
            }

        int prequestCredit = filter.getFirst() + filter.getPageSize();
        if (filter.getParams().isEmpty()) {
            prequestCreditdRequestCredits = prequestCreditdRequestCredits.subList(filter.getFirst(), prequestCredit > allRequestCredits.size() ? allRequestCredits.size() : prequestCredit);
            return prequestCreditdRequestCredits;
        }

        List<Predicate<RequestCheckbook>> predicates = configFilter(filter);

        List<RequestCheckbook> prequestCreditdList = allRequestCredits.stream().filter(predicates
                .stream().reduce(Predicate::or).orElse(t -> true))
                .collect(Collectors.toList());

        if (prequestCredit < prequestCreditdList.size()) {
            prequestCreditdList = prequestCreditdList.subList(filter.getFirst(), prequestCredit);
        }

        if (has(filter.getSortField())) {
            prequestCreditdList = prequestCreditdList.stream().
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
        return prequestCreditdList;
    }

    private List<Predicate<RequestCheckbook>> configFilter(Filter<RequestCheckbook> filter) {
    	List<Predicate<RequestCheckbook>> predicates = new ArrayList<>();
        if (filter.hasParam("id")) {
            Predicate<RequestCheckbook> idPredicate = (RequestCheckbook c) -> c.getId_request().equals(filter.getParam("id"));
            predicates.add(idPredicate);
        }

        return predicates;
    }

    

    public void insert(RequestCheckbook requestCredit) {
    	if (allRequestCredits.size() > 0) {
	        requestCredit.setId_request(allRequestCredits.stream()
	                .mapToLong(c -> c.getId_request())
	                .max()
	                .getAsLong()+1);
    	}
        allRequestCredits.add(requestCredit);
        rep.save(requestCredit);
    }




    public void remove(RequestCheckbook requestCredit) {
    	if (rep.findById(requestCredit.getId_request()).isPresent()) {
    		rep.deleteById(requestCredit.getId_request());
    	}
        allRequestCredits.remove(requestCredit);
    }

    public long count(Filter<RequestCheckbook> filter) {
        return allRequestCredits.stream()
                .filter(configFilter(filter).stream()
                        .reduce(Predicate::or).orElse(t -> true))
                .count();
    }

	public RequestCheckbook findById_request(Long id) {
		return allRequestCredits.stream().filter(c -> c.getId_request().equals(id)).findFirst()
				.orElseThrow(() -> new BusinessException("RequestCredit not found with id " + id));
	}

    public void update(RequestCheckbook requestCredit) {
        allRequestCredits.remove(allRequestCredits.indexOf(requestCredit));
        allRequestCredits.add(requestCredit);
        rep.save(requestCredit);
    }
}
