package tn.esprit.spring.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class sessionnn {
	@Id
	private Long id= (long) 1;
	private Long chat_id=(long) 1;
	private String email="sirine.kraiem@esprit.tn";
	public Long getId() {
		return id;
	}

	public Long getChat_id() {
		return chat_id;}

	public sessionnn() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setChat_id(Long chat_id) {
		this.chat_id = chat_id;
	}
	


	



}
