package tn.esprit.pidev.service;
import java.util.List;

import java.util.Optional;

import tn.esprit.pidev.model.Dab;

public interface IDabService {
	List<Dab> retrieveAllDabs();
	Dab addDab(Dab dab);
	void deleteDab(Dab dab);
	Dab updateDab(Dab dab);
	Optional<Dab> retrieveDab(Long id_dab);
}
