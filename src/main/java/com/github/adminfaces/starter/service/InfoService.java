/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.infra.model.Filter;
import com.github.adminfaces.starter.infra.model.SortOrder;
import com.github.adminfaces.starter.model.Information;
import com.github.adminfaces.starter.repository.InfoRepository;
import com.github.adminfaces.template.exception.BusinessException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.github.adminfaces.template.util.Assert.has;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InfoService implements Serializable {

	@Autowired
	InfoRepository rep;

	List<Information> allCars;


    @PostConstruct
    public void init() {
    	allCars =(List<Information>) rep.findAll();

    }

	
	
	public List<Information> getAllCars() {
		allCars = (List<Information>) rep.findAll();
		return allCars;
	}

	public void setAllCars(List<Information> allCars) {
		this.allCars = allCars;
	}

	public List<Information> listByModel(String model) {
		return allCars.stream().filter(c -> c.getQuestion().equalsIgnoreCase(model)).collect(Collectors.toList());

	}

	public List<Information> paginate(Filter<Information> filter) {
		List<Information> pagedCars = new ArrayList<>();
		if (has(filter.getSortOrder()) && !SortOrder.UNSORTED.equals(filter.getSortOrder())) {
			pagedCars = allCars.stream().sorted((c1, c2) -> {
				if (filter.getSortOrder().isAscending()) {
					return c1.getId().compareTo(c2.getId());
				} else {
					return c2.getId().compareTo(c1.getId());
				}
			}).collect(Collectors.toList());
		}

		int page = filter.getFirst() + filter.getPageSize();
		if (filter.getParams().isEmpty()) {
			pagedCars = pagedCars.subList(filter.getFirst(), page > allCars.size() ? allCars.size() : page);
			return pagedCars;
		}

		List<Predicate<Information>> predicates = configFilter(filter);

		List<Information> pagedList = allCars.stream().filter(predicates.stream().reduce(Predicate::or).orElse(t -> true))
				.collect(Collectors.toList());

		if (page < pagedList.size()) {
			pagedList = pagedList.subList(filter.getFirst(), page);
		}

		if (has(filter.getSortField())) {
			pagedList = pagedList.stream().sorted((c1, c2) -> {
				boolean asc = SortOrder.ASCENDING.equals(filter.getSortOrder());
				if (asc) {
					return c1.getId().compareTo(c2.getId());
				} else {
					return c2.getId().compareTo(c1.getId());
				}
			}).collect(Collectors.toList());
		}
		return pagedList;
	}

	private List<Predicate<Information>> configFilter(Filter<Information> filter) {
		List<Predicate<Information>> predicates = new ArrayList<>();
		if (filter.hasParam("id")) {
			Predicate<Information> idPredicate = (Information c) -> c.getId().equals(filter.getParam("id"));
			predicates.add(idPredicate);
		}

		if (filter.hasParam("minPrice") && filter.hasParam("maxPrice")) {
			Predicate<Information> minMaxPricePredicate = (
					Information c) -> c.getRate() >= Double.valueOf((String) filter.getParam("minPrice"))
							&& c.getRate() <= Double.valueOf((String) filter.getParam("maxPrice"));
			predicates.add(minMaxPricePredicate);
		} else if (filter.hasParam("minPrice")) {
			Predicate<Information> minPricePredicate = (
					Information c) -> c.getRate() >= Double.valueOf((String) filter.getParam("minPrice"));
			predicates.add(minPricePredicate);
		} else if (filter.hasParam("maxPrice")) {
			Predicate<Information> maxPricePredicate = (
					Information c) -> c.getRate() <= Double.valueOf((String) filter.getParam("maxPrice"));
			predicates.add(maxPricePredicate);
		}

		if (has(filter.getEntity())) {
			Information filterEntity = filter.getEntity();
			if (has(filterEntity.getQuestion())) {
				Predicate<Information> modelPredicate = (Information c) -> c.getQuestion().toLowerCase()
						.contains(filterEntity.getQuestion().toLowerCase());
				predicates.add(modelPredicate);
			}

			if (has(filterEntity.getRate())) {
				Predicate<Information> pricePredicate = (Information c) -> c.getRate().equals(filterEntity.getRate());
				predicates.add(pricePredicate);
			}

			if (has(filterEntity.getAnswer())) {
				Predicate<Information> namePredicate = (Information c) -> c.getAnswer().toLowerCase()
						.contains(filterEntity.getAnswer().toLowerCase());
				predicates.add(namePredicate);
			}
		}
		return predicates;
	}

	public List<String> getModels(String query) {
		return allCars.stream().filter(c -> c.getQuestion().toLowerCase().contains(query.toLowerCase()))
				.map(Information::getQuestion).collect(Collectors.toList());
	}

	public void insert(Information car) {
		rep.save(car);
		car.setId(allCars.stream().mapToLong(c -> c.getId()).max().getAsLong() + 1);
		car.setRate(0);
		allCars.add(car);
	}

	public void validate(Information car) {
		BusinessException be = new BusinessException();
		if (!car.hasQuestion()) {
			be.addException(new BusinessException("Information Question cannot be empty"));
		}
		if (!car.hasAnswer()) {
			be.addException(new BusinessException("Information Answer cannot be empty"));
		}

		if (!has(car.getRate())) {
			be.addException(new BusinessException("Information Rate cannot be empty"));
		}

		if (allCars.stream().filter(c -> c.getAnswer().equalsIgnoreCase(car.getAnswer()) && c.getId() != c.getId())
				.count() > 0) {
			be.addException(new BusinessException("Information username must be unique"));
		}
		if (has(be.getExceptionList())) {
			throw be;
		}
	}

	public void remove(Information car) {
	 allCars.remove(car);
		rep.delete(car);
	}

	public long count(Filter<Information> filter) {
		return allCars.stream().filter(configFilter(filter).stream().reduce(Predicate::or).orElse(t -> true)).count();
	}

	public Information findById(Long id) {
		
		return allCars.stream().filter(c -> c.getId().equals(id)).findFirst()
				.orElseThrow(() -> new BusinessException("Car not found with id " + id));
	}

	public void update(Information car) {
	
		allCars.remove(allCars.indexOf(car));
		rep.save(car);
		allCars.add(car);
	}
	public Information rateInfo(Information i) {
		Long l = i.getNb_rate()+1;
		Integer d= (int) (((i.getRate()*l))/(l+1));
		i.setNb_rate(l+1);
		i.setRate(d);
		rep.save(i);
		validate(i);
		allCars.remove(allCars.indexOf(i));
		rep.save(i);
		allCars.add(i);
		return i;
	}
}
