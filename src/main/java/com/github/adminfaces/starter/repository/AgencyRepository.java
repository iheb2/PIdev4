package com.github.adminfaces.starter.repository;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.github.adminfaces.starter.model.Agency;

@Repository
public interface AgencyRepository extends  JpaSpecificationExecutor, PagingAndSortingRepository<Agency,Long>, CrudRepository <Agency,Long>{

}
