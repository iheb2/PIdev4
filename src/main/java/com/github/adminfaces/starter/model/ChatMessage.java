package com.github.adminfaces.starter.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
public class ChatMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id; 
	@ManyToOne
	@JoinColumn(name = "chat_id")
	@JsonDeserialize
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private Chat chat;
	private String message;
	@Enumerated(EnumType.STRING)
	private TypeC type;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat
	@JsonDeserialize
	private Date postedDate=new Date();
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public TypeC getType() {
		return type;
	}

	public void setType(TypeC type) {
		this.type = type;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public ChatMessage() {
		super();
	}

	public ChatMessage(String message) {
		super();
		this.message = message;
	}

	public ChatMessage(Long id, Chat chat, String message, TypeC type, Date postedDate) {
		super();
		this.id = id;
		this.chat = chat;
		this.message = message;
		this.type = type;
		this.postedDate = postedDate;
	}

	public ChatMessage(Chat chat, String message, TypeC type, Date postedDate) {
		super();
		this.chat = chat;
		this.message = message;
		this.type = type;
		this.postedDate = postedDate;
	}
	


}
