package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.model.AmalWacelGhada;
import tn.esprit.spring.repo.a;




@Service
public class ampl implements Ia {
	@Autowired
	a ar ;
	@Override
		public List<AmalWacelGhada> retrievea() {
		return (List<AmalWacelGhada>) ar.findAll();

	}
	@Override
	public AmalWacelGhada findId(String Id){
		return ar.findById(Long.parseLong(Id)).orElse(null);
	}
}
