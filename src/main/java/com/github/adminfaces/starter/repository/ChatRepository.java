package com.github.adminfaces.starter.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.github.adminfaces.starter.model.Chat;




public interface ChatRepository extends  CrudRepository<Chat,  Long> {

	
}
