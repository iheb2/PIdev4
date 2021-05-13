package com.github.adminfaces.starter.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.adminfaces.starter.model.Dab;
import com.github.adminfaces.starter.model.Request;
import com.github.adminfaces.starter.repository.DabRepository;

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
	
	@Override
	public Map<Double, Double> StatisticMaxAmountPerWeek(int lim_week) {
		Map<Double, Double> MA = new HashMap<Double, Double>();
		double sum1 = 0;
		double sum2 = 0;
		double sum3 = 0;
		List<Dab> L = listByLimWeek(lim_week);
		for (int i = 0; i < L.size(); i++) {
			String month = String.valueOf(L.get(i).getLim_week());
			if (month.equals("01"))

			{
				sum1 += L.get(i).getMax_day();
				MA.put((double) 1, sum1);
			}

			else if (month.equals("02")) {
				sum2 += L.get(i).getMax_day();
				MA.put((double) 2, sum2);
			}

			else if (month.equals("03")) {
				sum3 += L.get(i).getMax_day();
				MA.put((double) 3, sum3);
			}

		}

		return MA;
	}
	
	@SuppressWarnings({ "null", "deprecation" })
	@Override
	public List<Dab> listByLimWeek(int lim_week) {

		List<Dab> L = (List<Dab>) dabrepo.findAll();
		List<Dab> ListR = new ArrayList<Dab>();
		for (int i = 0; i < L.size(); i++) {
			System.out.print("LIM " + L.get(i).getLim_week() + "\n");

			if (L.get(i).getLim_week() == lim_week) {

				ListR.add(L.get(i));

				System.out.print("Add lim  "	+ L.get(i).getLim_week());

			}
		}
		return ListR;
	}	

}
