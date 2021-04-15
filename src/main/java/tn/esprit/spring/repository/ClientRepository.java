package tn.esprit.spring.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.models.Client;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long>{


	@Query("SELECT c FROM Client c WHERE c.cin= :cin")
	List<Client> SearchClientByName(@Param("cin") String cin);
	
	@Query("SELECT c FROM Client c WHERE c.score= :score")
	List<Client> SearchClientByScore(@Param("score") Double score);
}
