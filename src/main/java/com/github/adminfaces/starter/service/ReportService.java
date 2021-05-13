/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.infra.model.Filter;
import com.github.adminfaces.starter.infra.model.SortOrder;
import com.github.adminfaces.starter.model.Car;
import com.github.adminfaces.starter.model.Client;
import com.github.adminfaces.starter.model.Report;
import com.github.adminfaces.starter.repository.ReportRepository;
import com.github.adminfaces.starter.model.Report;
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
 *         Report Business logic
 */
@Component
public class ReportService implements Serializable {

   
	@Autowired
	ReportRepository rep;

	List<Report> allCars;


    public void setAllCars(List<Report> allCars) {
		this.allCars = allCars;
	}



	@PostConstruct
    public void init() {
    	allCars =(List<Report>) rep.findAll();
    	
    }

	
	
	public List<Report> getAllCars() {
		allCars = (List<Report>) rep.findAll();
		return allCars;
	}

    public List<Report> listByModel(String model) {
        return allCars.stream()
                .filter(c -> c.getState().equalsIgnoreCase(model))
                .collect(Collectors.toList());

    }

    public List<Report> paginate(Filter<Report> filter) {
        List<Report> pagedCars = new ArrayList<>();
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

        List<Predicate<Report>> predicates = configFilter(filter);

        List<Report> pagedList = allCars.stream().filter(predicates
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

    private List<Predicate<Report>> configFilter(Filter<Report> filter) {
        List<Predicate<Report>> predicates = new ArrayList<>();
        if (filter.hasParam("id")) {
            Predicate<Report> idPredicate = (Report c) -> c.getId().equals(filter.getParam("id"));
            predicates.add(idPredicate);
        }

        
        if (has(filter.getEntity())) {
            Report filterEntity = filter.getEntity();
            if (has(filterEntity.getState())) {
                Predicate<Report> modelPredicate = (Report c) -> c.getState().toLowerCase().contains(filterEntity.getState().toLowerCase());
                predicates.add(modelPredicate);
            }

        

            if (has(filterEntity.getMessage())) {
                Predicate<Report> namePredicate = (Report c) -> c.getMessage().toLowerCase().contains(filterEntity.getMessage().toLowerCase());
                predicates.add(namePredicate);
            }
        }
        return predicates;
    }

    public List<String> getModels(String query) {
        return allCars.stream().filter(c -> c.getState()
                .toLowerCase().contains(query.toLowerCase()))
                .map(Report::getState)
                .collect(Collectors.toList());
    }

    public void insert(Report car) {
        car.setDateSe(new Date());
        car.setId(allCars.stream()
                .mapToLong(c -> c.getId())
                .max()
                .getAsLong()+1);
        allCars.add(car);
        rep.save(car);
    }




    public void remove(Report car) {
        allCars.remove(car);
    }

    public long count(Filter<Report> filter) {
        return allCars.stream()
                .filter(configFilter(filter).stream()
                        .reduce(Predicate::or).orElse(t -> true))
                .count();
    }

	public Report findById(Long id) {
		return allCars.stream().filter(c -> c.getId().equals(id)).findFirst()
				.orElseThrow(() -> new BusinessException("Car not found with id " + id));
	}

    public void update(Report car) {
    	car.setDateRe(new Date());
        allCars.remove(allCars.indexOf(car));
        allCars.add(car);
        rep.save(car);
    }
}
