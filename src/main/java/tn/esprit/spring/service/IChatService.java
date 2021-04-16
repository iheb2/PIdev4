package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.model.Chat;
import tn.esprit.spring.model.ChatMessage;


public interface IChatService { 
	List<Chat> retrieveAllChats();
	Chat createChat(Long id);
	Chat retriveChat(String id);
	public Chat retriveChatclient();
	public List<Chat> retrievenonrespondedChats();
	//Long updateChat(String id);
	long updateChatclient();
	long updateChatagent(String id);
	}
