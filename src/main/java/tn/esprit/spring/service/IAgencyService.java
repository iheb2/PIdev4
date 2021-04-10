package tn.esprit.pidev.service;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import tn.esprit.pidev.model.Agency;
import tn.esprit.pidev.model.AgencyPage;
import tn.esprit.pidev.model.Agencysearchcriterea;
import tn.esprit.pidev.model.Review;

public interface IAgencyService {
	List<Agency> retrieveAllAgencies();
	List<Review> retrieveAllReviews();
	Agency addAgency(Agency agency);
	void deleteAgency(Agency agency);
	Agency updateAgency(Agency agency);
	Optional<Agency> retrieveAgency(Long id_agency);
	void addReview(Review reviews);
	List<Review>   getAgencyInfo(Long AgencyId);
	Page<Agency> getagencies(AgencyPage agencyPage,
			Agencysearchcriterea agencysearchcriterea);
}
