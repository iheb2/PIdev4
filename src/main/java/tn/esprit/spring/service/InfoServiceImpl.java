package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.model.Info;
import tn.esprit.spring.repo.InfoRepository;



@Service
public class InfoServiceImpl implements IInfoService {
	@Autowired
	InfoRepository infoRepository ;

////////////////agent //////////////////

	@Override
	public Info addInfo(Info i) {
		return infoRepository.save(i);
	}

	@Override
	public void deleteInfo(Long Id) {
		infoRepository.deleteById((long) Id);
		
	}

	@Override
	public Info updateInfo(Info i) {
		return infoRepository.save(i);
	}
	@Override
	public Info retrieveInfo(String Id) {
		return  infoRepository.findById(Long.parseLong(Id)).orElse(null);
	}
	////////////client//////////
	@Override
	public Info retrieveInfoclient(String Id) {
		Info i = infoRepository.findById(Long.parseLong(Id)).orElse(null);
		Long l =i.getNb_consultation()+1;
		i.setNb_consultation(l);
		infoRepository.save(i);
		return i;
	}

	@Override
	public Info rateInfo(String Id,long rate) {
		Info i = infoRepository.findById(Long.parseLong(Id)).orElse(null);
		Long l = i.getNb_rate()+1;
		long d= ((i.getRate()*l)+rate)/(l+1);
		i.setNb_rate(l+1);
		i.setRate(d);
		infoRepository.save(i);
		return i;
	}
	///////////////////Agent&client///////////
	@Override
	public List<Info> retrieveAllInfos() {
		return (List<Info>) infoRepository.findAll();

	}
	@Override
	public List<Info> SearchInfoByQuestion(String Question) {
		return infoRepository.SearchInfoByQuestion(Question);}
	

	/*@Override
	public List<Info> SearchInfoByState(StateI state) {
		// TODO Auto-generated method stub
		return infoRepository.SearchInfoByState(state);}
*/
/*	@Override
	public List<Info> orderByAscendingDate() {
		return infoRepository.orderByAscendingDate();
	}*/

	@Override
	public List<Info> orderByDescendingDate() {
		return infoRepository.orderByDescendingDate();
	}

   @Override
	public List<Info> Range(Date min, Date max) {
		return infoRepository.Range(min, max);
	}

}
