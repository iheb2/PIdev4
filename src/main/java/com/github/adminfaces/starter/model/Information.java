package com.github.adminfaces.starter.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class Information  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
	private String question;
	
	private String answer;

	private Integer rate;

	@Temporal (TemporalType.DATE)
	private Date publication_date= new Date();

	private Long nb_consultation=(long) 0;
	private Long nb_rate=(long) 0;


	
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

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
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

	public Long getNb_rate() {
		return nb_rate;
	}

	public void setNb_rate(Long nb_rate) {
		this.nb_rate = nb_rate;
	}

	public Information() {
		super();
	}

	public Information(Long id, String question, String answer, Integer rate) {
		super();
		this.id = id;
		this.question = question;
		this.answer = answer;
		this.rate = rate;
	}
	



	public Information(Long id, String question, String answer, Integer rate, Date publication_date,
			Long nb_consultation, Long nb_rate) {
		super();
		this.id = id;
		this.question = question;
		this.answer = answer;
		this.rate = rate;
		this.publication_date = publication_date;
		this.nb_consultation = nb_consultation;
		this.nb_rate = nb_rate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nb_consultation == null) ? 0 : nb_consultation.hashCode());
		result = prime * result + ((nb_rate == null) ? 0 : nb_rate.hashCode());
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
		Information other = (Information) obj;
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
		if (nb_rate == null) {
			if (other.nb_rate != null)
				return false;
		} else if (!nb_rate.equals(other.nb_rate))
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
	
	/*
	 * public Set<Question> getQuestion() { return question; }
	 * 
	 * public void setQuestion(Set<Question> question) { this.question =
	 * question; }
	 */

	/*
	 * public Set<Notification> getNotification() { return notification; }
	 * 
	 * public void setNotification(Set<Notification> notification) {
	 * this.notification = notification; }
	 */

}
