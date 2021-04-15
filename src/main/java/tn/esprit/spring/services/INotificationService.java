package tn.esprit.spring.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.twilio.twiml.TwiMLException;

import tn.esprit.spring.models.Client;
import tn.esprit.spring.models.Notification;
import tn.esprit.spring.models.TypeNotification;
import tn.esprit.spring.repository.NotificationRepository;

public interface INotificationService {

	List<Notification> retrieveAllNotifications();

	Notification addNotification(Notification p);

	void deleteNotification(Long id_prod);

	Notification updateNotification(Notification p);

	Notification retrieveNotification(String id);

	public List<Notification> orderByAscendingSentDate();

	public List<Notification> orderByDescendingSentDate();

	List<Notification> SearchNotificationByTypes(TypeNotification type_notification);

	public void NotificationCommunicationProducts();

	public void MeetInvitation(String meetSubject, String date);

	public String NotificationSensibilisationMessages() throws IOException;

	public String NotificationAdviceMessages() throws IOException, TwiMLException;

	public List<Notification> Range(Date min, Date max);

	String NotificationLoanMaturityDate();
}
