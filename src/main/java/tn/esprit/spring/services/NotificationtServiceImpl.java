package tn.esprit.spring.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.twiml.Say;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import com.twilio.type.PhoneNumber;

import tn.esprit.spring.models.*;
import tn.esprit.spring.models.Client;
import tn.esprit.spring.models.Notification;
import tn.esprit.spring.models.TypeNotification;
import tn.esprit.spring.repository.NotificationRepository;
import tn.esprit.spring.security.SmsPojo;
import tn.esprit.spring.security.SmsService;

@Service
public class NotificationtServiceImpl implements INotificationService {
	@Autowired
	SmsService service;
	@Autowired
	IClientService cs;

	@Autowired
	private MailingServiceForToken emailSenderService;

	@Autowired
	private SimpMessagingTemplate webSocket;

	@Autowired
	NotificationRepository NotificationRepository;

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

	@Override
	public List<Notification> orderByAscendingSentDate() {
		return NotificationRepository.orderByAscendingSentDate();
	}

	@Override
	public List<Notification> orderByDescendingSentDate() {
		return NotificationRepository.orderByDescendingSentDate();
	}

	@Override
	public List<Notification> SearchNotificationByTypes(TypeNotification type_notification) {

		return NotificationRepository.SearchNotificationByType(type_notification);

	}

	@Override
	public List<Notification> Range(Date min, Date max) {
		return NotificationRepository.Range(min, max);
	}

	@Override
	public void NotificationCommunicationProducts() {
		SmsPojo sms = new SmsPojo();
		String msg = "Hi \n we want  share with you that new financial product    that you might be interested thank you for consulting our site for more details  ";
		for (Client c : cs.retrieveAllClients()) {
			if (c.getGroupe().equals("Groupe1")) {
				sms.setMessage(msg);
				sms.setTo(c.getPhnb());
				service.send(sms);
				Notification notif = new Notification();
				notif.setType_notification(TypeNotification.CommunicationProducts);
				notif.setMessage(msg);
				notif.setsenton("Sms");
				notif.setClientNotif(c);
				NotificationRepository.save(notif);
			}
		}

	}

	public String getline(int n) throws IOException {

		String line = Files.readAllLines(Paths.get("C://fcihiers//SensibilisationMessages.txt")).get(n);
		return line;
	}

	@Override
	public String NotificationSensibilisationMessages() throws IOException {
		Integer r = 0;
		SmsPojo sms = new SmsPojo();
		File file = new File("C://fcihiers//SensibilisationMessages.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		while ((br.readLine()) != null) {
			r++;
		}
		Random rand = new Random();
		int upperbound = r;
		int int_random = rand.nextInt(upperbound);
		if (int_random == 0) {
			int_random += 1;
		}
		String line = Files.readAllLines(Paths.get("C://fcihiers//SensibilisationMessages.txt")).get(int_random);
		sms.setMessage(line);

		for (Client c : cs.retrieveAllClients()) {
			sms.setTo(c.getPhnb());
			service.send(sms);
			Notification notif = new Notification();
			notif.setType_notification(TypeNotification.SensibilisationMessages);
			notif.setMessage(line);
			notif.setsenton("Sms");
			notif.setClientNotif(c);
			NotificationRepository.save(notif);
		}

		return "sent successfully";

	}

