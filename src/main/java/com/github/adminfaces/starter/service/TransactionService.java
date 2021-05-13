/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.infra.model.Filter;
import com.github.adminfaces.starter.infra.model.SortOrder;
import com.github.adminfaces.starter.model.Car;
import com.github.adminfaces.starter.model.StateT;
import com.github.adminfaces.starter.model.Transaction;
import com.github.adminfaces.starter.repository.TransactionRepository;
import com.github.adminfaces.template.exception.BusinessException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.github.adminfaces.template.util.Assert.has;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author rmpestano
 *         Transaction Business logic
 */
@Component
public class TransactionService implements Serializable {

    
    List<Transaction> allCars;
    
    @Autowired
    TransactionRepository rep;
    @PostConstruct
    public void init() {
    	allCars =(List<Transaction>) rep.findAll();

    }

	
	
	public List<Transaction> getAllCars() {
		allCars = (List<Transaction>) rep.findAll();
		return allCars;
	}

	public void setAllCars(List<Transaction> allCars) {
		this.allCars = allCars;
	}
    public List<Transaction> listByModel(String model) {
        return allCars.stream()
                .filter(c -> c.getState().toString().equalsIgnoreCase(model))
                .collect(Collectors.toList());

    }

    public List<Transaction> paginate(Filter<Transaction> filter) {
        List<Transaction> pagedCars = new ArrayList<>();
        if(has(filter.getSortOrder()) && !SortOrder.UNSORTED.equals(filter.getSortOrder())) {
                pagedCars = allCars.stream().
                    sorted((c1, c2) -> {
                        if (filter.getSortOrder().isAscending()) {
                            return c1.getId().compareTo(c2.getId());
                        } else {
                            return c2.getId().compareTo(c1.getId());
                        }
                    })
                    .collect(Collectors.toList());
            }

        int page = filter.getFirst() + filter.getPageSize();
        if (filter.getParams().isEmpty()) {
            pagedCars = pagedCars.subList(filter.getFirst(), page > allCars.size() ? allCars.size() : page);
            return pagedCars;
        }

        List<Predicate<Transaction>> predicates = configFilter(filter);

        List<Transaction> pagedList = allCars.stream().filter(predicates
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
                            return c1.getId().compareTo(c2.getId());
                        } else {
                            return c2.getId().compareTo(c1.getId());
                        }
                    })
                    .collect(Collectors.toList());
        }
        return pagedList;
    }

    private List<Predicate<Transaction>> configFilter(Filter<Transaction> filter) {
        List<Predicate<Transaction>> predicates = new ArrayList<>();
        if (filter.hasParam("id")) {
            Predicate<Transaction> idPredicate = (Transaction c) -> c.getId().equals(filter.getParam("id"));
            predicates.add(idPredicate);
        }

   /*     if (filter.hasParam("minPrice") && filter.hasParam("maxPrice")) {
            Predicate<Transaction> minMaxPricePredicate = (Transaction c) -> c.getPrice()
                    >= Double.valueOf((String) filter.getParam("minPrice")) && c.getPrice()
                    <= Double.valueOf((String) filter.getParam("maxPrice"));
            predicates.add(minMaxPricePredicate);
        } else if (filter.hasParam("minPrice")) {
            Predicate<Transaction> minPricePredicate = (Transaction c) -> c.getPrice()
                    >= Double.valueOf((String) filter.getParam("minPrice"));
            predicates.add(minPricePredicate);
        } else if (filter.hasParam("maxPrice")) {
            Predicate<Transaction> maxPricePredicate = (Transaction c) -> c.getPrice()
                    <= Double.valueOf((String) filter.getParam("maxPrice"));
            predicates.add(maxPricePredicate);
        }
*/
        if (has(filter.getEntity())) {
            Transaction filterEntity = filter.getEntity();
            if (has(filterEntity.getState())) {
                Predicate<Transaction> modelPredicate = (Transaction c) -> c.getState().toString().toLowerCase().contains(filterEntity.getState().toString().toLowerCase());
                predicates.add(modelPredicate);
            }

 
        }
        return predicates;
    }

    public Collection<StateT> getModels(String query) {
        return allCars.stream().filter(c -> c.getState().toString()
                .toLowerCase().contains(query.toLowerCase()))
                .map(Transaction::getState)
                .collect(Collectors.toList());
    }

    public void insert(Transaction car) {
        validate(car);
        car.setId(allCars.stream()
                .mapToLong(c -> c.getId())
                .max()
                .getAsLong()+1);
        allCars.add(car);
    }

    public void validate(Transaction car) {
        BusinessException be = new BusinessException();
        if (!car.hasState()) {
            be.addException(new BusinessException("Transaction name cannot be empty"));
        }
      if (!car.hasType()) {
           be.addException(new BusinessException("Transaction price cannot be empty"));
        }
    
        if(has(be.getExceptionList())) {
            throw be;
        }
    }


    public void remove(Transaction car) {
        allCars.remove(car);
    }

    public long count(Filter<Transaction> filter) {
        return allCars.stream()
                .filter(configFilter(filter).stream()
                        .reduce(Predicate::or).orElse(t -> true))
                .count();
    }

    public Transaction findById(Long id) {
        return allCars.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BusinessException("Car not found with id " + id));
    }

    public void update(Transaction car) {
        validate(car);
        allCars.remove(allCars.indexOf(car));
        allCars.add(car);
    }
}
