package com.github.adminfaces.starter.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.github.adminfaces.starter.model.Client;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
public class Report  implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	private String message;
    private String subject;
    private String respond="";
	private String state;
	@Temporal (TemporalType.DATE)
	private Date dateSe;
	@Temporal (TemporalType.DATE)
	private Date dateRe;
	private Long t;
	
	public Long getT() {
		return t;
	}

	public void setT(Long t) {
		this.t = t;
	}

	@ManyToOne
	private Client client;
	public Long getId() {
		return id;
	}
	
		public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}




	public String getRespond() {
		return respond;
	}








	public void setRespond(String respond) {
		this.respond = respond;
	}








	public Date getDateRe() {
		return dateRe;
	}








	public void setDateRe(Date dateRe) {
		this.dateRe = dateRe;
	}








	public Client getClient() {
		return client;
	}








	public void setClient(Client client) {
		this.client = client;
	}








	public void setId(Long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getDateSe() {
		return dateSe;
	}
	public void setDateSe(Date dateSe) {
		this.dateSe = dateSe;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateSe == null) ? 0 : dateSe.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Report other = (Report) obj;
		if (dateSe == null) {
			if (other.dateSe != null)
				return false;
		} else if (!dateSe.equals(other.dateSe))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	public Report(Long id, String message, String state, Date dateSe) {
		this.id = id;
		this.message = message;
		this.state = state;
		this.dateSe = dateSe;
	}
	public Report() {
		super();
	}

    public boolean hasMessage() {
        return message != null && !"".equals(message.trim());
    }

    public boolean hasState() {
        return state != null && !"".equals(state.trim());
    }
	
}
