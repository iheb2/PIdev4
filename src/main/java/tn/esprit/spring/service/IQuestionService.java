package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.model.Question;

public interface IQuestionService {
	List<Question> retrieveAllQuestions();	
	Question addQuestion(Question p);
	void deleteQuestion(Long id);
	Question updateQuestion(Question p);
	Question retrieveQuestion(String id_q);
	
	
}
