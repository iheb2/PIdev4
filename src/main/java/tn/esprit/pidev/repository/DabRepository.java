package tn.esprit.pidev.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import tn.esprit.pidev.model.Dab;

@Repository
public interface DabRepository  extends  CrudRepository <Dab,Long> {

}
