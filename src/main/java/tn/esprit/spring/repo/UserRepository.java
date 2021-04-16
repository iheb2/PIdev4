package tn.esprit.spring.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.model.User;




public interface UserRepository extends  CrudRepository<User,  Long> {

	
}
