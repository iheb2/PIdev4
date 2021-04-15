package tn.esprit.spring.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.spring.models.*;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.ConfirmationTokenRepository;
import tn.esprit.spring.repository.RoleRepository;
import tn.esprit.spring.repository.ScoreRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.security.jwt.JwtUtils;

@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	ClientRepository ClientRepository;

	@Autowired
	UserServices ur;

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	ScoreRepository SR;

	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	private MailingServiceForToken emailSenderService;

	@Override
	public List<Client> retrieveAllClients() {
		return (List<Client>) ClientRepository.findAll();
	}

	@Override
	public String addClient(Client p) {
		p.setEnabled(false);
		p.setScore(0.0);
		p.setPassword(encoder.encode(p.getPassword()));

		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);
		p.setRoles(roles);
		ConfirmationToken confirmationToken = new ConfirmationToken(p);

		confirmationTokenRepository.save(confirmationToken);

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(p.getEmail());
		mailMessage.setSubject("Complete Registration!");
		mailMessage.setFrom("iheb.amri@esprit.tn");
		mailMessage.setText("To confirm your account, please click here : "
				+ "http://localhost:8080/api/auth/confirm-account?token=" + confirmationToken.getConfirmationToken());
		emailSenderService.sendEmail(mailMessage);
		return "please check your mail to complete registration";

	}

	@Override
	public void deleteClient(Long id) {
		ClientRepository.deleteById((long) id);
	}

	@Override
	public Client updateClient(Client p) {
		return ClientRepository.save(p);
	}

	@Override
	public Client retrieveClient(Long id) {
		return ClientRepository.findById(id).orElse(null);
	}

	@Override
	public List<Client> retrieveClientByScore(Double score) {
		return ClientRepository.SearchClientByScore(score);
	}

	@Override
	public List<Client> SearchClientByName(String cin) {

		return ClientRepository.SearchClientByName(cin);

	}

	@Override
	public Double CalculScore(long id) {

		Client u = retrieveClient(id);
		Double c = 0.0;
		AmalWacelGhada score = u.getTabscore();
		Double sm = score.getMonthRedRate();

		if (sm == 0) {
			c += 20;
		} else if (sm > 0 && sm <= 0.5) {
			c += 10;

		} else if (sm > 0.5 && sm <= 1) {
			c += 0;

		} else
			c -= 10;

		Double tr = score.getTransactionsRate();
		if (tr >= 0 && tr <= 0.5) {
			c += 20;

		} else if (tr > 0.5 && tr <= 1) {
			c += 10;

		} else
			c += 0;
		Integer nb = score.getNbCeditPaye();

		if (nb == 0) {
			c += 0;

		} else if (nb >= 1 && nb <= 2) {
			c += 10;

		} else
			c += 20;
		LocalDate now = LocalDate.now();

		long years = java.time.temporal.ChronoUnit.YEARS.between(u.getJoin_date(), now);
		if (years == 0) {
			c += 0;

		} else if (years >= 1 && years <= 2) {
			c += 10;

		} else
			c += 20;
		return c;

	}

	@Override
	public void CalculScoreAndAffectToUclients() {

		for (Client c : retrieveAllClients()) {
			c.setScore(CalculScore(c.getId()));
			ClientRepository.save(c);
		}

	}
}
