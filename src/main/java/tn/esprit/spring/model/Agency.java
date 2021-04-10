package tn.esprit.pidev.model;
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
		
}
