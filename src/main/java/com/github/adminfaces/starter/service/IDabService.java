package com.github.adminfaces.starter.service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.github.adminfaces.starter.model.Dab;

public interface IDabService {
	List<Dab> retrieveAllDabs();
	Dab addDab(Dab dab);
	void deleteDab(Dab dab);
	Dab updateDab(Dab dab);
	Optional<Dab> retrieveDab(Long id_dab);
	Map<Double, Double> StatisticMaxAmountPerWeek(int lim_week);
	List<Dab> listByLimWeek(int lim_week);
}
