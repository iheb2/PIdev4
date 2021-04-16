package tn.esprit.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Info")
public class Info implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="Id_i")
	private long Id_i;
	@Column(name="Question")
	private String Question;
	@Column(name="Answer")
	private String Answer;
	@Column(name="publication_date")
	@Temporal (TemporalType.DATE)
	private Date publication_date= new Date();
	@Column(name="nb_consultation")
	private long nb_consultation=0;
	@Column(name="rate")
	private long rate=0;
	@Column(name="nb_rate")
	private long nb_rate=0;
	public long getNb_rate() {
		return nb_rate=0;
	}
	public void setNb_rate(long nb_rate) {
		this.nb_rate = nb_rate;
	}
	public long getNb_consultation() {
		return nb_consultation;
	}
	public void setNb_consultation(long nb_consultation) {
		this.nb_consultation = nb_consultation;
	}
	public long getId_i() {
		return Id_i;
	}
	public void setId_i(long id_i) {
		Id_i = id_i;
	}
	public String getQuestion() {
		return Question;
	}
	public void setQuestion(String question) {
		Question = question;
	}
	public String getAnswer() {
		return Answer;
	}
	public void setAnswer(String answer) {
		Answer = answer;
	}
	public Date getPublication_date() {
		return publication_date;
	}
	public void setPublication_date(Date publication_date) {
		this.publication_date = publication_date;
	}
	public long getRate() {
		return rate;
	}
	public void setRate(long rate) {
		this.rate = rate;
	}
	



	

}
