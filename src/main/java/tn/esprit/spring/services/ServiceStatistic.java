package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.models.Client;
import tn.esprit.spring.models.Statistic;

public interface ServiceStatistic {

	public Statistic CreateStatistic(List<Client> products);

}
