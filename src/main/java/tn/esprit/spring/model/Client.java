package tn.esprit.spring.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
@Entity
public class Client  implements Serializable 	{

	//universal version identifier
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id_client;
	
	@Column(name="cin")
	private String cin;
	
	
	@Column(name="adress")
	private String adress;
	


	@Temporal (TemporalType.DATE)
	private Date date_of_birth ;
	

	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="clientQuest")
	private Set<Question> question;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="clientNotif")
	private Set<Notification> notification;
	
	


	public Long getId_client() {
		return id_client;
	}

	public void setId_client(Long id_client) {
		this.id_client = id_client;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	
	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	
	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}




	@Override
	public int hashCode() {
		return super.hashCode();
	}



	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}



	@Override
	public String toString() {
		return super.toString();
	}



	public Set<Question> getQuestion() {
		return question;
	}

	public void setQuestion(Set<Question> question) {
		this.question = question;
	}

	public Set<Notification> getNotification() {
		return notification;
	}

	public void setNotification(Set<Notification> notification) {
		this.notification = notification;
	}








	
}
