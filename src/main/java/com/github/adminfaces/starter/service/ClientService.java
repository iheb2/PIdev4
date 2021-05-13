/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.starter.infra.model.Filter;
import com.github.adminfaces.starter.infra.model.SortOrder;
import com.github.adminfaces.starter.model.Client;
import com.github.adminfaces.starter.repository.ClientRepository;
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
public class ClientService implements Serializable {

	@Autowired
	ClientRepository rep;

	List<Client> allCars;


    @PostConstruct
    public void init() {
    	allCars =(List<Client>) rep.findAll();
    }

	
	
	public List<Client> getAllCars() {
		allCars = (List<Client>) rep.findAll();
		return allCars;
	}

	public void setAllCars(List<Client> allCars) {
		this.allCars = allCars;
	}

	public List<Client> listByModel(String model) {
		return allCars.stream().filter(c -> c.getGroupe().equalsIgnoreCase(model)).collect(Collectors.toList());

	}

	public List<Client> paginate(Filter<Client> filter) {
		List<Client> pagedCars = new ArrayList<>();
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

		List<Predicate<Client>> predicates = configFilter(filter);

		List<Client> pagedList = allCars.stream().filter(predicates.stream().reduce(Predicate::or).orElse(t -> true))
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

	private List<Predicate<Client>> configFilter(Filter<Client> filter) {
		List<Predicate<Client>> predicates = new ArrayList<>();
		if (filter.hasParam("id")) {
			Predicate<Client> idPredicate = (Client c) -> c.getId().equals(filter.getParam("id"));
			predicates.add(idPredicate);
		}

		if (filter.hasParam("minPrice") && filter.hasParam("maxPrice")) {
			Predicate<Client> minMaxPricePredicate = (
					Client c) -> c.getScore() >= Double.valueOf((String) filter.getParam("minPrice"))
							&& c.getScore() <= Double.valueOf((String) filter.getParam("maxPrice"));
			predicates.add(minMaxPricePredicate);
		} else if (filter.hasParam("minPrice")) {
			Predicate<Client> minPricePredicate = (
					Client c) -> c.getScore() >= Double.valueOf((String) filter.getParam("minPrice"));
			predicates.add(minPricePredicate);
		} else if (filter.hasParam("maxPrice")) {
			Predicate<Client> maxPricePredicate = (
					Client c) -> c.getScore() <= Double.valueOf((String) filter.getParam("maxPrice"));
			predicates.add(maxPricePredicate);
		}

		if (has(filter.getEntity())) {
			Client filterEntity = filter.getEntity();
			if (has(filterEntity.getGroupe())) {
				Predicate<Client> modelPredicate = (Client c) -> c.getGroupe().toLowerCase()
						.contains(filterEntity.getGroupe().toLowerCase());
				predicates.add(modelPredicate);
			}

			if (has(filterEntity.getScore())) {
				Predicate<Client> pricePredicate = (Client c) -> c.getScore().equals(filterEntity.getScore());
				predicates.add(pricePredicate);
			}

			if (has(filterEntity.getCin())) {
				Predicate<Client> namePredicate = (Client c) -> c.getCin().toLowerCase()
						.contains(filterEntity.getCin().toLowerCase());
				predicates.add(namePredicate);
			}
		}
		return predicates;
	}

	public List<String> getModels(String query) {
		return allCars.stream().filter(c -> c.getGroupe().toLowerCase().contains(query.toLowerCase()))
				.map(Client::getGroupe).collect(Collectors.toList());
	}

	public void insert(Client car) {
		validate(car);
		car.setId(allCars.stream().mapToLong(c -> c.getId()).max().getAsLong() + 1);
		allCars.add(car);
	}

	public void validate(Client car) {
		BusinessException be = new BusinessException();
		if (!car.hasGroupe()) {
			be.addException(new BusinessException("Client Groupe cannot be empty"));
		}
		if (!car.hasCin()) {
			be.addException(new BusinessException("Client Cin cannot be empty"));
		}

		if (!has(car.getScore())) {
			be.addException(new BusinessException("Client Score cannot be empty"));
		}

		if (allCars.stream().filter(c -> c.getCin().equalsIgnoreCase(car.getCin()) && c.getId() != c.getId())
				.count() > 0) {
			be.addException(new BusinessException("Client username must be unique"));
		}
		if (has(be.getExceptionList())) {
			throw be;
		}
	}

	public void remove(Client car) {
		rep.delete(car);
	}

	public long count(Filter<Client> filter) {
		return allCars.stream().filter(configFilter(filter).stream().reduce(Predicate::or).orElse(t -> true)).count();
	}

	public Client findById(Long id) {
		return allCars.stream().filter(c -> c.getId().equals(id)).findFirst()
				.orElseThrow(() -> new BusinessException("Car not found with id " + id));
	}

	public void update(Client car) {
		validate(car);
		allCars.remove(allCars.indexOf(car));
		allCars.add(car);
	}
}
