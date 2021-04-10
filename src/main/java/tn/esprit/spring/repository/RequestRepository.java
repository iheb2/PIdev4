package tn.esprit.pidev.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.pidev.model.Request;

@Repository
public interface RequestRepository extends CrudRepository <Request,Long> {

}
