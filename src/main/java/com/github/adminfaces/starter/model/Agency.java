package com.github.adminfaces.starter.model;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonUnwrapped;

@Entity
@Table(name="Agency")
public class Agency implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_agency;
	
	private int nb_agent;
	
	@Column(name="email")
	private String email;
	
	@Column(name="country")
	private String country;	
	
	@Column(name="adress")
	private String adress;
	
	@Column(name="phone")
	private Long phone;
	
	@Column(name="city")
	private String city;

	@Temporal (TemporalType.DATE)
	private Date holidays;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Dab>  Dabs;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ratingid")
	@JsonUnwrapped
	private Rating rating;

	@Transient
	private List<Review> reviews;

	public Agency() {
		super();
		// TODO Auto-generated constructor stub
	}

	 public Long getId_agency() {
			return id_agency;
		}

		public void setId_agency(Long id_agency) {
			this.id_agency = id_agency;
		}

		public Set<Dab> getDabs() {
			return Dabs;
		}

		public void setDabs(Set<Dab> dabs) {
			Dabs = dabs;
		}

		public int getNb_agent() {
			return nb_agent;
		}

		public void setNb_agent(int nb_agent) {
			this.nb_agent = nb_agent;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getAdress() {
			return adress;
		}

		public void setAdress(String adress) {
			this.adress = adress;
		}

		public Long getPhone() {
			return phone;
		}

		public void setPhone(Long phone) {
			this.phone = phone;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public Date getHolidays() {
			return holidays;
		}

		public void setHolidays(Date holidays) {
			this.holidays = holidays;
		}
		

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public Rating getRating() {
			return rating;
		}

		public void setRating(Rating rating) {
			this.rating = rating;
		}

		public List<Review> getReviews() {
			return reviews;
		}

		public void setReviews(List<Review> reviews) {
			this.reviews = reviews;
		}

		
		@Override
		public int hashCode() {
			return super.hashCode();
		}

		String phone_s = String.valueOf(phone);
		String nb_agent_s = String.valueOf(nb_agent);
		
		@Override
		public boolean equals(Object obj) {
			return super.equals(obj);
		}

		public Agency email(String email) {
	        this.email = email;
	        return this;
	    }

	    public Agency country(String country) {
	        this.country = country;
	        return this;
	    }

	    public Agency city(String city) {
	        this.city = city;
	        return this;
	    }
	    
	    public Agency adress(String adress) {
	        this.adress = adress;
	        return this;
	    }
	    
	    public Agency phone(Long phone) {
	        this.phone = phone;
	        return this;
	    }
	    
	    public Agency nb_agent(int nb_agent) {
	        this.nb_agent = nb_agent;
	        return this;
	    }


		@Override
		public String toString() {
			return super.toString();
		}

		
		public boolean hasEmail() {
	        return email != null && !"".equals(email.trim());
	    }

	    public boolean hasCountry() {
	        return country != null && !"".equals(country.trim());
	    }
	    
	    public boolean hasCity() {
	        return city != null && !"".equals(city.trim());
	    }
	    
	    public boolean hasAdress() {
	        return adress != null && !"".equals(adress.trim());
	    }
	    
	    public boolean hasPhone() {
	        return phone_s != null && !"".equals(phone_s.trim());
	    }
	    
	    public boolean hasNb_agent() {
	        return nb_agent_s != null && !"".equals(nb_agent_s.trim());
	    }

	    

}
