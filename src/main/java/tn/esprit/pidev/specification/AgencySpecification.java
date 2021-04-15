package tn.esprit.pidev.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import tn.esprit.pidev.model.Agency;
import tn.esprit.pidev.model.Agencysearchcriterea;

public class AgencySpecification implements Specification <Agency>{
 private Agencysearchcriterea criteria;

@Override
public Predicate toPredicate(Root<Agency> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

     return null;
 }
}

   
	

