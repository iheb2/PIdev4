package tn.esprit.spring.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity

public class AmalWacelGhada {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
	private Double TransactionsRate ;
	private Double MonthRedRate ;
	private Integer NbCeditPaye ;
	private Integer MontantCredit;
	private Date attribDate;
	private Integer nbmois ;
	
	  @OneToOne(mappedBy = "tabscore")
	  @JsonIgnore
	    private Client client;
	  
	  @JsonIgnore
	  @OneToMany(cascade = CascadeType.ALL, mappedBy="ac")
		private List<Transaction> transaction;
	
	

	public List<Transaction> getTransaction() {
		return transaction;
	}
	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}
	public Client getClient() {
		return client;
	}
	public Integer getMontantCredit() {
		return MontantCredit;
	}
	public void setMontantCredit(Integer montantCredit) {
		MontantCredit = montantCredit;
	}
	public Date getAttribDate() {
		return attribDate;
	}
	public void setAttribDate(Date attribDate) {
		this.attribDate = attribDate;
	}
	public Integer getNbmois() {
		return nbmois;
	}
	public void setNbmois(Integer nbmois) {
		this.nbmois = nbmois;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public AmalWacelGhada() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Double getTransactionsRate() {
		return TransactionsRate;
	}
	public void setTransactionsRate(Double transactionsRate) {
		TransactionsRate = transactionsRate;
	}
	public Double getMonthRedRate() {
		return MonthRedRate;
	}
	public void setMonthRedRate(Double monthRedRate) {
		MonthRedRate = monthRedRate;
	}
	public Integer getNbCeditPaye() {
		return NbCeditPaye;
	}
	public void setNbCeditPaye(Integer nbCeditPaye) {
		NbCeditPaye = nbCeditPaye;
	}

	
}
