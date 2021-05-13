package com.github.adminfaces.starter.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.adminfaces.starter.model.SavedRequests;
@Repository
public interface SavedRequestsRepository  extends CrudRepository <SavedRequests,Long> {

	

}
