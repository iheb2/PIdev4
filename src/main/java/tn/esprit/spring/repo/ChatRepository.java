package tn.esprit.spring.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.model.Chat;



public interface ChatRepository extends  CrudRepository<Chat,  Long> {

	
}
