package tn.esprit.pidev.model;

import java.io.Serializable;


import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="RequestCheckbook")
public class RequestCheckbook extends Request implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	
	private int nb_page;

	public RequestCheckbook() {
		super();
	}


	
	

	public int getNb_page() {
		return nb_page;
	}

	public void setNb_page(int nb_page) {
		this.nb_page = nb_page;
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
