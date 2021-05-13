package com.github.adminfaces.starter.model;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public class Request implements Serializable  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_request;
	
	
	
	@Column(name="email")
	private String email;
	
	@Column(name="type")
	private String type;
	
	@Column(name="status")
	private String status;

	@Temporal (TemporalType.DATE)
	private Date cr_date ;
	
	public Request(Long id_request) {
		super();
		this.id_request = id_request;
	}
	@Temporal (TemporalType.DATE)
	private Date exp_date ;
	
	@ManyToOne
	private Client client;
	
	public Request() {
		super();
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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

	public Request email(String email) {
        this.email = email;
        return this;
    }

    public Request Type(String type) {
        this.type = type;
        return this;
    }
    
    public Request Status(String status) {
        this.status = status;
        return this;
    }


	@Override
	public String toString() {
		return super.toString();
	}

    public boolean hasEmail() {
        return email != null && !"".equals(email.trim());
    }
    
    public boolean hasType() {
        return type != null && !"".equals(type.trim());
    }
    
    public boolean hasStatus() {
        return status != null && !"".equals(status.trim());
    }
	
}
