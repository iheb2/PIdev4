package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.model.ChatMessage;



public interface IChatMessageService {
	List<ChatMessage> retrieveAllChatmessages();
	ChatMessage sendmsg(String i);
	ChatMessage answermsg(String i,long id);
	List<ChatMessage> retrievenonanswered();
	public List<ChatMessage> msgclient(long id);
	List<ChatMessage> retrievenonansweredclient(long id);

	
}
