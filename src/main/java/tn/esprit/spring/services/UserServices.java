package tn.esprit.spring.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import tn.esprit.spring.models.User;
import tn.esprit.spring.repository.UserRepository;

@Service
@Transactional
public class UserServices {

	@Autowired
	private UserRepository repo;

	public List<User> listAll() {
		return repo.findAll(Sort.by("email").ascending());
	}

	public User retrieveClient(Long id) {
		return repo.findById(id).orElse(null);
	}
}
