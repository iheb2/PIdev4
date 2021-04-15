package tn.esprit.pidev.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="RequestCreditCard")
public class RequestCreditCard extends Request implements Serializable  {
	
	private static final long serialVersionUID = 1L;


	
	private String cardtype;
	
	private int nb_card;	

	public RequestCreditCard() {
		super();
	}

	
	
public int getNb_Card() {
		return nb_card;
	}

	public void setNb_Card(int nb_card) {
		this.nb_card = nb_card;
	}

	public String getCardType() {
		return cardtype;
	}

	public void setCardType(String cardtype) {
		this.cardtype = cardtype;
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
