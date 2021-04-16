package tn.esprit.spring.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


import tn.esprit.spring.model.ChatMessage;
import tn.esprit.spring.service.IChatMessageService;
@Controller
public class ChatMessageController {
	@Autowired
	IChatMessageService chatmsg;
////////////////pour l'agent /////////////////////
	@GetMapping("/retrieve-all-msgs")
	@ResponseBody
	public List<ChatMessage> getmsgs() {
		List<ChatMessage> list = chatmsg.retrieveAllChatmessages();
		return list;	
	}
	@GetMapping("/retrieve-all-msgs/{id}")
	@ResponseBody
	public List<ChatMessage> getmsgsC(@PathVariable("id") long id) {
		List<ChatMessage> list = chatmsg.msgclient(id);
		return list;	
	}
	@GetMapping("/retrieve-all-msgsN")
	@ResponseBody
	public List<ChatMessage> getmsgsN() {
		List<ChatMessage> list = chatmsg.retrievenonanswered();
		return list;	
	}
	@GetMapping("/retrieve-all-msgsN/{id}")
	@ResponseBody
	public List<ChatMessage> getmsgsCN(@PathVariable("id") long id) {
		List<ChatMessage> list = chatmsg.retrievenonansweredclient(id);
		return list;}
	////////////pour l'agent:repondre aux msg
	@PostMapping("/answer-msg/{id}")
	@ResponseBody
	public void answerMsg(@RequestBody String s, @PathVariable("id") long id) {
		ChatMessage a = chatmsg.answermsg(s,  id);
	
	}
////////////////pour le client en cour d'utilisation///////////////////
	@PostMapping("/send-msg")
	@ResponseBody
	public ChatMessage sendMsg(@RequestBody String s) {
		ChatMessage a = chatmsg.sendmsg(s);
		return a;
	}
}
