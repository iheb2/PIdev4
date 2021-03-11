package tn.esprit.spring.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="Answer")
public class Answer implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="Id_a")
	private long id_a;

	@Temporal (TemporalType.DATE)
	private Date DateA;
	@Column(name="Messagea")
	private String messagea;
	@OneToOne(mappedBy="answer")
	private Question question;
	
	public Answer() {
		super();
	}
	
	public long getId_a() {
		return id_a;
	}
	public void setId_a(long id_a) {
		this.id_a = id_a;
	}
	public Date getDateA() {
		return DateA;
	}
	public void setDateA(Date dateA) {
		DateA = dateA;
	}
	public String getMessagea() {
		return messagea;
	}
	public void setMessagea(String messagea) {
		this.messagea = messagea;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	
}