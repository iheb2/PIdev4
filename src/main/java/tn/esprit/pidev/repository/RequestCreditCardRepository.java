package tn.esprit.pidev.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import tn.esprit.pidev.model.Request;
import tn.esprit.pidev.model.RequestCreditCard;

@Repository
public interface RequestCreditCardRepository extends CrudRepository <RequestCreditCard,Long>{

}
