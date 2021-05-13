package com.github.adminfaces.starter.model;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.github.adminfaces.starter.model.AmalWacelGhada;
@Entity
@Table(name="Transaction")
public class Transaction implements Serializable {
		private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="Id_t")
	private Long id;
	@Column(name="amount")
	private double amount;
	@Column(name="Date_transaction")
	@Temporal (TemporalType.DATE)
	private Date dateT= new Date();
	@Column(name="Date_p")
	@Temporal (TemporalType.DATE)
	private Date dateP;
	@Column(name="Type_Transaction")
	@Enumerated(EnumType.STRING)
	private TypeT type;
	@Column(name="mode")
	@Enumerated(EnumType.STRING)
	private mode mode;
	@Column(name="retard")
	private Long retard;
	@Column(name="nb_pay")
	private int nb;
	@Column(name="State")
	@Enumerated(EnumType.STRING)
	private StateT state;
	@Column(name="montant_paye")
	private double total;
	@Column(name="penalite")
	private double penalite;
	@Column(name="montant_compte")
	private double argent;
	
	@ManyToOne
	AmalWacelGhada ac;
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getPenalite() {
		return penalite;
	}
	public void setPenalite(double penalite) {
		this.penalite = penalite;
	}
	public double getArgent() {
		return argent;
	}
	public void setArgent(double argent) {
		this.argent = argent;
	}
	public StateT getState() {
		return state;
	}
	public void setState(StateT state) {
		this.state = state;
	}
	public Date getDateP() {
		return dateP;
	}
	public void setDateP(Date dateP) {
		this.dateP = dateP;
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
	public AmalWacelGhada getAc() {
		return ac;
	}
	public void setAc(AmalWacelGhada ac) {
		this.ac = ac;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id_t) {
		this.id = id_t;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDateT() {
		return dateT;
	}
	public void setDateT(Date dateT) {
		this.dateT = dateT;
	}
	public TypeT getType() {
		return type;
	}

	public void setType(TypeT type) {
		this.type = type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Transaction(Long id_t, double amount, Date dateT, Date dateP, TypeT type, mode mode,
			Long retard, int nb, StateT state, float total, float penalite, float argent, AmalWacelGhada ac) {
		super();
		this.id = id_t;
		this.amount = amount;
		this.dateT = dateT;
		this.dateP = dateP;
		this.type = type;
		this.mode = mode;
		this.retard = retard;
		this.nb = nb;
		this.state = state;
		this.total = total;
		this.penalite = penalite;
		this.argent = argent;
		this.ac = ac;
	}
	public Transaction() {
		super();
	}

	  public boolean hasState() {
	        return state != null && !"".equals(state.toString().trim());
	    }
	  public boolean hasType() {
	        return type != null && !"".equals(type.toString().trim());
	    }
	  public boolean hasDateT() {
	        return dateT != null && !"".equals(dateT.toString().trim());
	    }
	
	
}
