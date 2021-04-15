package tn.esprit.pidev.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import tn.esprit.pidev.model.Request;
import tn.esprit.pidev.model.RequestCredit;

@Repository
public interface RequestCreditRepository extends CrudRepository <RequestCredit,Long>{

}
