package com.github.adminfaces.starter.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.adminfaces.starter.model.ChatMessage;
import com.github.adminfaces.starter.model.TypeC;
import com.github.adminfaces.starter.repository.ChatMessageRepository;




@Service
public class ChatMessageServicempl implements IChatMessageService{
	@Autowired
	ChatMessageRepository chatmessageRepository ;
	@Autowired
	IChatService  chatService;
	
	@Override
//pour l'agent afficher tous les message
		public List<ChatMessage> retrieveAllChatmessages() {
			return  (List<ChatMessage>) chatmessageRepository.findAll();
	}

//pour le client: envoyer un msg
	@Override
	public ChatMessage sendmsg(String i,String id) {
		ChatMessage c = new ChatMessage(i); //cree un chatmsg contenant uniquement le msg i
		c.setChat(chatService.retriveChat(id)); //lier le chatmsg au chat grace a session
		Long n = chatService.updateChatclient(id);   //mettre a jour le nb de msg envoyer par le client et non lu par l'agent
		c.setType(TypeC.a);
		long z = chatmessageRepository.count();
		c.setId(z+1);
		return chatmessageRepository.save(c);
	}
//pour l'agent:answer msg
	@Override
	public ChatMessage answermsg(String i, long id) {
		ChatMessage c = new ChatMessage(i);
		c.setChat(chatService.retriveChat(String.valueOf(id)));
		Long n = chatService.updateChatagent(String.valueOf(id));
		c.setType(TypeC.b);
		long z = chatmessageRepository.count();
		c.setId(z+1);

		List<ChatMessage> list= retrievenonansweredclient(id);
		for(ChatMessage m:list){m.setType(TypeC.c);
		chatmessageRepository.save(m);}
		return chatmessageRepository.save(c);
		
		
	}
//pour l'agent afficher tous les msg echangés pour un client particulier
	@Override
	public List<ChatMessage> msgclient(long id){
		List<ChatMessage> list= (List<ChatMessage>) chatmessageRepository.findAll();
		List<ChatMessage> list1= new ArrayList<ChatMessage>();
		for (ChatMessage c:list){ if(c.getChat().getId()==id){list1.add(c);}}
		return list1;
	}
	
//pour l'agent afficher tous les msg auquel il n'a pas repondu	
	@Override
	public List<ChatMessage> retrievenonanswered() {
		List<ChatMessage> list= (List<ChatMessage>) chatmessageRepository.findAll();
		List<ChatMessage> list1= new ArrayList<ChatMessage>();
		for (ChatMessage c:list){
			if(c.getType()==TypeC.a){list1.add(c);}
		}
		
		return list1;
	}
	//pour l'agent afficher tous les msg auquel il n'a pas repondu	pour un client pariculier
		@Override
		public List<ChatMessage> retrievenonansweredclient(long id) {
			List<ChatMessage> list=  msgclient(id);
			List<ChatMessage> list1= new ArrayList<ChatMessage>();
			for (ChatMessage c:list){
				if(c.getType()==TypeC.a){list1.add(c);}
			}
			return list1;
		}

}
