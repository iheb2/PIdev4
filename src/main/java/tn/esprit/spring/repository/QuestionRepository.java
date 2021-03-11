package tn.esprit.spring.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.model.Question;



@Repository

public interface QuestionRepository  extends CrudRepository<Question,  Long> {

}