	@Override
	public void MeetInvitation(String meetSubject, String date) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setSubject("Invitation for meeting");
		mailMessage.setFrom("iheb.amri@esprit.tn");
		for (Client c : cs.retrieveAllClients()) {
			if (c.getGroupe().equals("Groupe1")) {
				String msg = "Hi " + c.getFirst_name() + " " + c.getLast_name() + "\n"
						+ "we want to invite u to owr meeting on  " + date + " , wich about " + meetSubject;
				mailMessage.setTo(c.getEmail());
				mailMessage.setText(msg);
				emailSenderService.sendEmail(mailMessage);
				Notification notif = new Notification();
				notif.setType_notification(TypeNotification.MeetInvitation);
				notif.setMessage(msg);
				notif.setsenton("Email");
				notif.setClientNotif(c);
				NotificationRepository.save(notif);
			}
		}

	}

	@Override
	public String NotificationAdviceMessages() throws IOException, TwiMLException {
		Integer r = 0;
		File file = new File("C://fcihiers//AdviceMessages.txt");
		FileReader fr = new FileReader(file); // reads the file
		BufferedReader br = new BufferedReader(fr);
		while ((br.readLine()) != null) {
			r++;
		}
		Random rand = new Random();
		int upperbound = r;
		int int_random = rand.nextInt(upperbound);
		if (int_random == 0) {
			int_random += 1;
		}
		String line = Files.readAllLines(Paths.get("C://fcihiers//AdviceMessages.txt")).get(int_random);
		for (Client c : cs.retrieveAllClients()) {
			PhoneNumber to_number = new PhoneNumber(c.getPhnb());
			getVoiceNote(line);
			makeCall(to_number);
			Notification notif = new Notification();
			notif.setType_notification(TypeNotification.AdviceMessages);
			notif.setMessage(line);
			notif.setsenton("Voice-note");
			notif.setClientNotif(c);
			NotificationRepository.save(notif);
		}
		br.close();

		return "voice note  running successfully ..";

	}

	@Override
	public String NotificationLoanMaturityDate() {
		for (Client c : cs.retrieveAllClients()) {
			AmalWacelGhada cc = c.getTabscore();
			List<Transaction> listTransaction = cc.getTransaction();
			List<Transaction> listAPaye = new ArrayList<>();

			SmsPojo sms = new SmsPojo();
			for (Transaction tt : listTransaction) {
				if (tt.getType() == TypeT.Credit && tt.getState() == StateT.non) {
					listAPaye.add(tt);
				}

			}
			Date date = cc.getAttribDate();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = dateFormat.format(date);
			String msg = "hi " + c.getFirst_name() + " " + c.getLast_name() + " \n" + "your credit in owr bank is "
					+ cc.getMontantCredit() + " tooked on " + strDate + " to pay on " + cc.getNbmois()
					+ " Months \n We want to remind  that you have to pay " + listAPaye.get(0).getAmount() + " dt "
					+ " before " + listAPaye.get(0).getDateP().toString();

			sms.setMessage(msg);
			sms.setTo(c.getPhnb());
			service.send(sms);
			Notification notif = new Notification();
			notif.setType_notification(TypeNotification.LoanMaturityDate);
			notif.setMessage(msg);
			notif.setsenton("Sms");
			notif.setClientNotif(c);
			NotificationRepository.save(notif);

		}

		return "sent successfully";
	}

	public static final String ACCOUNT_SID = "AC90a519e1fd0175da181b563c2ac46c03";
	public static final String AUTH_TOKEN = "0644fe5111d4dfe31cea4aa4637c44f2";

	public ResponseEntity<Object> getVoiceNote(String op) throws TwiMLException {
		String otp = op;
		Say say = new Say.Builder("Your OTP is: " + otp).voice(Say.Voice.WOMAN).build();
		VoiceResponse response = new VoiceResponse.Builder().say(say).build();
		return new ResponseEntity<>(response.toXml(), HttpStatus.OK);
	}

	public ResponseEntity<Object> makeCall(PhoneNumber to_number) {

		TwilioRestClient client = new TwilioRestClient.Builder(ACCOUNT_SID, AUTH_TOKEN).build();
		PhoneNumber from_number = new PhoneNumber("+14422465096");
		URI uri = URI.create("https://talk2amar-projectid.appspot.com/voice-note?otp=345678");
		Call call = Call.creator(to_number, from_number, uri).create(client);
		return new ResponseEntity<Object>("Call has initiated successfully and call SID is:" + call.getSid(),
				HttpStatus.OK);
	}
}
