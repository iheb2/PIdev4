package com.github.adminfaces.starter.service;

import java.util.List;

import com.github.adminfaces.starter.model.ChatMessage;





public interface IChatMessageService {
	List<ChatMessage> retrieveAllChatmessages();
	
	ChatMessage answermsg(String i,long id);
	List<ChatMessage> retrievenonanswered();
	public List<ChatMessage> msgclient(long id);
	List<ChatMessage> retrievenonansweredclient(long id);
	ChatMessage sendmsg(String i, String id);

	
}
