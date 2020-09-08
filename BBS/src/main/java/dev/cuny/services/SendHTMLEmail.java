package dev.cuny.services;
 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SendHTMLEmail {
 
    public static void sendHtmlEmail(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message) throws AddressException,
            MessagingException {
 
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        // set plain text message
        msg.setContent(message, "text/html");
        // sends the e-mail
        Transport.send(msg);
 
    }
    public static String parseFile(String path) throws IOException {
    	try {
    		BufferedReader bf = new BufferedReader(new FileReader(path));
    		StringBuilder sb = new StringBuilder();
    		String line = null;
    		String ls = System.getProperty("line.separator");
    		while((line=bf.readLine()) != null){
    			sb.append(line);
    			sb.append(ls);
    		}
    		sb.deleteCharAt(sb.length()-1);
    		bf.close();
    		return sb.toString();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
}