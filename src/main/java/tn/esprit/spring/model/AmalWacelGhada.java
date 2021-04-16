package  tn.esprit.spring.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity

public class AmalWacelGhada {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
	private Double TransactionsRate ;
	private Double MonthRedRate ;
	private Double Somme ;
	public Double getSomme() {
		return Somme;
	}

	public void setSomme(Double somme) {
		Somme = somme;
	}

	private Integer NbCeditPaye ;
	private Integer MontantCredit;
	@Temporal (TemporalType.DATE)
	private Date attribDate;
	private Integer nbmois ;
	private Integer portionamount ;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="ac")
	private List<Transaction> transaction;
	
	/*
	 * ROLE_USER      
	 * ROLE_MODERATOR
	 * ROLE_ADMIN     
	 * */
	
	

	
	public Integer getPortionamount() {
		return portionamount;
	}

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	public void setPortionamount(Integer portionamount) {
		this.portionamount = portionamount;
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

	public AmalWacelGhada(long id, Double transactionsRate, Double monthRedRate, Integer nbCeditPaye,
			Integer montantCredit, Date attribDate, Integer nbmois, Integer portionamount,
			List<Transaction> transaction) {
		this.id = id;
		TransactionsRate = transactionsRate;
		MonthRedRate = monthRedRate;
		NbCeditPaye = nbCeditPaye;
		MontantCredit = montantCredit;
		this.attribDate = attribDate;
		this.nbmois = nbmois;
		this.portionamount = portionamount;
		this.transaction = transaction;
	}


	
}
