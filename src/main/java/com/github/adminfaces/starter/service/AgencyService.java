/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.infra.model.Filter;
import com.github.adminfaces.starter.infra.model.SortOrder;
import com.github.adminfaces.starter.model.Agency;
import com.github.adminfaces.starter.model.Client;
import com.github.adminfaces.starter.model.Dab;
import com.github.adminfaces.starter.model.Agency;
import com.github.adminfaces.starter.repository.AgencyRepository;
import com.github.adminfaces.starter.model.Agency;
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
 *         Agency Business logic
 */
@Component
public class AgencyService implements Serializable {

   
	@Autowired
	AgencyRepository rep;

	List<Agency> allAgencys;


    public void setAllAgencys(List<Agency> allAgencys) {
		this.allAgencys = allAgencys;
	}



	@PostConstruct
    public void init() {
    	allAgencys =(List<Agency>) rep.findAll();
    	
    }

	
	
	public List<Agency> getAllAgencys() {
		allAgencys = (List<Agency>) rep.findAll();
		return allAgencys;
	}

    public List<Agency> listByModel(String model) {
        return allAgencys.stream()
                .filter(c -> c.getCountry().equalsIgnoreCase(model))
                .collect(Collectors.toList());

    }

    public List<Agency> paginate(Filter<Agency> filter) {
        List<Agency> pagedAgencys = new ArrayList<>();
        if(has(filter.getSortOrder()) && !SortOrder.UNSORTED.equals(filter.getSortOrder())) {
                pagedAgencys = allAgencys.stream().
                    sorted((c1, c2) -> {
                        if (filter.getSortOrder().isAscending()) {
                            return c1.getId_agency().compareTo(c2.getId_agency());
                        } else {
                            return c2.getId_agency().compareTo(c1.getId_agency());
                        }
                    })
                    .collect(Collectors.toList());
            }

        int page = filter.getFirst() + filter.getPageSize();
        if (filter.getParams().isEmpty()) {
            pagedAgencys = pagedAgencys.subList(filter.getFirst(), page > allAgencys.size() ? allAgencys.size() : page);
            return pagedAgencys;
        }

        List<Predicate<Agency>> predicates = configFilter(filter);

        List<Agency> pagedList = allAgencys.stream().filter(predicates
                .stream().reduce(Predicate::or).orElse(t -> true))
                .collect(Collectors.toList());

        if (page < pagedList.size()) {
            pagedList = pagedList.subList(filter.getFirst(), page);
        }

        if (has(filter.getSortField())) {
            pagedList = pagedList.stream().
                    sorted((c1, c2) -> {
                        boolean asc = SortOrder.ASCENDING.equals(filter.getSortOrder());
                        if (asc) {
                            return c1.getId_agency().compareTo(c2.getId_agency());
                        } else {
                            return c2.getId_agency().compareTo(c1.getId_agency());
                        }
                    })
                    .collect(Collectors.toList());
        }
        return pagedList;
    }

    private List<Predicate<Agency>> configFilter(Filter<Agency> filter) {
        List<Predicate<Agency>> predicates = new ArrayList<>();
        if (filter.hasParam("id")) {
            Predicate<Agency> idPredicate = (Agency c) -> c.getId_agency().equals(filter.getParam("id"));
            predicates.add(idPredicate);
        }

        if (filter.hasParam("phone")) {
            Predicate<Agency> phPredicate = (Agency c) -> c.getPhone().equals(filter.getParam("phone"));
            predicates.add(phPredicate);
        }
        if (has(filter.getEntity())) {
            Agency filterEntity = filter.getEntity();
            if (has(filterEntity.getCountry())) {
                Predicate<Agency> countryPredicate = (Agency c) -> c.getCountry().toLowerCase().contains(filterEntity.getCountry().toLowerCase());
                predicates.add(countryPredicate);
            }


            if (has(filterEntity.getEmail())) {
                Predicate<Agency> emailPredicate = (Agency c) -> c.getEmail().toLowerCase().contains(filterEntity.getEmail().toLowerCase());
                predicates.add(emailPredicate);
            }
            if (has(filterEntity.getCity())) {
                Predicate<Agency> cityPredicate = (Agency c) -> c.getCity().toLowerCase().contains(filterEntity.getCity().toLowerCase());
                predicates.add(cityPredicate);
            }
            if (has(filterEntity.getAdress())) {
                Predicate<Agency> adPredicate = (Agency c) -> c.getAdress().toLowerCase().contains(filterEntity.getAdress().toLowerCase());
                predicates.add(adPredicate);
            }
        }
        return predicates;
    }

    public List<String> getModels(String query) {
        return allAgencys.stream().filter(c -> c.getCountry()
                .toLowerCase().contains(query.toLowerCase()))
                .map(Agency::getCountry)
                .collect(Collectors.toList());
    }

    public void insert(Agency age) {
    	validate(age);
    	
    	if (allAgencys.size() > 0) {
	        age.setId_agency(allAgencys.stream()
	                .mapToLong(c -> c.getId_agency())
	                .max()
	                .getAsLong()+1);
    	}
        allAgencys.add(age);
        rep.save(age);
    }

    public void validate(Agency agency) {
        BusinessException be = new BusinessException();
        if (!agency.hasEmail()) {
            be.addException(new BusinessException("Email cannot be empty"));
        }
        if (!agency.hasCountry()) {
            be.addException(new BusinessException("Country cannot be empty"));
        }
        if (!agency.hasAdress()) {
           be.addException(new BusinessException("Address cannot be empty"));
        }
        if (!agency.hasCity()) {
        	be.addException(new BusinessException("City cannot be empty"));
        }
        if (!agency.hasPhone()) {
        	be.addException(new BusinessException("Phone cannot be empty"));
        }
    
        if(has(be.getExceptionList())) {
            throw be;
        }
    }


    public void remove(Agency age) {
    	if (rep.findById(age.getId_agency()).isPresent()) {
    		rep.deleteById(age.getId_agency());
    	}
        allAgencys.remove(age);
    }

    public long count(Filter<Agency> filter) {
        return allAgencys.stream()
                .filter(configFilter(filter).stream()
                        .reduce(Predicate::or).orElse(t -> true))
                .count();
    }

	public Agency findById_agency(Long id) {
		return allAgencys.stream().filter(c -> c.getId_agency().equals(id)).findFirst()
				.orElseThrow(() -> new BusinessException("Agency not found with id " + id));
	}

    public void update(Agency age) {
        allAgencys.remove(allAgencys.indexOf(age));
        allAgencys.add(age);
        rep.save(age);
    }
}
