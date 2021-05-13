package com.github.adminfaces.starter.service;

import java.util.Arrays;
import java.util.Date;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.adminfaces.starter.model.Agency;
import com.github.adminfaces.starter.model.AgencyPage;
import com.github.adminfaces.starter.model.Agencysearchcriterea;
import com.github.adminfaces.starter.model.Rating;
import com.github.adminfaces.starter.model.Review;
import com.github.adminfaces.starter.repository.AgencyCriteriaRepository;
import com.github.adminfaces.starter.repository.AgencyRepository;
import com.github.adminfaces.starter.repository.RatingRepository;
import com.github.adminfaces.starter.repository.ReviewRepository;

@Service
public class AgencyServiceImpl implements IAgencyService{
	@Autowired
   AgencyRepository agencyrepo; 
	@Autowired
	AgencyCriteriaRepository agencyCriteriarepo;
	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private RatingRepository ratingRepository;
@Autowired
	private static final Logger LOG = LoggerFactory.getLogger(AgencyServiceImpl.class);
	@Override
	public List<Agency> retrieveAllAgencies() {
		List<Agency> agencylist =(List<Agency>)agencyrepo.findAll();
		return agencylist;
	}

	@Override
	public Agency addAgency(Agency agency) {
		
		return agencyrepo.save(agency);
	}

	@Override
	public void deleteAgency(Agency agency) {
		agencyrepo.deleteById(agency.getId_agency());
		
	}

	@Override
	public Agency updateAgency(Agency agency) {
		return agencyrepo.save(agency);
	}

	@Override
	public Optional<Agency> retrieveAgency(Long id_agency) {
		return agencyrepo.findById(id_agency);
	}

	@Override
	public void addReview(Review reviews) {
		reviews.setCreateTimestamp(new Date());		
		reviewRepository.save(reviews);
		Rating ratings = null;
		List<Rating> ratingList = ratingRepository.fetchRatingByAgencyId(reviews.getAgencyId());
		if (!ratingList.isEmpty()) {
			LOG.info("Update existing ratings...");
			ratings = ratingList.get(0);
		} else {
			LOG.info("Add a new ratings...");
			ratings = new Rating(reviews.getAgencyId(), 0, 0, 0, (double) 0, 0, new Date());
		}

	/*	if ("Y".equalsIgnoreCase(reviews.getLikeMovie())) {
			ratings.setLikes(ratings.getLikes() + 1);
		} else {
			ratings.setDislike(ratings.getDislike() + 1);
		}*/
		ratings.setTotalRatings(ratings.getTotalRatings() + 1);
		ratingRepository.save(ratings);
		
	
		
	}
	
@Override
	public List<Agency>  getAgencyInfo(Long AgencyId) {

	Optional<Agency> agencyOptional = agencyrepo.findById(AgencyId);
		Agency agency = agencyOptional.get();
		// Update Reviews
		List<Review> reviews = reviewRepository.fetchReviewByAgencyId(agency.getId_agency());
		agency.setReviews(reviews);

		
		List<Agency> list = Arrays.asList(new Agency[] { agency });
		

		return list;
	}

@Override
public List<Review> retrieveAllReviews() {
	List<Review> Reviewlist =(List<Review> )reviewRepository.findAll();
	return Reviewlist;
}

@Override
public Page<Agency> getagencies(AgencyPage agencyPage, Agencysearchcriterea agencysearchcriterea) {
	
	return agencyCriteriarepo.findAllWithFilters(agencyPage, agencysearchcriterea);
}


}
	
	

