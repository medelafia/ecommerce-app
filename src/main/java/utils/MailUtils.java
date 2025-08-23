package utils;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;


public class MailUtils {
	
	
	
	public static void sendMail(String to , String content , String subject) throws AddressException, MessagingException { 
		String from = System.getenv("MAIL_FROM"); 
		String smtpServer = System.getenv("MAIL_SMTP_SERVER");
		String password =  System.getenv("MAIL_PASSWORD");
		String port =  System.getenv("MAIL_SMTP_PORT");
		
		Properties properties = new Properties() ; 
		
		

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", smtpServer) ; 
        properties.put("mail.smtp.port" , port ) ; 
        
        
        
        
		Session session = Session.getDefaultInstance(properties , 
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(from, password) ;
					};
					
				}
		) ; 
	
		MimeMessage mimeMessage = new MimeMessage(session) ; 
		
		mimeMessage.setFrom(new InternetAddress(from)) ; 
		mimeMessage.setSubject(subject);
		mimeMessage.setRecipients(RecipientType.TO, InternetAddress.parse(to, false) );
		mimeMessage.setText(content );
		
		
		Transport.send(mimeMessage); 
		
		
	}
}
