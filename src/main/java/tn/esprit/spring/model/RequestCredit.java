package tn.esprit.pidev.model;

import java.io.Serializable;


import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="RequestCredit")
public class RequestCredit extends Request implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	@Column(name="state")
	private int state;
	
	@Column(name="amount")
	private double amount;	

	public RequestCredit() {
		super();
	}


	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}


	@Override
	public int hashCode() {
		return super.hashCode();
	}



	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}



	@Override
	public String toString() {
		return super.toString();
	}

}
