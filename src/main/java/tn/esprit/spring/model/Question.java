package tn.esprit.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="Question")
public class Question implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="Id_q")
	private long id_q;
	@Column(name="Topic")
	private String topic;

	@Column(name="sending_date")
	@Temporal (TemporalType.DATE)
	private Date SendingD;
	@Column(name="Message")
	private String message;
	@Column(name="State")
	@Enumerated(EnumType.STRING)
	private State state;
	@OneToOne
	private Answer answer;
	@ManyToOne 
	Client client;
	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Question() {
		super();
		
	}
	public long getId_q() {
		return id_q;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public Date getSendingD() {
		return SendingD;
	}
	public void setSendingD(Date sendingD) {
		SendingD = sendingD;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
}
