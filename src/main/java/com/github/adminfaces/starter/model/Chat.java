package com.github.adminfaces.starter.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@Entity
public class Chat implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	private Long  msg_non_lu1; //nb msg pour le client
	private Long  msg_non_lu2; //nb msg pour agent
	@OneToOne (mappedBy="chat")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private Client owner;
	@Autowired
	@OneToMany(mappedBy = "chat")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private List<ChatMessage> messages;
	public Long getMsg_non_lu1() {
		return msg_non_lu1;
	}

	public void setMsg_non_lu1(Long msg_non_lu1) {
		this.msg_non_lu1 = msg_non_lu1;
	}

	public Long getMsg_non_lu2() {
		return msg_non_lu2;
	}

	public void setMsg_non_lu2(Long msg_non_lu2) {
		this.msg_non_lu2 = msg_non_lu2;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Client getOwner() {
		return owner;
	}

	public void setOwner(Client owner) {
		this.owner = owner;
	}



	public List<ChatMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<ChatMessage> messages) {
		this.messages = messages;
	}


}
