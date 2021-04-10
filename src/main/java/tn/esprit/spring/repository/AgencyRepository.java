package tn.esprit.pidev.repository;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import tn.esprit.pidev.model.Agency;

@Repository
public interface AgencyRepository extends  JpaSpecificationExecutor, PagingAndSortingRepository<Agency,Long>, CrudRepository <Agency,Long>{

}
