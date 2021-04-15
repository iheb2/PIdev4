package tn.esprit.spring.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Transaction")

public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id_t")
	private Long id_t;
	@Column(name = "amount")
	private double amount;
	@Column(name = "Date_transaction")
	@Temporal(TemporalType.DATE)
	private Date DateT = new Date();
	@Column(name = "Date_p")
	@Temporal(TemporalType.DATE)
	private Date DateP;
	@Column(name = "Type_Transaction")
	@Enumerated(EnumType.STRING)
	private TypeT type;
	@Column(name = "mode")
	@Enumerated(EnumType.STRING)
	private mode mode;
	@Column(name = "retard")
	private Long retard;
	@Column(name = "nb_pay")
	private int nb;
	@Enumerated(EnumType.STRING)
	private StateT State;

	@ManyToOne
	AmalWacelGhada ac;

	public Long getId_t() {
		return id_t;
	}

	public void setId_t(Long id_t) {
		this.id_t = id_t;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDateT() {
		return DateT;
	}

	public void setDateT(Date dateT) {
		DateT = dateT;
	}

	public TypeT getType() {
		return type;
	}

	public StateT getState() {
		return State;
	}

	public void setState(StateT state) {
		State = state;
	}

	public AmalWacelGhada getAc() {
		return ac;
	}

	public void setAc(AmalWacelGhada ac) {
		this.ac = ac;
	}

	public Date getDateP() {
		return DateP;
	}

	public void setDateP(Date dateP) {
		DateP = dateP;
	}

	public mode getMode() {
		return mode;
	}

	public void setMode(mode mode) {
		this.mode = mode;
	}

	public Long getRetard() {
		return retard;
	}

	public void setRetard(Long retard) {
		this.retard = retard;
	}

	public int getNb() {
		return nb;
	}

	public void setNb(int nb) {
		this.nb = nb;
	}

	public void setType(TypeT type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Transaction() {
		super();
	}

	public Transaction(Long id_t, double amount, Date dateT, TypeT type) {
		this.id_t = id_t;
		this.amount = amount;
		DateT = dateT;
		this.type = type;
	}

}
