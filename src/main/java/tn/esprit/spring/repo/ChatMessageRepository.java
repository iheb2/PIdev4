package tn.esprit.spring.repo;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.model.ChatMessage;

public interface ChatMessageRepository extends CrudRepository <ChatMessage,  Long> {

}
