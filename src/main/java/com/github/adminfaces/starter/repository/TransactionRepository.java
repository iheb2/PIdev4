package com.github.adminfaces.starter.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.adminfaces.starter.model.Transaction;






@Repository

public interface TransactionRepository  extends CrudRepository<Transaction, Long> {


}
