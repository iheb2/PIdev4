package tn.esprit.spring.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.model.Chat;
import tn.esprit.spring.model.ChatMessage;
import tn.esprit.spring.model.TypeC;
import tn.esprit.spring.model.sessionnn;
import tn.esprit.spring.repo.ChatMessageRepository;
import tn.esprit.spring.repo.ChatRepository;



@Service
public class ChatMessageServicempl implements IChatMessageService{
	@Autowired
	ChatMessageRepository chatmessageRepository ;
	@Autowired
	IChatService  chatService;
	sessionnn s = new sessionnn();
	@Override
//pour l'agent afficher tous les message
		public List<ChatMessage> retrieveAllChatmessages() {
			return  (List<ChatMessage>) chatmessageRepository.findAll();
	}

//pour le client: envoyer un msg
	@Override
	public ChatMessage sendmsg(String i) {
		ChatMessage c = new ChatMessage(i); //cree un chatmsg contenant uniquement le msg i
		c.setChat(chatService.retriveChatclient()); //lier le chatmsg au chat grace a session
		Long n = chatService.updateChatclient();   //mettre a jour le nb de msg envoyer par le client et non lu par l'agent
		c.setType(TypeC.a);
		return chatmessageRepository.save(c);
	}
//pour l'agent:answer msg
	@Override
	public ChatMessage answermsg(String i, long id) {
		ChatMessage c = new ChatMessage(i);
		c.setChat(chatService.retriveChat(String.valueOf(id)));
		Long n = chatService.updateChatagent(String.valueOf(id));
		c.setType(TypeC.b);
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
