package tn.esprit.pidev.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pidev.model.Dab;
import tn.esprit.pidev.repository.DabRepository;

@Service
public class DabServiceImpl implements IDabService{
	@Autowired
   DabRepository dabrepo; 
	@Override
	public List<Dab> retrieveAllDabs() {
		List<Dab> dablist =(List<Dab>)dabrepo.findAll();
		return dablist;
	}

	@Override
	public Dab addDab(Dab dab) {
		
		return dabrepo.save(dab);
	}

	@Override
	public void deleteDab(Dab dab) {
		dabrepo.deleteById(dab.getId_dab());
		
	}

	@Override
	public Dab updateDab(Dab dab) {
		return dabrepo.save(dab);
	}

	@Override
	public Optional<Dab> retrieveDab(Long id_dab) {
		return dabrepo.findById(id_dab);
	}

}
