package com.github.adminfaces.starter.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class SavedRequests implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_request;
	
	
	
	@Column(name="email")
	private String email;
	
	@Column(name="type")
	private String type;

	@Temporal (TemporalType.DATE)
	private Date cr_date ;
	
	@Temporal (TemporalType.DATE)
	private Date exp_date ;
	@ManyToOne
	private Client client;
	
	
	public SavedRequests(Long id_request) {
		super();
		this.id_request = id_request;
	}
	public SavedRequests() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId_request() {
		return id_request;
	}
	public void setId_request(Long id_request) {
		this.id_request = id_request;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getCr_date() {
		return cr_date;
	}
	public void setCr_date(Date cr_date) {
		this.cr_date = cr_date;
	}
	public Date getExp_date() {
		return exp_date;
	}
	public void setExp_date(Date exp_date) {
		this.exp_date = exp_date;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
