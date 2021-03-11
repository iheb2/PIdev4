package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.esprit.spring.model.Answer;

import tn.esprit.spring.repository.AnswerRepository;;


@Service
public class AnswerServiceImpl implements IAnswerService{

	@Autowired
	AnswerRepository answerRepository ;

	@Override
	public List<Answer> retrieveAllAnswers() {
		return (List<Answer>) answerRepository.findAll();
	}

	
	@Override
	public Answer addAnswer(Answer p) {
		return answerRepository.save(p);
	}

	@Override
	public void deleteAnswer(Long id_prod) {
		// TODO Auto-generated method stub
		answerRepository.deleteById((long) id_prod);
	}

	@Override
	public Answer updateAnswer(Answer p) {
		return answerRepository.save(p);
	}
	@Override
	public Answer retrieveAnswer(String id_a) {
		return answerRepository.findById(Long.parseLong(id_a)).orElse(null);
	}
	
}
