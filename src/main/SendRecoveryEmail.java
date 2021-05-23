package main;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendRecoveryEmail {
	// create verification code for user
	public String getRandom() {
		Random number = new Random();
		int code = number.nextInt(999999);
		
		return String.format("%06d", code);
	}
	
	// send code to user's email
	public boolean sendEmail(users.Users user) {
		boolean verified = false;
		
		String toEmail = user.getEmail();
		String fromEmail = "nebulakesupport@nebulake.com";
		String password = "speAkfriendAndenter#64";
		
		try {
			// Host SMTP Server details
			Properties server = new Properties();
			
			server.setProperty("mail.smtp.host", "smtp.ionos.com");
			server.setProperty("mail.smtp.port", "587");
			server.setProperty("mail.smtp.auth", "true");
			server.setProperty("mail.smtp.starttls.enable", "true");
			server.put("mail.smtp.socketFactory.port", "587");
			server.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
 
            //get session to authenticate the host email address and password
            Session session = Session.getInstance(server, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            //set email message details
            Message mess = new MimeMessage(session);

    		//set from email address
            mess.setFrom(new InternetAddress(fromEmail));
            
    		//set to email address or destination email address
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
    		
    		//set email subject
            mess.setSubject("User Password Recovery");
            
    		//set message text
            mess.setText("To Recover your account, Please verify your account using this code: " + user.getVerification());
            
            //send the message
            Transport.send(mess);
            
            verified = true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return verified;
    }

		
	
}
