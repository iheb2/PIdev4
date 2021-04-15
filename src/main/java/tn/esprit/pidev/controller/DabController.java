package tn.esprit.pidev.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pidev.model.Dab;

import tn.esprit.pidev.service.IDabService;

@RestController
public class DabController {
	@Autowired
IDabService dabservice;
 	// http://localhost:8080/SpringMVC/servlet/retrieve-all-Dabs
	 @GetMapping("/retrieve-all-Dabs")
	 @ResponseBody
	 public List<Dab> getDabs() {
	 List<Dab> dab = dabservice.retrieveAllDabs();
	 return dab;
	 }
	//*http://localhost:8080/SpringMVC/servlet/retrieve-Dab/{Dab-id}
	  @GetMapping("/retrieve-Dab/{Dab-id}")
	  @ResponseBody
	  public Optional<Dab> retrieveDab(@PathVariable("Dab-id") Long id_dab) {
	  return dabservice.retrieveDab(id_dab);
	  }
	  // http://localhost:8080/SpringMVC/servlet/add-Dab
	  @PostMapping("/add-Dab")
	  @ResponseBody
	  public Dab addDab(@RequestBody Dab dab) {
	  return dabservice.addDab(dab);
	  
	  }
	// http://localhost:8080/SpringMVC/servlet/remove-Dab/{Dab-id}
	   @DeleteMapping("/remove-Dab/{Dab-id}")
	   @ResponseBody
	   public void removeDab(@PathVariable("Dab-id") Dab id_dab) {
		   dabservice.deleteDab(id_dab);
	   }
	   // http://localhost:8080/SpringMVC/servlet/modify-Dab
	    @PutMapping("/modify-Dab")
	    @ResponseBody
	    public Dab modifyDab(@RequestBody Dab dab){
	    return dabservice.updateDab(dab);
	   }
	 
}
