package tn.esprit.spring.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.twilio.twiml.TwiMLException;
import tn.esprit.spring.models.Notification;
import tn.esprit.spring.models.TypeNotification;
import tn.esprit.spring.services.INotificationService;

@RestController
public class NotificationController {

	@Autowired
	INotificationService notificationService;

	@Scheduled(cron = "0 0 0 * * MON")
	@GetMapping("/NotificationSensibilisationMessages")
	@ResponseBody
	public String NotificationSensibilisationMessages() throws Exception {
		return notificationService.NotificationSensibilisationMessages();
	}

	@Scheduled(cron = "0 0 0 21 * *")
	@GetMapping("/NotificationLoanMaturityDate")
	@ResponseBody
	public void NotificationLoanMaturityDate() {

		notificationService.NotificationLoanMaturityDate();

	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")

	@GetMapping("/retrieve-all-Notifications")
	@ResponseBody
	public List<Notification> getNotifications() {
		List<Notification> list = notificationService.retrieveAllNotifications();
		return list;
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
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

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@DeleteMapping("/remove-Notification/{Notification-id}")
	@ResponseBody
	public void removeNotification(@PathVariable("Notification-id") Long id_Notification) {
		notificationService.deleteNotification(id_Notification);
		;
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@PutMapping("/modify-Notification")
	@ResponseBody
	public Notification modifyNotification(@RequestBody Notification c) {
		return notificationService.updateNotification(c);
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/retrieve-all-Notification-asc-date")
	@ResponseBody
	public List<Notification> orderByAscendingQantity() {
		List<Notification> list = notificationService.orderByAscendingSentDate();
		return list;
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/retrieve-all-Notification-desc-date")
	@ResponseBody
	public List<Notification> orderByDescendingQantity() {
		List<Notification> list = notificationService.orderByDescendingSentDate();
		return list;
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/search-Notification/{Notification-type}")
	@ResponseBody
	public List<Notification> SearchProductByName(
			@PathVariable("Notification-type") TypeNotification type_notification) {
		return notificationService.SearchNotificationByTypes(type_notification);
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/range/{min}/{max}")
	@ResponseBody
	public List<Notification> RangeProducts(@PathVariable("min") String min, @PathVariable("max") String max

	) {
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(min);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date date2 = null;
		try {
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(max);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return notificationService.Range(date1, date2);
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/NotificationCommunicationProducts")
	@ResponseBody
	public void NotificationCommunicationProducts() {
		notificationService.NotificationCommunicationProducts();
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/MeetInvitation/{meetSubject}/{date}")
	@ResponseBody
	public void MeetInvitation(@PathVariable("meetSubject") String meetSubject, @PathVariable("date") String date

	) {

		notificationService.MeetInvitation(meetSubject, date);
		;
	}

	@GetMapping("/NotificationAdviceMessages")
	@ResponseBody
	public String NotificationAdviceMessages() throws IOException, TwiMLException {

		return notificationService.NotificationAdviceMessages();

	}

}
