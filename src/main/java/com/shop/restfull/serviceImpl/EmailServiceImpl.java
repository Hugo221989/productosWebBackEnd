package com.shop.restfull.serviceImpl;

import java.nio.charset.StandardCharsets;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.shop.restfull.dto.Mail;
import com.shop.restfull.service.IEmailService;

@Service
public class EmailServiceImpl implements IEmailService {
	
	@Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

	@Override
	public void sendEmail(Mail mail, String idioma, String type) {
		 try {
    		 MimeMessage message = emailSender.createMimeMessage();
             MimeMessageHelper helper = new MimeMessageHelper(message,
                     MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                     StandardCharsets.UTF_8.name());
             Context context = new Context();
             context.setVariables(mail.getModel());
             String html = "";
             if(type.equals("activate"))html = this.sendEmailToActivateUser(mail, idioma, context);
             
             helper.setTo(mail.getTo());
             helper.setText(html, true);
             helper.setSubject(mail.getSubject());
             helper.setFrom(mail.getFrom());        
             emailSender.send(message);
    	 } catch (Exception e){
             throw new RuntimeException(e);
    	 }
	}

	@Override
	public String sendEmailToActivateUser(Mail mail, String idioma, Context context) {
		String html = templateEngine.process("email/emailActivarUser/email-template_act_user", context);
       if(null != idioma && idioma.equals("en"))
        	html = templateEngine.process("email/emailActivarUser/email-template_act_user_en", context);
        return html;
	}

}
