package tn.esprit.spring.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.model.Notification;
import tn.esprit.spring.service.INotificationService;

@RestController
public class NotificationController {

	@Autowired
	INotificationService notificationService;
	
			@GetMapping("/retrieve-all-Notifications")
			@ResponseBody
			public List<Notification> getNotifications() {
				List<Notification> list = notificationService.retrieveAllNotifications();
				return list;
			}
			
			@GetMapping("/retrieve-Notification/{Notification-id}")
			@ResponseBody
			public Notification retrieveNotification(@PathVariable("Notification-id") String id_client) {
				return notificationService.retrieveNotification(id_client);
			}	
			
			@PostMapping("/add-Notification")
			@ResponseBody
			public Notification addNotification(@RequestBody Notification c) {
				Notification Notification = notificationService.addNotification(c);
				return Notification;
			}
			
				@DeleteMapping("/remove-Notification/{Notification-id}")
				@ResponseBody
				public void removeNotification (@PathVariable("Notification-id") Long id_Notification) {
					notificationService.deleteNotification(id_Notification);;
				}
				
				@PutMapping("/modify-Notification")
				@ResponseBody
				public Notification modifyNotification(@RequestBody Notification c) {
					return notificationService.updateNotification(c);
				}

				
}
