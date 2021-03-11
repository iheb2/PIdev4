package tn.esprit.spring.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.model.Client;


@Repository
public interface ClientRepository extends CrudRepository<Client, Long>{

	
}