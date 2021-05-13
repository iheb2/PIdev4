package com.github.adminfaces.starter.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.github.adminfaces.starter.model.Request;
import com.github.adminfaces.starter.model.RequestCreditCard;

@Repository
public interface RequestCreditCardRepository extends CrudRepository <RequestCreditCard,Long>{

}
