package in.ajayit.util;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {  //These all utils classes are used to perform excel and pdf report and send to email 

	private JavaMailSender mailSender;  //JavaMailSender is predefine class to send email 
	
	public boolean sendEmail(String subject, String body, String to, File f) {
		
		try {
			
			MimeMessage mimemsg = mailSender.createMimeMessage();  //MimeMessage is predefined class, send msg with attachment
			MimeMessageHelper helper = new MimeMessageHelper(mimemsg, true);  //construction injection
			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setTo(to);
			helper.addAttachment("Plans-Info", f);
			mailSender.send(mimemsg);  //there are 2 type of msg we can send 1) simple msg - plain text 2)Mime msg - msg with some attachment
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return true;
	}
}
