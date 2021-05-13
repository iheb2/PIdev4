package com.github.adminfaces.starter.service;
import java.util.List;

import java.util.Optional;

import com.github.adminfaces.starter.model.RequestCheckbook;

public interface IRequestCheckbookService {
	List<RequestCheckbook> retrieveAllRequestCheckbooks();
	RequestCheckbook addRequestCheckbook(RequestCheckbook request);
	void deleteRequestCheckbook(RequestCheckbook request);
	RequestCheckbook updateRequestCheckbook(RequestCheckbook request);
	Optional<RequestCheckbook> retrieveRequestCheckbook(Long id_request);
}
