package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.model.Notification;
import tn.esprit.spring.repository.NotificationRepository;;



@Service
public class NotificationtServiceImpl implements INotificationService{

	@Autowired
	NotificationRepository NotificationRepository ;

	@Override
	public List<Notification> retrieveAllNotifications() {
		return (List<Notification>) NotificationRepository.findAll();
	}

	@Override
	public Notification addNotification(Notification p) {
		return NotificationRepository.save(p);
	}

	@Override
	public void deleteNotification(Long id) {
		NotificationRepository.deleteById((long) id);
	}

	@Override
	public Notification updateNotification(Notification p) {
		return NotificationRepository.save(p);
	}

	@Override
	public Notification retrieveNotification(String id) {
		return NotificationRepository.findById(Long.parseLong(id)).orElse(null);
	}
	

	
}
