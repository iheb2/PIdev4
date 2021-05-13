package com.github.adminfaces.starter.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.adminfaces.starter.infra.model.*;
import com.github.adminfaces.starter.model.Report;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long>{
	@Query("select p from Report p where p.dateSe between :min and :max")
	public List<Report> Range1(@Param("min") Date min, @Param("max") Date max);
	@Query("SELECT i FROM Report i WHERE i.state= :state")
	List<Report> SearchReportByState(@Param("state")String State);

}
