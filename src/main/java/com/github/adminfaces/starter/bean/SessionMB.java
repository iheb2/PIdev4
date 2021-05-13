package com.github.adminfaces.starter.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.github.adminfaces.starter.model.Chat;
import com.github.adminfaces.starter.model.ChatMessage;
import com.github.adminfaces.starter.model.Report;
import com.github.adminfaces.starter.model.Transaction;
import com.github.adminfaces.starter.model.TypeC;
import com.github.adminfaces.starter.model.User;
import com.github.adminfaces.starter.repository.ChatMessageRepository;
import com.github.adminfaces.starter.repository.ChatRepository;
import com.github.adminfaces.starter.repository.ClientRepository;
import com.github.adminfaces.starter.repository.UserRepository;
import com.github.adminfaces.starter.service.IChatMessageService;
import com.github.adminfaces.starter.service.IChatService;

@Named
@ViewScoped
public class SessionMB implements Serializable {
	@Autowired
	UserRepository us;
	@Autowired
	ClientRepository cl;
	@Autowired
	ChatRepository r;
	@Autowired
	ChatMessageRepository b;
	@Autowired
	IChatService  ch;
	@Autowired
	IChatMessageService  cm;
	private List<Transaction> historique;
	private String currentUser;
	private List<Report> reclamation;
	private Long ids;
	private List<ChatMessage> c;
	private String msg;
	
	
	
	public List<ChatMessage> getC() {
		Long a = us.findByUsername(currentUser).getId();
		c= cl.findById(a).get().getChat().getMessages();
		
		return c;
	}

	public void sedmsg(){
		Long a = us.findByUsername(currentUser).getId();
		ChatMessage c = new ChatMessage(msg); //cree un chatmsg contenant uniquement le msg i
		c.setChat(r.findById(a).get()); //lier le chatmsg au chat grace a session
	   //mettre a jour le nb de msg envoyer par le client et non lu par l'agent
		c.setType(TypeC.a);
		c.setPostedDate(new Date());
		long z = b.count();
		c.setId(z+1);
		b.save(c);

		
	}
	
	

	public void setC(List<ChatMessage> c) {
		this.c = c;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public Long getIds() {
		ids = us.findByUsername(currentUser).getId();
		return ids;
	}


	public void setIds(Long ids) {
		this.ids = ids;
	}


	public List<Report> getReclamation() {
		Long a = us.findByUsername(currentUser).getId();
		reclamation= cl.findById(a).get().getReport();
		return reclamation;
	}


	public void setReclamation(List<Report> reclamation) {
		this.reclamation = reclamation;
	}


	@PostConstruct
	public void init() {
		currentUser = SecurityContextHolder.getContext().getAuthentication().getName();

	}

	
	public List<Transaction> getHistorique() {
		historique = new ArrayList<Transaction>();
		User user = us.findByUsername(currentUser);
		
		if (user != null) {
			Long a = user.getId();
			
			historique= cl.findById(a).get().getTabscore().getTransaction();
		}
		return historique;
	}


	public void setHistorique(List<Transaction> historique) {
		this.historique = historique;
	}


	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
}
