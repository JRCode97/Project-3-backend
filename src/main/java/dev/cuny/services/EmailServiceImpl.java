package dev.cuny.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.cuny.entities.EmailUser;

@Component
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	JavaMailSender javamailsender;

	@Override
	public EmailUser createEmail(EmailUser user) {

		SimpleMailMessage mail = new SimpleMailMessage();

		mail.setTo("superslicepizzeria@gmail.com");

		mail.setText("email sent.." + user.getEmailAddress());

		mail.setSubject("HelloSubject");
		javamailsender.send(mail);
		return user;
	}

	@Override
	public EmailUser ResetClientPassword(String email) {
		
		EmailUser u = new EmailUser();
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setSubject("[PassWord Reset]");
		mail.setText("This is your temporary password:  " + String.valueOf(getRandomPass(10)));
		javamailsender.send(mail);

		return u;

	}

	private char[] getRandomPass(double random) {
		String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String Small_chars = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		String symbols = "!@#$%^&*_=+-/.?<>)";
		String values = Capital_chars + Small_chars + numbers + symbols;

		Random rndm_method = new Random();

		char[] password = new char[10];

		for (int i = 0; i < 10; i++) {
			password[i] = values.charAt(rndm_method.nextInt(values.length()));

		}
		return password;

	}

}
