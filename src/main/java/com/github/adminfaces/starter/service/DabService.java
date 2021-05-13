/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.infra.model.Filter;
import com.github.adminfaces.starter.infra.model.SortOrder;
import com.github.adminfaces.starter.model.Dab;
import com.github.adminfaces.starter.model.Transaction;
import com.github.adminfaces.starter.model.Client;
import com.github.adminfaces.starter.model.Dab;
import com.github.adminfaces.starter.repository.DabRepository;
import com.github.adminfaces.starter.model.Dab;
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
 *         Dab Business logic
 */
@Component
public class DabService implements Serializable {

   
	@Autowired
	DabRepository rep;

	List<Dab> allDabs;


    public void setAllDabs(List<Dab> allDabs) {
		this.allDabs = allDabs;
	}



	@PostConstruct
    public void init() {
    	allDabs =(List<Dab>) rep.findAll();
    	
    }

	
	
	public List<Dab> getAllDabs() {
		allDabs = (List<Dab>) rep.findAll();
		return allDabs;
	}

    public List<Dab> listByModel(String model) {
        return allDabs.stream()
                .filter(c -> c.getCountry().equalsIgnoreCase(model))
                .collect(Collectors.toList());

    }

    public List<Dab> paginate(Filter<Dab> filter) {
        List<Dab> pdabdDabs = new ArrayList<>();
        if(has(filter.getSortOrder()) && !SortOrder.UNSORTED.equals(filter.getSortOrder())) {
                pdabdDabs = allDabs.stream().
                    sorted((c1, c2) -> {
                        if (filter.getSortOrder().isAscending()) {
                            return c1.getId_dab().compareTo(c2.getId_dab());
                        } else {
                            return c2.getId_dab().compareTo(c1.getId_dab());
                        }
                    })
                    .collect(Collectors.toList());
            }

        int pdab = filter.getFirst() + filter.getPageSize();
        if (filter.getParams().isEmpty()) {
            pdabdDabs = pdabdDabs.subList(filter.getFirst(), pdab > allDabs.size() ? allDabs.size() : pdab);
            return pdabdDabs;
        }

        List<Predicate<Dab>> predicates = configFilter(filter);

        List<Dab> pdabdList = allDabs.stream().filter(predicates
                .stream().reduce(Predicate::or).orElse(t -> true))
                .collect(Collectors.toList());

        if (pdab < pdabdList.size()) {
            pdabdList = pdabdList.subList(filter.getFirst(), pdab);
        }

        if (has(filter.getSortField())) {
            pdabdList = pdabdList.stream().
                    sorted((c1, c2) -> {
                        boolean asc = SortOrder.ASCENDING.equals(filter.getSortOrder());
                        if (asc) {
                            return c1.getId_dab().compareTo(c2.getId_dab());
                        } else {
                            return c2.getId_dab().compareTo(c1.getId_dab());
                        }
                    })
                    .collect(Collectors.toList());
        }
        return pdabdList;
    }

    private List<Predicate<Dab>> configFilter(Filter<Dab> filter) {
        List<Predicate<Dab>> predicates = new ArrayList<>();
        if (filter.hasParam("id")) {
            Predicate<Dab> idPredicate = (Dab c) -> c.getId_dab().equals(filter.getParam("id"));
            predicates.add(idPredicate);
        }

        
        if (has(filter.getEntity())) {
            Dab filterEntity = filter.getEntity();
            if (has(filterEntity.getCountry())) {
                Predicate<Dab> countryPredicate = (Dab c) -> c.getCountry().toLowerCase().contains(filterEntity.getCountry().toLowerCase());
                predicates.add(countryPredicate);
            }


            
            if (has(filterEntity.getCity())) {
                Predicate<Dab> cityPredicate = (Dab c) -> c.getCity().toLowerCase().contains(filterEntity.getCity().toLowerCase());
                predicates.add(cityPredicate);
            }
            if (has(filterEntity.getAdress())) {
                Predicate<Dab> adPredicate = (Dab c) -> c.getAdress().toLowerCase().contains(filterEntity.getAdress().toLowerCase());
                predicates.add(adPredicate);
            }
        }
        return predicates;
    }

    public List<String> getModels(String query) {
        return allDabs.stream().filter(c -> c.getCountry()
                .toLowerCase().contains(query.toLowerCase()))
                .map(Dab::getCountry)
                .collect(Collectors.toList());
    }

    public void insert(Dab dab) {
    	validate(dab);
    	
    	if (allDabs.size() > 0) {
	        dab.setId_dab(allDabs.stream()
	                .mapToLong(c -> c.getId_dab())
	                .max()
	                .getAsLong()+1);
    	}
        allDabs.add(dab);
        rep.save(dab);
    }

    public void validate(Dab dab) {
        BusinessException be = new BusinessException();
        if (!dab.hasCountry()) {
            be.addException(new BusinessException("Country cannot be empty"));
        }
        if (!dab.hasAdress()) {
           be.addException(new BusinessException("Address cannot be empty"));
        }
        if (!dab.hasCity()) {
        	be.addException(new BusinessException("City cannot be empty"));
        }
    
        if(has(be.getExceptionList())) {
            throw be;
        }
    }


    public void remove(Dab dab) {
    	if (rep.findById(dab.getId_dab()).isPresent()) {
    		rep.deleteById(dab.getId_dab());
    	}
    	
        allDabs.remove(dab);
    }

    public long count(Filter<Dab> filter) {
        return allDabs.stream()
                .filter(configFilter(filter).stream()
                        .reduce(Predicate::or).orElse(t -> true))
                .count();
    }

	public Dab findById_dab(Long id) {
		return allDabs.stream().filter(c -> c.getId_dab().equals(id)).findFirst()
				.orElseThrow(() -> new BusinessException("Dab not found with id " + id));
	}

    public void update(Dab dab) {
        allDabs.remove(allDabs.indexOf(dab));
        allDabs.add(dab);
        rep.save(dab);
    }
}
