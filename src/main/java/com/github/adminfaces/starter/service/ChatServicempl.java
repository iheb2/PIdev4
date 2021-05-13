package com.github.adminfaces.starter.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.adminfaces.starter.model.Chat;
import com.github.adminfaces.starter.repository.ChatRepository;


@Service
public class ChatServicempl implements IChatService{
	@Autowired
	ChatRepository chatRepository ;
	

///////////////////////coté agent////////////////////
	@Override
//voir l'ensemble de tous les chat  :pour l'agent
	public List<Chat> retrieveAllChats() {
		return (List<Chat>) chatRepository.findAll();
	}
//pour que l'agent puisse choisir quel chat il veut consulter
		@Override
		public Chat retriveChat(String id) {
			return chatRepository.findById(Long.parseLong(id)).orElse(null);
		}
//pour l'agent afficher tous les chats qui  contiennt des msg non lu
		public List<Chat> retrievenonrespondedChats() {
			List<Chat> list= (List<Chat>) chatRepository.findAll();
			List<Chat> list1= new ArrayList<Chat>();
			for (Chat c: list){if (c.getMsg_non_lu1()!=0){list1.add(c);}}
			return list1;

		}

////////////coté client/////////////////////////////////

//////////////////////pas de controller////////////////////
//la creation du chat se fait simultanaiment avec la creation du client le client et le chat on le meme id.
		//on lui donne en parametre l'id  du client
		@Override
		public Chat createChat(Long id) {
			Chat i = new Chat();
			i.setMsg_non_lu1(null);
			i.setMsg_non_lu2(null);
			i.setId(id);
			return chatRepository.save(i);
		}
//mettre a jour le nb de msg non lus par le client  et le retourner(sera utiiser lors de la creation du msg)
	@Override
	public long updateChatclient(String id) {
		Chat c = retriveChat(id);
		Long nb=c.getMsg_non_lu1()+1;
		c.setMsg_non_lu1(nb);
		c.setMsg_non_lu2((long) 0);
		chatRepository.save(c);
		return nb;
	
	}
//mettre a jour le nb de msg non lus par l'agent pour chaque client et le retourner (sera utiiser lors de la creation du msg)
	@Override
	public long updateChatagent(String id) {
		Chat c = retriveChat( id);
		Long nb=c.getMsg_non_lu2()+1;
		c.setMsg_non_lu2(nb);
		c.setMsg_non_lu1((long) 0);
		chatRepository.save(c);
		return nb;
	}

	
}
