package com.github.adminfaces.starter.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.github.adminfaces.starter.model.Request;
import com.github.adminfaces.starter.model.RequestCredit;

@Repository
public interface RequestCreditRepository extends CrudRepository <RequestCredit,Long>{

}
