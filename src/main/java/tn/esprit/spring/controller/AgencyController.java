package tn.esprit.pidev.controller;

import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pidev.ExportPDF.ExportPdf;
import tn.esprit.pidev.model.Agency;
import tn.esprit.pidev.model.AgencyPage;
import tn.esprit.pidev.model.Agencysearchcriterea;
import tn.esprit.pidev.model.Review;
import tn.esprit.pidev.repository.AgencyRepository;
import tn.esprit.pidev.service.IAgencyService;

@RestController
public class AgencyController {
	@Autowired
	private static final Logger LOG = LoggerFactory.getLogger(AgencyController.class);
	 
	 @Autowired
     IAgencyService agencyservice;
 	// http://localhost:8080/SpringMVC/servlet/retrieve-all-Agencies
	 @GetMapping("/retrieve-all-Agencies")
	 @ResponseBody
	 public List<Agency> getAgencies() {
	 List<Agency> agency = agencyservice.retrieveAllAgencies();
	 return agency;
	 }
	//*http://localhost:8080/SpringMVC/servlet/retrieve-Agency/{Agency-id}
	  @GetMapping("/retrieve-Agency/{Agency-id}")
	  @ResponseBody
	  public Optional<Agency> retrieveAgency(@PathVariable("Agency-id") Long id_agency) {
	  return agencyservice.retrieveAgency(id_agency);
	  }
	  // http://localhost:8080/SpringMVC/servlet/add-Agency
	  @PostMapping("/add-Agency")
	  @ResponseBody
	  public Agency addAgency(@RequestBody Agency agency) {
	  return agencyservice.addAgency(agency);
	  
	  }
	//http://localhost:8080/SpringMVC/servlet/remove-Agency/{Agency-id}
	   @DeleteMapping("/remove-Agency/{Agency-id}")
	   @ResponseBody
	   public void removeAgency(@PathVariable("Agency-id") Agency id_agency) {
		   agencyservice.deleteAgency(id_agency);
	   }
	   // http://localhost:8080/SpringMVC/servlet/modify-Agency
	    @PutMapping("/modify-Agency")
	    @ResponseBody
	    public Agency modifyAgency(@RequestBody Agency agency){
	    return agencyservice.updateAgency(agency);
	   }
	    // http://localhost:8080/SpringMVC/servlet/review
	    @PostMapping("/review")
		@ResponseBody
		public List<Review>  addAgencyReview(@RequestBody Review reviews) {
			LOG.info("Add Agency Reviews...");
			agencyservice.addReview(reviews);
			
			return agencyservice.getAgencyInfo(reviews.getAgencyId());
		}
	 	// http://localhost:8080/SpringMVC/servlet/retrieve-all-reviews
		 @GetMapping("/retrieve-all-reviews")
		 @ResponseBody
		 public List<Review> getReviews() {
		 List<Review> Review = agencyservice.retrieveAllReviews();
		 return Review;
		 }
		 @GetMapping("/popular")
		 @ResponseBody
	    public List<Agency> getPopularAgencies() {
		LOG.info("Fetch Popular Agencies...");
		List<Agency> list = agencyservice.retrieveAllAgencies();
		LOG.debug(": " + list.size());
		/*.sorted((p1, p2) -> (p1.getRating()).compareTo(p2.getRating()))*/

		List<Agency> ratedAgency = list.parallelStream().filter(obj -> null != obj.getRating())
				.collect(Collectors.toList());
		LOG.info(" Popular Agencies Fetched...");  
		LOG.debug(": " + ratedAgency.size());
		return ratedAgency;
		  }	 
		// http://localhost:8080/SpringMVC/servlet/exportpdf
		 @GetMapping(value = "/exportpdf", produces = MediaType.APPLICATION_PDF_VALUE)
			public ResponseEntity<InputStreamResource> employeeReports(HttpServletResponse response) throws IOException {

				List<Agency> allAgencies = agencyservice.retrieveAllAgencies();

				ByteArrayInputStream bis = ExportPdf.AgenciesReport(allAgencies);

				HttpHeaders headers = new HttpHeaders();

				headers.add("Content-Disposition", "attachment;filename=agencies.pdf");

				return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
						.body(new InputStreamResource(bis));
			   }
		// http://localhost:8080/SpringMVC/servlet/search
		 @GetMapping("/search")
		    public ResponseEntity<Page<Agency>> getagencies(AgencyPage agencyPage,
		    		Agencysearchcriterea agencysearchcriterea){
		        return new ResponseEntity<>(agencyservice.getagencies(agencyPage, agencysearchcriterea),
		                HttpStatus.OK);
		    }
		 
}
