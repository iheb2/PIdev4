package tn.esprit.spring.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class Client extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "phnb")
	private String phnb;

	@Column(name = "cin")
	private String cin;

	@Column(name = "adress")
	private String adress;

	@Column(name = "first_name")
	private String first_name;

	@Column(name = "last_name")
	private String last_name;

	@Temporal(TemporalType.DATE)
	private Date date_of_birth;

	private LocalDate join_date = LocalDate.now();;
	@Column(name = "score")
	private Double score;

	@Column(name = "groupe")
	private String groupe;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Scores_id", referencedColumnName = "id")
	@JsonIgnore
	private AmalWacelGhada tabscore;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "clientNotif")
	@JsonIgnore
	private Set<Notification> notification;

	public Set<Notification> getNotification() {
		return notification;
	}

	public void setNotification(Set<Notification> notification) {
		this.notification = notification;
	}

	public LocalDate getJoin_date() {
		return join_date;
	}

	public void setJoin_date(LocalDate join_date) {
		this.join_date = join_date;
	}

	public String getPhnb() {
		return phnb;
	}

	public void setPhnb(String phnb) {
		this.phnb = phnb;
	}

	public AmalWacelGhada getTabscore() {
		return tabscore;
	}

	public void setTabscore(AmalWacelGhada tabscore) {
		this.tabscore = tabscore;
	}

	public Double getScore() {
		return score;
	}

	public String getGroupe() {
		return groupe;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Client() {
		super();
	}

	public Client(String username, String email, String password, String cin, String adress, String first_name,
			String last_name) {
		super(username, email, password);
		this.cin = cin;
		this.adress = adress;
		this.first_name = first_name;
		this.last_name = last_name;
	}

	public Client(String username, String email, String password, String cin, String adress, String first_name,
			String last_name, Date date_of_birth) {
		super(username, email, password);
		this.cin = cin;
		this.adress = adress;
		this.first_name = first_name;
		this.last_name = last_name;
		this.date_of_birth = date_of_birth;
	}

	public Long getId() {
		return super.getId();
	}

	public void setId(Long id) {
		super.setId(id);
	}

	public void setUsername(String username) {
		super.setUsername(username);
	}

	public void setEmail(String email) {
		super.setEmail(email);
	}

	public void setPassword(String password) {
		super.setPassword(password);

	}

	public String getUsername() {
		return super.getUsername();
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getEmail() {
		return super.getEmail();
	}

	public String getPassword() {
		return super.getPassword();
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public void setRoles(Set<Role> ROLE_USER) {
		super.setRoles(ROLE_USER);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((adress == null) ? 0 : adress.hashCode());
		result = prime * result + ((cin == null) ? 0 : cin.hashCode());
		result = prime * result + ((date_of_birth == null) ? 0 : date_of_birth.hashCode());
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (adress == null) {
			if (other.adress != null)
				return false;
		} else if (!adress.equals(other.adress))
			return false;
		if (cin == null) {
			if (other.cin != null)
				return false;
		} else if (!cin.equals(other.cin))
			return false;
		if (date_of_birth == null) {
			if (other.date_of_birth != null)
				return false;
		} else if (!date_of_birth.equals(other.date_of_birth))
			return false;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		return true;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	/*
	 * public Set<Question> getQuestion() { return question; }
	 * 
	 * public void setQuestion(Set<Question> question) { this.question =
	 * question; }
	 */

	/*
	 * public Set<Notification> getNotification() { return notification; }
	 * 
	 * public void setNotification(Set<Notification> notification) {
	 * this.notification = notification; }
	 */

}
