package com.github.adminfaces.starter.model;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="Dab")
public class Dab implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_dab;
	
	
	@Column(name="country")
	private String country;	
	
	@Column(name="adress")
	private String adress;
	
	@Column(name="city")
	private String city;
	
	private int max_day;
	
	private int lim_week;
	@ManyToOne
	private Agency agency ;
	

	public Dab() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId_dab() {
		return id_dab;
	}


	public void setId_dab(Long id_dab) {
		this.id_dab = id_dab;
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


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public int getMax_day() {
		return max_day;
	}


	public void setMax_day(int max_day) {
		this.max_day = max_day;
	}


	public int getLim_week() {
		return lim_week;
	}


	public void setLim_week(int lim_week) {
		this.lim_week = lim_week;
	}


	public Agency getAgency() {
		return agency;
	}


	public void setAgency(Agency agency) {
		this.agency = agency;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    public Dab country(String country) {
        this.country = country;
        return this;
    }

    public Dab city(String city) {
        this.city = city;
        return this;
    }
    
    public Dab adress(String adress) {
        this.adress = adress;
        return this;
    }
    
    public Dab lim_week(int lim_week) {
        this.lim_week = lim_week;
        return this;
    }
    
    public Dab max_day(int max_day) {
        this.max_day = max_day;
        return this;
    }
    
    String max_day_s = String.valueOf(max_day);
    String lim_week_s = String.valueOf(lim_week);


	@Override
	public String toString() {
		return super.toString();
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
    
    public boolean hasMax_Day() {
        return max_day_s != null && !"".equals(max_day_s.trim());
    }
    
    public boolean hasLim_Week() {
        return lim_week_s != null && !"".equals(lim_week_s.trim());
    }
    
    
}
