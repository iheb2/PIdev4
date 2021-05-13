package com.github.adminfaces.starter.service;

import java.util.List;

import com.github.adminfaces.starter.model.Chat;


public interface IChatService { 
	List<Chat> retrieveAllChats();
	Chat createChat(Long id);
	Chat retriveChat(String id);

	public List<Chat> retrievenonrespondedChats();
	//Long updateChat(String id);
	
	long updateChatagent(String id);
	long updateChatclient(String id);
	}
