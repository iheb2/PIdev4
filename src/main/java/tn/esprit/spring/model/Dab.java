package tn.esprit.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
@Entity
public class Dab  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id6 ;

}
