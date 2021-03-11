package tn.esprit.spring.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.model.Answer;

import tn.esprit.spring.service.IAnswerService;

@Controller
public class AnswerController {

	@Autowired
	IAnswerService answerService;
	
	@GetMapping("/retrieve-all-Answers")
	@ResponseBody
	public List<Answer> getAnswers() {
		List<Answer> list = answerService.retrieveAllAnswers();
		return list;
		
	}

	@PostMapping("/add-Answer")
	@ResponseBody
	public Answer addAnswer(@RequestBody Answer c) {
		Answer Answer = answerService.addAnswer(c);
		return Answer;
	}
	
	@DeleteMapping("/remove-Answer/{Answer-id}")
	@ResponseBody
	public void removeAnswer (@PathVariable("Answer-id") Long id_Answer) {
		answerService.deleteAnswer(id_Answer);;
	}
	
	@PutMapping("/modify-Answer")
	@ResponseBody
	public Answer modifyAnswer(@RequestBody Answer c) {
		return answerService.updateAnswer(c);
	}
	@GetMapping("/retrieve-Answer/{Answer-id}")
	@ResponseBody
	public Answer retrieveAnswer(@PathVariable("Answer-id") String  id_a) {
		return answerService.retrieveAnswer(id_a);}
}
