package com.github.adminfaces.starter.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
@Entity
public class Product  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id7;


}
