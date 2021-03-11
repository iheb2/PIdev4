package tn.esprit.spring.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.model.Question;

import tn.esprit.spring.service.IQuestionService;

@Controller
public class QuestionController {

	@Autowired
	IQuestionService questionService;
	
	@GetMapping("/retrieve-all-Questions")
	@ResponseBody
	public List<Question> getQuestions() {
		List<Question> list = questionService.retrieveAllQuestions();
		return list;
		
	}

	@PostMapping("/add-Question")
	@ResponseBody
	public Question addQuestion(@RequestBody Question c) {
		Question Question = questionService.addQuestion(c);
		return Question;
	}
	
	@DeleteMapping("/remove-Question/{Question-id}")
	@ResponseBody
	public void removeQuestion (@PathVariable("Question-id") Long id_Question) {
		questionService.deleteQuestion(id_Question);
	}
	
	@PutMapping("/modify-Question")
	@ResponseBody
	public Question modifyQuestion(@RequestBody Question c) {
		return questionService.updateQuestion(c);
	}
	@GetMapping("/retrieve-Question/{Question-id}")
	@ResponseBody
	public Question retrieveQuestion(@PathVariable("Question-id") String  id_q) {
		return questionService.retrieveQuestion(id_q);
	}
}