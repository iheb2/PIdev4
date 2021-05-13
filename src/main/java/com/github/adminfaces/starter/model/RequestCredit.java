package com.github.adminfaces.starter.model;

import java.io.Serializable;


import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="RequestCredit")
public class RequestCredit extends Request implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="months")
	private double months;
	
	@Column(name="total")
	private double total;
	
	@Column(name="interest")
	private double interest;
	
	@Column(name="monthly_payment")
	private double monthly_payment;
	
	public RequestCredit() {
		super();
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public double getMonths() {
		return months;
	}

	public void setMonths(double months) {
		this.months = months;
	}
	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}
	
	public double getMonthly_payment() {
		return monthly_payment;
	}

	public void setMonthly_payment(double monthly_payment) {
		this.monthly_payment = monthly_payment;
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

	String amount_s = String.valueOf(amount);
	String months_s = String.valueOf(months);
	
	public RequestCredit amount(double amount) {
        this.amount = amount;
        return this;
    }

    public Request months(double months) {
        this.months = months;
        return this;
    }


    public boolean hasAmount() {
        return amount_s != null && !"".equals(amount_s.trim());
    }
    
    public boolean hasMonths() {
        return months_s != null && !"".equals(months_s.trim());
    }
   
}
