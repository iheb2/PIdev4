package tn.esprit.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ClientController {

	@GetMapping("/dashboard")
	public String gooHome(){
		return "dashboard";
		
	}
	
	@GetMapping("/clients")
	public String goclients(){
		return "clients";
		
	}
	
	
	
	
}
