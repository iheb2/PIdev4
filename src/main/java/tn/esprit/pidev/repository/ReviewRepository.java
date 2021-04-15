package tn.esprit.pidev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pidev.model.Review;
@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer>{
	@Query("Select reviews from Review reviews where reviews.AgencyId= :AgencyId")
    List<Review> fetchReviewByAgencyId(@Param("AgencyId") Long AgencyId) ;
}
