package com.github.adminfaces.starter.model;

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
	private Long id;
	@Column(name="Question")
	private String question;
	@Column(name="Answer")
	private String answer;
	@Column(name="publication_date")
	@Temporal (TemporalType.DATE)
	private Date publication_date= new Date();
	@Column(name="nb_consultation")
	private Long nb_consultation=(long) 0;
	@Column(name="rate")
	private Integer rate=0;
	@Column(name="nb_rate")
	private long nb_rate=0;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Date getPublication_date() {
		return publication_date;
	}
	public void setPublication_date(Date publication_date) {
		this.publication_date = publication_date;
	}
	public Long getNb_consultation() {
		return nb_consultation;
	}
	public void setNb_consultation(Long nb_consultation) {
		this.nb_consultation = nb_consultation;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	public long getNb_rate() {
		return nb_rate;
	}
	public void setNb_rate(long nb_rate) {
		this.nb_rate = nb_rate;
	}
	public Info(Long id, String question, String answer, Date publication_date, Long nb_consultation, Integer rate,
			long nb_rate) {
		this.id = id;
		this.question = question;
		this.answer = answer;
		this.publication_date = publication_date;
		this.nb_consultation = nb_consultation;
		this.rate = rate;
		this.nb_rate = nb_rate;
	}
	public Info() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nb_consultation == null) ? 0 : nb_consultation.hashCode());
		result = prime * result + (int) (nb_rate ^ (nb_rate >>> 32));
		result = prime * result + ((publication_date == null) ? 0 : publication_date.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
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
		Info other = (Info) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nb_consultation == null) {
			if (other.nb_consultation != null)
				return false;
		} else if (!nb_consultation.equals(other.nb_consultation))
			return false;
		if (nb_rate != other.nb_rate)
			return false;
		if (publication_date == null) {
			if (other.publication_date != null)
				return false;
		} else if (!publication_date.equals(other.publication_date))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.rate))
			return false;
		return true;
	}
	 public boolean hasQuestion() {
	        return question != null && !"".equals(question.trim());
	    }
	 public boolean hasAnswer() {
	        return answer != null && !"".equals(answer.trim());
	    }
	public Info(Long id, String question, String answer, Integer rate) {
		super();
		this.id = id;
		this.question = question;
		this.answer = answer;
		this.rate = rate;
	}

}
