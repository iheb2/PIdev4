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

import tn.esprit.spring.model.Info;
import tn.esprit.spring.service.IInfoService;


@Controller
public class InfoController {
	@Autowired
	IInfoService infoService;
	@GetMapping("/retrieve-all-Infos")
	@ResponseBody
	public List<Info> getInfos() {
		List<Info> list = infoService.retrieveAllInfos();
		return list;
		
	}

	@PostMapping("/add-Info")
	@ResponseBody
	public Info addInfo(@RequestBody Info c) {
		Info Info = infoService.addInfo(c);
		return Info;
	}
	
	@DeleteMapping("/remove-Info/{Info-id}")
	@ResponseBody
	public void removeInfo (@PathVariable("Info-id") Long id_Info) {
		infoService.deleteInfo(id_Info);
	}
	
	@PutMapping("/modify-Info")
	@ResponseBody
	public Info modifyInfo(@RequestBody Info c) {
		return infoService.updateInfo(c);
	}
	@PutMapping("/rate-Info/{Info-id}")
	@ResponseBody
	public Info rateInfo(@RequestBody long c,@PathVariable("Info-id") String  id) {
		return infoService.rateInfo(id, c);
	}
	@GetMapping("/retrieve-Info/{Info-id}")
	@ResponseBody
	public Info retrieveInfo(@PathVariable("Info-id") String  id_i) {
		return infoService.retrieveInfo(id_i);
	}
	@GetMapping("/retrieve-Info-client/{Info-id}")
	@ResponseBody
	public Info retrieveInfoclient(@PathVariable("Info-id") String  id_i) {
		return infoService.retrieveInfoclient(id_i);
	}
	@GetMapping("/search-Info-Topic/{Info-topic}")
	@ResponseBody
	public List<Info>  SearchInfoByTopic(@PathVariable("Info-topic") String Topic) {
	return infoService.SearchInfoByQuestion(Topic);}

	/*@GetMapping("/search-Info-State/{Info-state}")
	@ResponseBody
	public List<Info>  SearchInfoByState(@PathVariable("Info-state") StateI state) {
	return infoService.SearchInfoByState(state);}
	*/
	/*@GetMapping("/retrieve-all-Infos-asc")
	@ResponseBody
	public List<Info> orderByAscendingDate() {
		List<Info> list = infoService.orderByAscendingDate();
		return list;
	}*/
	@GetMapping("/retrieve-all-Infos-desc")
	@ResponseBody
	public List<Info> orderByDescendingDate() {
		List<Info> list = infoService.orderByDescendingDate();
		return list;
	}
	@GetMapping("/range/{min}/{max}")
	@ResponseBody
	public List<Info>  RangeProducts(@PathVariable("min") String min, @PathVariable("max") String max) {
	Date date1 = null;
	try {
		date1 = new SimpleDateFormat("yyyy-MM-dd").parse(min);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
	Date date2 = null;
	try {
		date2 = new SimpleDateFormat("yyyy-MM-dd").parse(max);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  

		return infoService.Range(date1, date2);
	}

}
