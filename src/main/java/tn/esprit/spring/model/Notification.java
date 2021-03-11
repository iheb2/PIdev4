package tn.esprit.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
@Entity
public class Notification  implements Serializable {
	
	//universal version identifier 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_notification ;
	
	@Enumerated(EnumType.STRING)
	private TypeNotification type_notification ;

	@Temporal (TemporalType.DATE)
	private Date send_date ;
	
	@Column(name="message")
	private String message ;
	
	@ManyToOne 
	Client clientNotif;
	
	
	public Notification() {
		super();
	}
	
	public long getId_notification() {
		return id_notification;
	}

	public TypeNotification getType_notification() {
		return type_notification;
	}

	public void setType_notification(TypeNotification type_notification) {
		this.type_notification = type_notification;
	}
	
	
	public Date getSend_date() {
		return send_date;
	}

	public void setSend_date(Date send_date) {
		this.send_date = send_date;
	}

	public String getMessage() {
		return message;
	}

	public void setDesc_message(String desc_message) {
		this.message = desc_message;
	}

	public Client getClientNotif() {
		return clientNotif;
	}

	public void setClientNotif(Client clientNotif) {
		this.clientNotif = clientNotif;
	}

	public void setId_notification(long id_notification) {
		this.id_notification = id_notification;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notification other = (Notification) obj;
		if (send_date == null) {
			if (other.send_date != null)
				return false;
		} else if (!send_date.equals(other.send_date))
			return false;
		if (send_date == null) {
			if (other.send_date != null)
				return false;
		} else if (!send_date.equals(other.send_date))
			return false;
		if (id_notification != other.id_notification)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Notification [id_notification=" + id_notification + ", type_notification=" + type_notification + ", message=" + message + ", send_date=" + send_date
				+ "]";
	}


	
	
	
}
