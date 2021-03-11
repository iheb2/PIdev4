package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.esprit.spring.model.Question;
import tn.esprit.spring.model.Transaction;
import tn.esprit.spring.repository.QuestionRepository;;


@Service
public class QuestionServiceImpl implements IQuestionService{

	@Autowired
	QuestionRepository questionRepository ;

	@Override
	public List<Question> retrieveAllQuestions() {
		return (List<Question>) questionRepository.findAll();
	}
	@Override
	public Question addQuestion(Question q) {
		return questionRepository.save(q);
	}

	@Override
	public void deleteQuestion(Long id_q) {
		questionRepository.deleteById((long) id_q);
	}

	@Override
	public Question updateQuestion(Question q) {
		return questionRepository.save(q);
	}
	@Override
	public Question retrieveQuestion(String id_q) {
		return questionRepository.findById(Long.parseLong(id_q)).orElse(null);
	}
	
}
