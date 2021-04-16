package tn.esprit.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name="Report")
public class Report implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="Id")
	private long Id_R;
	@Column(name="DateR")
	@Temporal (TemporalType.DATE)
	private Date DateR;
	@Column(name="State")
	@Enumerated(EnumType.STRING)
	private StateR State;
	@Column(name="reciver")
	private String to;
	@Column(name="subject")
    private String subject;
	@Column(name="message")
    private String message;
	@Column(name="file")
    private String path;
	@Column(name="Date_Respond")
	@Temporal (TemporalType.DATE)
	private Date DateRe;
	@Column(name="respond")
    private String respond;
	@Column(name="sender")
	private String from;
	@OneToOne
	 private DBFile dbfile;	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonDeserialize
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private User client;
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	@Column(name="Treatment_Date")
	private long T;
    public long getT() {
		return T;
	}

	public void setT(long t) {
		T = t;
	}

	public Date getDateRe() {
		return DateRe;
	}

	public void setDateRe(Date dateRe) {
		DateRe = dateRe;
	}

	public String getRespond() {
		return respond;
	}

	public void setRespond(String respond) {
		this.respond = respond;
	}

	public long getId_R() {
		return Id_R;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setId_R(long id_R) {
		this.Id_R = id_R;
	}

	public Date getDateR() {
		return DateR;
	}

	public void setDateR(Date dateR) {
		this.DateR = dateR;
	}

	public StateR getState() {
		return State;
	}

	public void setState(StateR state) {
		this.State = state;
	}

	public String getTo() { return to; }

    public void setTo(String to) { this.to = to; }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "EmailRequest{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

	public DBFile getDbfile() {
		return dbfile;
	}

	public void setDbfile(DBFile dbfile) {
		this.dbfile = dbfile;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Report() {
		super();
	}

	public Report(long id_R, Date dateR, StateR state, String to, String subject, String message, String path,
			Date dateRe, String respond, String from, DBFile dbfile, long t) {
		Id_R = id_R;
		DateR = dateR;
		State = state;
		this.to = to;
		this.subject = subject;
		this.message = message;
		this.path = path;
		DateRe = dateRe;
		this.respond = respond;
		this.from = from;
		this.dbfile = dbfile;
		T = t;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
    
}
