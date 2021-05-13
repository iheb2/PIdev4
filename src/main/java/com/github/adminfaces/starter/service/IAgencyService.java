package com.github.adminfaces.starter.service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.github.adminfaces.starter.model.Agency;
import com.github.adminfaces.starter.model.AgencyPage;
import com.github.adminfaces.starter.model.Agencysearchcriterea;
import com.github.adminfaces.starter.model.Review;

public interface IAgencyService {
	List<Agency> retrieveAllAgencies();
	List<Review> retrieveAllReviews();
	Agency addAgency(Agency agency);
	void deleteAgency(Agency agency);
	Agency updateAgency(Agency agency);
	Optional<Agency> retrieveAgency(Long id_agency);
	void addReview(Review reviews);
	List<Agency>   getAgencyInfo(Long AgencyId);
	Page<Agency> getagencies(AgencyPage agencyPage, Agencysearchcriterea agencysearchcriterea);
}
