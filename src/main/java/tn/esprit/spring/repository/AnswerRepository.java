package tn.esprit.spring.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.model.Answer;



@Repository

public interface AnswerRepository  extends CrudRepository<Answer, Long> {

}
