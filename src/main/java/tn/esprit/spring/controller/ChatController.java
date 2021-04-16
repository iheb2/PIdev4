package tn.esprit.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.esprit.spring.model.Chat;
import tn.esprit.spring.model.ChatMessage;
import tn.esprit.spring.model.TypeC;
import tn.esprit.spring.service.IChatService;


@Controller
public class ChatController {
	@Autowired
	IChatService  chatService;
////////////////	pour l'agent /////////////////////
	@GetMapping("/retrieve-all-Chats")
	@ResponseBody
	public List<Chat> getchats() {
		List<Chat> list = chatService.retrieveAllChats();
		return list;	
	}
	
	@GetMapping("/retrieve-chat/{id}")
	@ResponseBody
	public Chat getchat(@PathVariable("id") String id){
	Chat c =chatService.retriveChat(id);	
	return c;
	}
	
	@GetMapping("/retrieve-all-msg-in-chat/{id}")
	@ResponseBody
	public String getmsg(@PathVariable("id") String id){
	Chat c =chatService.retriveChat(id);
	String S="";
	List<ChatMessage> list= c.getMessages();
	for (ChatMessage i :list){ 
		if (i.getType()==TypeC.b) 
		{	S=S+ "\n "+i.getPostedDate()+"\n"+"Agent:"+i.getMessage();}
	else {S=S+ "\n "+i.getPostedDate()+"\n"+i.getChat().getOwner().getFirstName()+"  "+i.getChat().getOwner().getLastName()+i.getMessage();}}
	
return S;
	}
	
	@GetMapping("/retrieve-all-Chats-nonrepondu")
	@ResponseBody
	public String getchats1() {
		List<Chat> a = chatService.retrievenonrespondedChats();
		String s=" ";
		for(Chat i:a){s=s+"\n le chat "+i.getId()+" contient "+i.getMsg_non_lu1()+" msg non lus";}
		return s;
		
	}
	@GetMapping("/retrieve-all-msg-nonrepondu_id/{id}")
	@ResponseBody
	public String getchats4(@PathVariable("id") String id) {
		Chat i = chatService.retriveChat(id);;
		
		String s=" ";
		Long o = i.getMsg_non_lu1();
		{s=s+"vous avez "+o+" nv msg de la part du client"+id+"\n";}
		for(ChatMessage a:i.getMessages()){if(a.getType()==TypeC.a){
				s=s+a.getMessage()+"\n";}}
		
		return s;
		
	}
///////////////////////// pour le client/////////////////////////
	
	@GetMapping("/retrieve-chat")
	@ResponseBody
	public Chat  getchat(){	
	return  chatService.retriveChatclient();}
	@GetMapping("/retrieve-all-msg-in-chat-client")
	@ResponseBody
	public String getmsg1(){
	Chat c =chatService.retriveChatclient();
	String S="";
	List<ChatMessage> list= c.getMessages();
	for (ChatMessage i :list){ 
		if (i.getType()==TypeC.b) 
		{	S=S+ "\n "+i.getPostedDate()+"\n"+"Agent:"+i.getMessage();}
	else {S=S+ "\n "+i.getPostedDate()+"\n"+i.getChat().getOwner().getFirstName()+"  "+i.getChat().getOwner().getLastName()+i.getMessage();}}
	
return S;
	}
	@GetMapping("/retrieve-all-msg-nonrepondu_client")
	@ResponseBody
	public String getchats2() {
		Chat i = chatService.retriveChatclient();
		List<ChatMessage> list2 = new ArrayList<ChatMessage>();
		String s=" ";
		Long o = i.getMsg_non_lu2();
		{s=s+"vous avez "+o+" nv msg ";}
		for(ChatMessage a:i.getMessages()){if(a.getType()==TypeC.b){
		list2.add(a);}}
		for (int j = 0;j<o;j++){s="\n"+list2.get(list2.size()-j).getMessage()+"\n"+s;}
		
		return s;
		
	}
	
///////////////teste des fct//////////////////////////



}
