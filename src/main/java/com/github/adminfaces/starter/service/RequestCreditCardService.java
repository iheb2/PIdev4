/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.infra.model.Filter;
import com.github.adminfaces.starter.infra.model.SortOrder;
import com.github.adminfaces.starter.model.RequestCredit;
import com.github.adminfaces.starter.model.RequestCreditCard;
import com.github.adminfaces.starter.model.Client;
import com.github.adminfaces.starter.model.RequestCredit;
import com.github.adminfaces.starter.model.RequestCredit;
import com.github.adminfaces.starter.repository.RequestCreditCardRepository;
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
public class RequestCreditCardService implements Serializable {

   
	@Autowired
	RequestCreditCardRepository rep;

	List<RequestCreditCard> allRequestCredits;


    public void setAllRequestCredits(List<RequestCreditCard> allRequestCredits) {
		this.allRequestCredits = allRequestCredits;
	}



	@PostConstruct
    public void init() {
    	allRequestCredits =(List<RequestCreditCard>) rep.findAll();
    	
    }

	
	
	public List<RequestCreditCard> getAllRequestCredits() {
		allRequestCredits = (List<RequestCreditCard>) rep.findAll();
		return allRequestCredits;
	}

   /* public List<RequestCredit> listByModel(String model) {
        return allRequestCredits.stream()
                .filter(c -> c.getCountry().equalsIgnoreCase(model))
                .collect(Collectors.toList());

    }*/

    public List<RequestCreditCard> paginate(Filter<RequestCreditCard> filter) {
        List<RequestCreditCard> prequestCreditdRequestCredits = new ArrayList<>();
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

        List<Predicate<RequestCreditCard>> predicates = configFilter(filter);

        List<RequestCreditCard> prequestCreditdList = allRequestCredits.stream().filter(predicates
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

    private List<Predicate<RequestCreditCard>> configFilter(Filter<RequestCreditCard> filter) {
    	List<Predicate<RequestCreditCard>> predicates = new ArrayList<>();
        if (filter.hasParam("id")) {
            Predicate<RequestCreditCard> idPredicate = (RequestCreditCard c) -> c.getId_request().equals(filter.getParam("id"));
            predicates.add(idPredicate);
        }

        return predicates;
    }

    

    public void insert(RequestCreditCard requestCredit) {
    	if (allRequestCredits.size() > 0) {
	        requestCredit.setId_request(allRequestCredits.stream()
	                .mapToLong(c -> c.getId_request())
	                .max()
	                .getAsLong()+1);
    	}
        allRequestCredits.add(requestCredit);
        rep.save(requestCredit);
    }




    public void remove(RequestCreditCard requestCredit) {
    	if (rep.findById(requestCredit.getId_request()).isPresent()) {
    		rep.deleteById(requestCredit.getId_request());
    	}
        allRequestCredits.remove(requestCredit);
    }

    public long count(Filter<RequestCreditCard> filter) {
        return allRequestCredits.stream()
                .filter(configFilter(filter).stream()
                        .reduce(Predicate::or).orElse(t -> true))
                .count();
    }

	public RequestCreditCard findById_request(Long id) {
		return allRequestCredits.stream().filter(c -> c.getId_request().equals(id)).findFirst()
				.orElseThrow(() -> new BusinessException("RequestCredit not found with id " + id));
	}

    public void update(RequestCreditCard requestCredit) {
        allRequestCredits.remove(allRequestCredits.indexOf(requestCredit));
        allRequestCredits.add(requestCredit);
        rep.save(requestCredit);
    }
}
