package tn.esprit.spring.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.esprit.spring.model.AmalWacelGhada;
import tn.esprit.spring.service.ampl;




@Controller
public class acon {
	@Autowired
	ampl infoService;
	@GetMapping("/retrieve-all-awgs")
	@ResponseBody
	public List<AmalWacelGhada> getInfos() {
		List<AmalWacelGhada> list = infoService.retrievea();
		return list;
		
	}

	

}
