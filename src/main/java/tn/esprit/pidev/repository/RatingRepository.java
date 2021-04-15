package tn.esprit.pidev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pidev.model.Rating;

@Repository 
public interface RatingRepository extends CrudRepository<Rating, Integer>{

	@Query("Select rating from Rating rating where rating.id= :AgencyId")
	List<Rating> fetchRatingByAgencyId(@Param("AgencyId") Long AgencyId);
}
