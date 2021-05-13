package com.github.adminfaces.starter.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.github.adminfaces.starter.model.Dab;

@Repository
public interface DabRepository  extends  CrudRepository <Dab,Long> {

}
