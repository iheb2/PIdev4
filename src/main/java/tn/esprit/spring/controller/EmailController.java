package tn.esprit.spring.controller;


import java.time.Duration;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.model.EmailRequest;
import tn.esprit.spring.model.Report;
import tn.esprit.spring.model.StateR;
import tn.esprit.spring.model.sessionnn;
import tn.esprit.spring.service.EmailService;
import tn.esprit.spring.service.IReportService;
import tn.esprit.spring.service.IUserService;





@RestController
public class EmailController {
	@Autowired
	IReportService reportService;
    @Autowired
    private EmailService emailService;
	@Autowired
	IUserService user;
    sessionnn s=new sessionnn();
    //this api send simple email
    @PostMapping("/sendingemail/{MDP}")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request,@PathVariable("MDP") String mdp )
    {
        System.out.println(request);


        boolean result = this.emailService.sendEmail(request.getSubject(), request.getMessage(), "sirine.kraie@esprit.tn",mdp);

        if(result){
        
        	Report report=new Report();
        	report.setClient(user.getUser(Long.toString(s.getId())));
        	report.setFrom(s.getEmail());
        	report.setTo("sirine.kraie@esprit.tn");
        	report.setDateR(new Date());
        	report.setMessage(request.getMessage());
        	report.setSubject(request.getSubject());
        	report.setState(StateR.pas_encore_traiter);
        	reportService.addReport(report);
        	//report.setClient();
        	return  ResponseEntity.ok("Email Properly Sent Successfully... ");

        }else{

            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("email sending fail");
        }
    }

    //this api send email with file
 @PostMapping("/sendemailattachement/{MDP}")
    public ResponseEntity<?> sendEmailWithAttachment(@RequestBody EmailRequest request,String path,@PathVariable("MDP") String mdp)
    {
        System.out.println(request);

        
		boolean result = this.emailService.sendWithAttachmet(request.getSubject(), request.getMessage(), "sirine.kraie@esprit.tn",path,mdp );

        if(result){
           	Report report=new Report();
        	report.setFrom(s.getEmail());
        	report.setTo("sirine.kraie@esprit.tn");
        	report.setDateR(new Date());
        	report.setMessage(request.getMessage());
        	report.setSubject(request.getSubject());
        	report.setState(StateR.pas_encore_traiter);
        	report.setPath(path);
        	reportService.addReport(report);
            return  ResponseEntity.ok("Sent Email With Attchment Successfully... ");

        }else{

            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("attachment email sending fail");
        }

    }
   @PutMapping("/respondgemail/{id}")
   public ResponseEntity<?> respondEmail(@RequestBody EmailRequest request,@PathVariable("id") String id)
   {
   	System.out.println(request);
    Report r = reportService.retrieveReport(id);
    boolean result = this.emailService.respondEmail(request.getSubject(),request.getMessage(),r.getFrom());

       if(result){
    	
    	    r.setRespond(request.getMessage());
    	   	Date dateRe= new Date();
    	   	Date z=r.getDateR();
    	   	r.setDateRe(dateRe);

    	   	
    	   	
    	   	long diffInMillies = Math.abs(z.getTime() - dateRe.getTime());
		    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
		    r.setT(diff);
    		 r.setState(StateR.traiter);
    	     Report Report = reportService.updateReport(r);  
    	 
			
           return  ResponseEntity.ok("Email answered Successfully... ");

       }else{

           return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("email sending fail");
       }
   }
 /*  
   @PutMapping("/respondmailattachement/{id}")
   public ResponseEntity<?> respondEmailWithAttachment(@RequestBody EmailRequest request,String path,@PathVariable("id") String id)
   {   Report r = reportService.retrieveReport(id);
       System.out.println(request);
		boolean result = this.emailService.respondWithAttachment(request.getSubject(),request.getMessage(),path,r.getFrom());

        if(result){
     	r.setRespond(request.getMessage());
  	   	Date dateRe= new Date();
  	   	r.setDateRe(dateRe);
  	    Date v = r.getDateR();
  	    long diffInMillies = Math.abs(dateRe.getTime() -v.getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

  		 r.setT(diff);
  		 r.setState(StateR.traiter);
  	     Report Report = reportService.updateReport(r);
   
           return  ResponseEntity.ok("respond Email With Attchment Successfully... ");

       }else{

           return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("attachment email sending fail");
       }

   }*/
}
