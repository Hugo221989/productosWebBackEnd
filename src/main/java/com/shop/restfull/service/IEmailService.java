package com.shop.restfull.service;

import org.thymeleaf.context.Context;

import com.shop.restfull.dto.Mail;

public interface IEmailService {

	 public void sendEmail(Mail mail, String idioma, String type);
	 
	 public String sendEmailToActivateUser(Mail mail, String idioma, Context context);
	 
	 
}
