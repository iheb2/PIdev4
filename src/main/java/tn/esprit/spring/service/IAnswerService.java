package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.model.Answer;

public interface IAnswerService {

	List<Answer> retrieveAllAnswers();
	Answer addAnswer(Answer p);
	void deleteAnswer(Long id);
	Answer updateAnswer(Answer p);
	Answer retrieveAnswer(String id_a);
	
	
}
