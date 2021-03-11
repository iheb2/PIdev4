package tn.esprit.spring.model;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name="Transaction")
public class Transaction implements Serializable {
		private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="Id_t")
	private Long id_t;
	@Column(name="amount")
	private double amount;
	@Column(name="Date_transaction")
	@Temporal (TemporalType.DATE)
	private Date DateT;
	@Column(name="Type_Transaction")
	@Enumerated(EnumType.STRING)
	private TypeT type;
	@ManyToOne
	Account account;
	
	

	public Transaction() {
		super();
	}
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
	public void setType(TypeT type) {
		this.type = type;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Transaction [id_t=" + id_t + ", amount=" + amount + ", DateT=" + DateT + ", type=" + type + ", account="
				+ account + "]";
	}
	
}
