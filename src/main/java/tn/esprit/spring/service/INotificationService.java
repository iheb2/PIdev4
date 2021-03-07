package tn.esprit.spring.service;


import java.util.List;
import tn.esprit.spring.model.Notification;


public interface INotificationService {

List<Notification> retrieveAllNotifications();
	
	Notification addNotification(Notification p);
	
	void deleteNotification(Long id_prod);
	
	Notification updateNotification(Notification p);
	
	 Notification  retrieveNotification(String id);
	
	
	
}
