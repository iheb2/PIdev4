package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.model.Info;


public interface IInfoService {
	List<Info> retrieveAllInfos();	
	Info addInfo(Info i);
	void deleteInfo(Long id);
	Info updateInfo(Info i);
	Info retrieveInfo(String id);
	List<Info> SearchInfoByQuestion (String Topic);
//	public List<Info> orderByAscendingDate();
	public List<Info> orderByDescendingDate();
	public List<Info> Range(Date min, Date max);
	//List<Info> SearchInfoByState (StateI state);
	Info rateInfo(String Id, long rate);
	Info retrieveInfoclient(String Id);



}
