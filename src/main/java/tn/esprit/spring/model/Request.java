package tn.esprit.pidev.model;
import java.io.Serializable;

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

	@Temporal (TemporalType.DATE)
	private Date cr_date ;
	
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
