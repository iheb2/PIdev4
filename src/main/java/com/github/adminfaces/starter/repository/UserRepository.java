package com.github.adminfaces.starter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.adminfaces.starter.infra.model.*;
import com.github.adminfaces.starter.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
    User findByEmail(String emailId);
	public User getUserByUsernameAndPassword(String username, String password); 

	User findByUsername(String username);


}
