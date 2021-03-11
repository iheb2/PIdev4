package tn.esprit.spring.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.model.Transaction;


@Repository

public interface TransactionRepository  extends CrudRepository<Transaction, Long> {

}
