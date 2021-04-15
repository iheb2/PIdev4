package tn.esprit.pidev.model;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.OneToMany;


@Entity
public class Client  implements Serializable 	{

	//universal version identifier
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id_client;
	
	@Column(name="cin")
	private String cin;
	
	@Column(name="email")
	private String email;
	
	@Column(name="adress")
	private String adress;
	
	@Column(name="first_name")
	private String first_name;
	
	@Column(name="last_name")
	private String last_name;

	@Temporal (TemporalType.DATE)
	private Date date_of_birth ;
	
	@Column(name="password_client")
	private String password_client ;
    @OneToMany(cascade = CascadeType.ALL)
	private Set<Request> requests;
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(int id_client) {
		super();
		this.id_client = id_client;
	}

	private int score; 
	
	


	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword_client() {
		return password_client;
	}

	public void setPassword_client(String password_client) {
		this.password_client = password_client;
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

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}










	
}
