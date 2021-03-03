package tn.esprit.spring.model;

import java.io.Serializable;


import javax.persistence.*;
@Entity
public class Account  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id1 ;

}
