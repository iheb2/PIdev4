package tn.esprit.pidev.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.pidev.model.SavedRequests;
@Repository
public interface SavedRequestsRepository  extends CrudRepository <SavedRequests,Long> {

	

}
