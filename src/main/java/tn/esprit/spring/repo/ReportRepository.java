package tn.esprit.spring.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.model.Report;

import tn.esprit.spring.model.StateR;

@Repository
public interface ReportRepository extends  CrudRepository<Report,  Long>  {
	@Query("select p from Report p where p.DateR between :min and :max")
	public List<Report> Range1(@Param("min") Date min, @Param("max") Date max);
	
	@Query("SELECT i FROM Report i WHERE i.State= :State")
	List<Report> SearchReportByState(@Param("State") StateR State);

}
