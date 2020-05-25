package dev.cuny.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.cuny.app.BBSClient;

import dev.cuny.entities.Client;
import dev.cuny.entities.EmailUser;

@Component
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	JavaMailSender javamailsender;

	@Autowired
	BBSClient BBSclient;

	@Override
	public Client ResetClientPassword(String email) {

		// Get a client by their email
		Client c = BBSclient.getClientByEmail(email);
		System.out.println(c);
		System.out.println("hello");

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		String newpass = String.valueOf(getRandomPass(10));
		mail.setSubject("[PassWord Reset]");
		mail.setText("This is your temporary password:  " + newpass);
		try {
			javamailsender.send(mail);

			c.setPassword(newpass);
			BBSclient.updateClient(c);

			return c;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

		

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
