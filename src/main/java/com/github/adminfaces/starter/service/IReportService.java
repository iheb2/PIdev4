package com.github.adminfaces.starter.service;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import com.github.adminfaces.starter.model.Report;



public interface IReportService {
	List<Report> retrieveAllReports();	
	Report addReport(Report i);
	void deleteReport(Long id);
	Report updateReport(Report i);
	Report retrieveReport(String id);
	List<Report> SearchReportBySubject (String subject);
	public List<Report> orderByAscendingDate();
	public List<Report> orderByDescendingDate();
	public List<Report> Range1(Date min, Date max);
	float moyenne_respond(Date min, Date max);
	public long max_respond(Date min,Date max);
	public Long min_respond(Date min,Date max);
	public long max_report(Date min,Date max);
	public float   count_report_per_user(Date min,Date max);
	public long min_report(Date min,Date max);
	String Stat(Date min, Date max) ;
	List<Report> SearchReportByState(String state);

}
