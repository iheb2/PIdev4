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
	
	public Notification (long id_notification, TypeNotification type_notification, Date send_date,String message ) {
		super();
		this.id_notification = id_notification;
		this.type_notification = type_notification;
		this.send_date = send_date;
		this.message = message;
		
	}
	public Notification ( TypeNotification type_notification, Date send_date,String message ) {
		super();
		this.type_notification = type_notification;
		this.send_date = send_date;
		this.message = message;
		
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
/*	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date_message == null) ? 0 : date_message.hashCode());
		result = prime * result + ((desc_message == null) ? 0 : desc_message.hashCode());
		result = prime * result + (int) (id_message ^ (id_message >>> 32));
		return result;
	}*/
	
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
