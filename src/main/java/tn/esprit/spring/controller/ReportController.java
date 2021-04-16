package tn.esprit.spring.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.text.DocumentException;
import com.sun.el.parser.ParseException;

import tn.esprit.spring.model.Report;
import tn.esprit.spring.model.StateR;
import tn.esprit.spring.model.Transaction;
import tn.esprit.spring.service.IReportService;
import tn.esprit.spring.service.UserPDFExporter;


@Controller
public class ReportController {
	@Autowired
	IReportService reportService;


	@GetMapping("/retrieve-all-Reports")
	@ResponseBody
	public List<Report> getReports() 
	{
		List<Report>  list = reportService.retrieveAllReports();
		return list;
		
	}
	@PostMapping("/add-Report")
	@ResponseBody
	public Report addReport(@RequestBody Report c) {
		Date d= new Date();
		c.setDateR(d);
		c.setState(StateR.pas_encore_traiter);
		Report Report = reportService.addReport(c);
		return Report;
	}

	@DeleteMapping("/remove-Report/{Report-id}")
	@ResponseBody
	public void removeReport (@PathVariable("Report-id") Long id_Report) 
	{
		reportService.deleteReport(id_Report);
	}


	@GetMapping("/retrieve-Report/{Report-id}")
	@ResponseBody
	public Report retrieveReport(@PathVariable("Report-id") String  id_t)
	{
		return reportService.retrieveReport(id_t);

	}
	////////////////////////
	@GetMapping("/test/{min}/{max}")
	@ResponseBody
	   public float test(@PathVariable("min") String min, @PathVariable("max") String max) throws java.text.ParseException{
		Date date1 = null;
		date1 = new SimpleDateFormat("yyyy-MM-dd").parse(min);  
		Date date2 = null;	
		date2 = new SimpleDateFormat("yyyy-MM-dd").parse(max);
		return reportService.count_report_per_user(date1,date2);}
	
		@GetMapping("/test1/{min}/{max}")
		@ResponseBody
		   public List<Report> test1(@PathVariable("min") String min, @PathVariable("max") String max) throws java.text.ParseException{
			Date date1 = null;
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(min);  
			Date date2 = null;
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(max); 
			return reportService.Range1(date1,date2);}
		@GetMapping("/test2/{min}/{max}")
		@ResponseBody
			   public float test2(@PathVariable("min") String min, @PathVariable("max") String max) throws java.text.ParseException{
				Date date1 = null;
				date1 = new SimpleDateFormat("yyyy-MM-dd").parse(min);  
				Date date2 = null;	
				date2 = new SimpleDateFormat("yyyy-MM-dd").parse(max);
				return reportService.min_respond(date1, date2);}
		
		@GetMapping("/test3/{min}/{max}")
		@ResponseBody
			   public float test3(@PathVariable("min") String min, @PathVariable("max") String max) throws java.text.ParseException{
				Date date1 = null;
				date1 = new SimpleDateFormat("yyyy-MM-dd").parse(min);  
				Date date2 = null;	
				date2 = new SimpleDateFormat("yyyy-MM-dd").parse(max);
				return reportService.max_respond(date1, date2);}
		@GetMapping("/test4/{min}/{max}")
		@ResponseBody
			   public float test4(@PathVariable("min") String min, @PathVariable("max") String max) throws java.text.ParseException{
				Date date1 = null;
				date1 = new SimpleDateFormat("yyyy-MM-dd").parse(min);  
				Date date2 = null;	
				date2 = new SimpleDateFormat("yyyy-MM-dd").parse(max);
				return reportService.max_report(date1, date2);}
		
		@GetMapping("/Stat/{min}/{max}")
		@ResponseBody
		   public String statreport(@PathVariable("min") String min, @PathVariable("max") String max) throws java.text.ParseException, IOException 
		{
			Date date1 = null;
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(min);  
			Date date2 = null;	
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(max);
			List<Report>list=reportService.Range1(date1,date2);
			int y = list.size();
			float a = reportService.count_report_per_user(date1, date2);
		long n = reportService.max_report(date1, date2);
		 long k = reportService.min_report(date1, date2);
		    long x = reportService.max_respond(date1, date2);
		    long l =reportService. min_respond(date1, date2);
		    float b = reportService.moyenne_respond(date1, date2);
		    String s="         rapport des reclamation effectuer entre les dates" +min+ " et " +max+"\n"+
		    		"\n le nb de reclamations effectuer durant cette periode est "+ String.valueOf(y)+
		    	"\n La moyenne de reclamation par client est:  "+String.valueOf(a)+"\n Le nombre maximal de reclamation enoyer par un client:"+String.valueOf(n)+
				"\n Le nombre mainimal de reclamation enoyer par un client:"+String.valueOf(k)+"\n La  plus longue periode prise par nos agent pour repondre a une reclamation est:"
				+String.valueOf(x)+"\n La  plus courte periode prise par nos agent pour repondre a une reclamation est:"+String.valueOf(l)
				+"\n La  periode moyenne prise par nos agent pour repondre a une reclamation est:"+String.valueOf(b);
	         return s;}
				
	
}