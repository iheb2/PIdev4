package com.github.adminfaces.starter.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.adminfaces.starter.model.ChatMessage;


public interface ChatMessageRepository extends CrudRepository <ChatMessage,  Long> {

}
