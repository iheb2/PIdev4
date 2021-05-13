package com.github.adminfaces.starter.service;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.Duration;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.adminfaces.starter.model.Client;
import com.github.adminfaces.starter.model.Report;
import com.github.adminfaces.starter.repository.ClientRepository;
import com.github.adminfaces.starter.repository.ReportRepository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ReportServiceImpl implements IReportService{
	@Autowired
	ReportRepository reportRepository;

	@Autowired
	ClientRepository userR;

	@Override
	public List<Report> retrieveAllReports() {
		return (List<Report>) reportRepository.findAll();}

	@Override
	public Report addReport(Report i) {
		return reportRepository.save(i);
	}

	@Override
	public void deleteReport(Long id) {
		reportRepository.deleteById((long) id);
		
	}

	@Override
	public Report updateReport(Report i) {
	 
		return reportRepository.save(i);
	}

	@Override
	public Report retrieveReport(String id) {
		return reportRepository.findById(Long.parseLong(id)).orElse(null);
	}



	@Override
	public List<Report> orderByAscendingDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> orderByDescendingDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> Range1(Date min, Date max) {
		return reportRepository.Range1(min, max);
	}

	@Override
	public List<Report> SearchReportByState(String state) {
		// TODO Auto-generated method stub
		return reportRepository.SearchReportByState(state);
	}

	@Override
	public List<Report> SearchReportBySubject(String subject) {
		// TODO Auto-generated method stub
		return null;
	}


/////////////////////////Stat///////////////////////////////////////////////////////////////
	@Override
	public float   count_report_per_user(Date min,Date max){//nb report total:nb client
	
	long n = userR.count();
	System.out.print(n);
	//List<Report> m = Range1(min, max);
	List<Report> m=(List<Report>) reportRepository.findAll();
	System.out.print(m.size());
	System.out.print(m);
	System.out.print((float) m.size()/n);
	return ((float) m.size()/n);
	
	}
	@Override
public long max_report(Date min,Date max){                           //nb max de rapport par un seul client
	List<Client>list =(List<Client>) userR.findAll();
	List<Long> list1= new ArrayList<Long>();
	
	for(Client i: list){
		List<Report> list2= new ArrayList<Report>();
		for (Report r :i.getReport())
		{if (r.getDateRe().before(max)&&r.getDateRe().after(min)){list2.add(r);}
		list1.add((long) list2.size());
	}}
System.out.print(Collections.max(list1));
	return Collections.max(list1);}
	
	
	@Override
public long min_report(Date min,Date max){
	List<Client>list =(List<Client>) userR.findAll();
	List<Long> list1= new ArrayList<Long>();
	
	for(Client i: list){
		List<Report> list2= new ArrayList<Report>();
		for (Report r :i.getReport())
		{if (r.getDateRe().before(max)&&r.getDateRe().after(min)){list2.add(r);}
		list1.add((long) list2.size());
	}}
	System.out.print(Collections.min(list1));
	return Collections.min(list1);}
	@Override
public long max_respond(Date min,Date max){             //nb max de jours pour repondre
	List<Report> list = (List<Report>) Range1(min, max);

	List<Long> list1= new ArrayList<Long>();
	for(Report i: list){
		list1.add(i.getT());	
	}
	 Long m = Collections.max(list1);
	 System.out.print(m);
	return m;}
	@Override
public Long min_respond(Date min,Date max){
	List<Report> list = (List<Report>) Range1(min, max);

	List<Long> list1= new ArrayList<Long>();
	for(Report i: list){
		list1.add(i.getT());	
	}
	 Long m = Collections.min(list1);
	 System.out.print(m);
	return m;}
	@Override
public float moyenne_respond(Date min,Date max)
{ List<Report> list = (List<Report>) Range1(min, max);
long m = 0;
for (Report i: list){long a = i.getT();
	m=m+a;
	System.out.println(m);}

	return ((float)m/list.size());
	}

	@Override
	public String Stat(Date min, Date max) {
		// TODO Auto-generated method stub
		return null;
	}

 
}