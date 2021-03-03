package tn.esprit.spring.model;



import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Person implements Serializable{
	
	//universal version identifier 
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_client ;
	
	@Column(name="cin")
	private String cin;
	
	@Column(name="email")
	private String email;
	
	@Column(name="adress")
	private String adress;
	
	@Column(name="first_name")
	private String first_name;

	@Temporal (TemporalType.DATE)
	private Date date_of_birth ;
	
	@Column(name="password_client")
	private String password_client ;

}
