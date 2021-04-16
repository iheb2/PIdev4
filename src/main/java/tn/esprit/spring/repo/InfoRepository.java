package tn.esprit.spring.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.model.Info;


@Repository
public interface  InfoRepository extends  CrudRepository<Info,  Long> {
	@Query("SELECT i FROM Info i WHERE i.Question =Question")
	List<Info> SearchInfoByQuestion(@Param("Question") String Question);
	/*@Query("SELECT i FROM Info i WHERE i.State= :State")
	List<Info> SearchInfoByState(@Param("State") StateI State);*/
	@Query("from Info order by SendingD asc")
	List<Info> orderByAscendingDate();
	@Query("from Info order by SendingD desc")
	List<Info> orderByDescendingDate();
	@Query("select p from Info p where p.publication_date between :min and :max")
	public List<Info> Range(@Param("min") Date min, @Param("max") Date max);
	
	

}
