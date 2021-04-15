package tn.esprit.pidev.service;
import java.util.List;

import java.util.Optional;

import tn.esprit.pidev.model.RequestCheckbook;

public interface IRequestCheckbookService {
	List<RequestCheckbook> retrieveAllRequestCheckbooks();
	RequestCheckbook addRequestCheckbook(RequestCheckbook request);
	void deleteRequestCheckbook(RequestCheckbook request);
	RequestCheckbook updateRequestCheckbook(RequestCheckbook request);
	Optional<RequestCheckbook> retrieveRequestCheckbook(Long id_request);
}
