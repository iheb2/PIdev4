package com.github.adminfaces.starter.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.adminfaces.starter.model.Information;



@Repository
public interface  InfoRepository extends  CrudRepository<Information,  Long> {
/*	@Query("SELECT i FROM Info i WHERE i.Question =Question")
	List<Info> SearchInfoByQuestion(@Param("Question") String Question);
	@Query("from Info order by nb_consultation asc")
	List<Info> orderByAscendingnb();
	@Query("from Info order by rate desc")
	List<Info> orderByDescendingrate();
	@Query("select p from Info p where p.publication_date between :min and :max")
	public List<Info> Range(@Param("min") Date min, @Param("max") Date max);
*/
	
	

}
