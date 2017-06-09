package com.nuance.qa.tool;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class QatoolEmailServiceImpl {

	@Autowired
	JavaMailSender mailSender;
	
	public void sendSimpleEmail() throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		helper.setFrom("cse63152@gmail.com");
		helper.setTo("yc_whj@hotmail.com");
		helper.setSubject("New message from me");
		helper.setText("He says: How are you doing");
		
		FileSystemResource couponImage = new FileSystemResource("c:\\la.jpg");
		helper.addAttachment("Coupon.png", couponImage);
		
		mailSender.send(message);
	}
	
}
