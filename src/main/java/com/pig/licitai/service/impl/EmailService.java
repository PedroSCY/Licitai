package com.pig.licitai.service.impl;


import org.hibernate.context.TenantIdentifierMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.pig.licitai.exceptions.RegraDeNegocioException;
import com.pig.licitai.model.entity.EmailModel;
import com.pig.licitai.model.enuns.StatusEmail;
import com.pig.licitai.model.repository.EmailRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Service
public class EmailService {
	
    @Autowired
    private EmailRepository repository;

    @Autowired
    private JavaMailSender emailSender;

    public EmailModel sendEmail(EmailModel emailModel) {
        emailModel.setSendDataEmail(LocalDateTime.now());
        
        String conteudoEmail = ConverterEmail(emailModel);
        try {
        	
        	 MimeMessage message = emailSender.createMimeMessage();

             MimeMessageHelper helper;
             helper = new MimeMessageHelper(message, true, "UTF-8");
             helper.setFrom(emailModel.getEmailFrom());
             helper.setTo(emailModel.getEmailTo());
             helper.setSubject(emailModel.getSubject());
             helper.setText(conteudoEmail, true);
             helper.addInline("LicitaiLogo", new ClassPathResource("images/LogoLicitAi.png"));
             helper.addInline("LicitaiLogoCrop", new ClassPathResource("images/LogoCrop.png"));
             helper.addInline("iconeTwitter", new ClassPathResource("images/image-1.png"));
             helper.addInline("iconeLinkedin", new ClassPathResource("images/image-2.png"));
             helper.addInline("iconeInstagram", new ClassPathResource("images/image-3.png"));

            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);

        }catch (MailException e){
        	System.out.println(e.getMessage());
            emailModel.setStatusEmail(StatusEmail.ERROR);

        }finally {
        	emailModel.setResolut(emailModel.getResolut() + 1);
            return repository.save(emailModel);
        }
    }
    
    public void deletarPorId(EmailModel email) {
    	Optional<EmailModel> emailFix = repository.findById(email.getEmailId());
    	if(emailFix.isPresent()) {
    		repository.deleteById(email.getEmailId());
    		return;
    	}
    	
    	throw new RegraDeNegocioException("Email não encontrado");
    	
    }
    
    public ArrayList<EmailModel> obterPorStatus(int status){
    	return repository.findByStatusEmail(status);
    }
    
    public Page<EmailModel> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }
    
    private String ConverterEmail(EmailModel email) {
  
    	return "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
          		"<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\r\n" + 
          		"<head>\r\n" + 
          		"  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n" + 
          		"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
          		"  <meta name=\"x-apple-disable-message-reformatting\">\r\n" + 
          		"  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
          		"  <title></title>\r\n" + 
          		"    <style type=\"text/css\">\r\n" + 
          		"      @media only screen and (min-width: 570px) {\r\n" + 
          		"  .u-row {\r\n" + 
          		"    width: 550px !important;\r\n" + 
          		"  }\r\n" + 
          		"  .u-row .u-col {\r\n" + 
          		"    vertical-align: top;\r\n" + 
          		"  }\r\n" + 
          		"  .u-row .u-col-100 {\r\n" + 
          		"    width: 550px !important;\r\n" + 
          		"  }\r\n" + 
          		"}\r\n" + 
          		"@media (max-width: 570px) {\r\n" + 
          		"  .u-row-container {\r\n" + 
          		"    max-width: 100% !important;\r\n" + 
          		"    padding-left: 0px !important;\r\n" + 
          		"    padding-right: 0px !important;\r\n" + 
          		"  }\r\n" + 
          		"  .u-row .u-col {\r\n" + 
          		"    min-width: 320px !important;\r\n" + 
          		"    max-width: 100% !important;\r\n" + 
          		"    display: block !important;\r\n" + 
          		"  }\r\n" + 
          		"  .u-row {\r\n" + 
          		"    width: calc(100% - 40px) !important;\r\n" + 
          		"  }\r\n" + 
          		"  .u-col {\r\n" + 
          		"    width: 100% !important;\r\n" + 
          		"  }\r\n" + 
          		"  .u-col > div {\r\n" + 
          		"    margin: 0 auto;\r\n" + 
          		"  }\r\n" + 
          		"}\r\n" + 
          		"body {\r\n" + 
          		"  margin: 0;\r\n" + 
          		"  padding: 0;\r\n" + 
          		"}\r\n" + 
          		"\r\n" + 
          		"table,\r\n" + 
          		"tr,\r\n" + 
          		"td {\r\n" + 
          		"  vertical-align: top;\r\n" + 
          		"  border-collapse: collapse;\r\n" + 
          		"}\r\n" + 
          		"p {\r\n" + 
          		"  margin: 0;\r\n" + 
          		"}\r\n" + 
          		".ie-container table,\r\n" + 
          		".mso-container table {\r\n" + 
          		"  table-layout: fixed;\r\n" + 
          		"}\r\n" + 
          		"\r\n" + 
          		"* {\r\n" + 
          		"  line-height: inherit;\r\n" + 
          		"}\r\n" + 
          		"\r\n" + 
          		"a[x-apple-data-detectors='true'] {\r\n" + 
          		"  color: inherit !important;\r\n" + 
          		"  text-decoration: none !important;\r\n" + 
          		"}\r\n" + 
          		"\r\n" + 
          		"table, td { color: #000000; } #u_body a { color: #3598db; text-decoration: underline; }\r\n" + 
          		"    </style>\r\n" + 
          		"  \r\n" + 
          		"  \r\n" + 
          		"<link href=\"https://fonts.googleapis.com/css?family=Lato:400,700&display=swap\" rel=\"stylesheet\" type=\"text/css\"><link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,700&display=swap\" rel=\"stylesheet\" type=\"text/css\">\r\n" + 
          		"\r\n" + 
          		"</head>\r\n" + 
          		"\r\n" + 
          		"<body class=\"clean-body u_body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #293c4b;color: #000000\">\r\n" + 
          		"\r\n" + 
          		"  <table id=\"u_body\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #293c4b;width:100%\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
          		"  <tbody>\r\n" + 
          		"  <tr style=\"vertical-align: top\">\r\n" + 
          		"    <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\r\n" + 
          		"    \r\n" + 
          		"\r\n" + 
          		"<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\r\n" + 
          		"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 550px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\r\n" + 
          		"    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\r\n" + 
          		"\r\n" + 
          		"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 550px;display: table-cell;vertical-align: top;\">\r\n" + 
          		"  <div style=\"height: 100%;width: 100% !important;\">\r\n" + 
          		"<div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\r\n" + 
          		"  \r\n" + 
          		"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
          		"  <tbody>\r\n" + 
          		"    <tr>\r\n" + 
          		"      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:20px 10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n" + 
          		"        \r\n" + 
          		"<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
          		"  <tr>\r\n" + 
          		"    <td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\r\n" + 
          		"      <a href=\"https://unlayer.com\" target=\"_blank\">\r\n" + 
          		"      <img align=\"center\" border=\"0\" src='cid:LicitaiLogo' alt=\"Logo\" title=\"Logo\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 35%;max-width: 185.5px;\" width=\"185.5\"/>\r\n" + 
          		"      </a>\r\n" + 
          		"    </td>\r\n" + 
          		"  </tr>\r\n" + 
          		"</table>\r\n" + 
          		"\r\n" + 
          		"      </td>\r\n" + 
          		"    </tr>\r\n" + 
          		"  </tbody>\r\n" + 
          		"</table>\r\n" + 
          		"\r\n" + 
          		"  </div>\r\n" + 
          		"  </div>\r\n" + 
          		"</div>\r\n" + 
          		"\r\n" + 
          		"    </div>\r\n" + 
          		"  </div>\r\n" + 
          		"</div>\r\n" + 
          		"\r\n" + 
          		"<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\r\n" + 
          		"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 550px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #169179;\">\r\n" + 
          		"    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\r\n" + 
          		"\r\n" + 
          		"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 550px;display: table-cell;vertical-align: top;\">\r\n" + 
          		"  <div style=\"height: 100%;width: 100% !important;\">\r\n" + 
          		"  <div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\r\n" + 
          		"  \r\n" + 
          		"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
          		"  <tbody>\r\n" + 
          		"    <tr>\r\n" + 
          		"      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:30px 10px 0px 15px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n" + 
          		"        \r\n" + 
          		"  <h3 style=\"margin: 0px; color: #ffffff; line-height: 140%; text-align: center; word-wrap: break-word; font-weight: normal; font-family: 'Montserrat',sans-serif; font-size: 23px;\">\r\n" + 
          		"    ANUNCIO DE OPORTUNIDADE\r\n" + 
          		"  </h3>\r\n" + 
          		"\r\n" + 
          		"      </td>\r\n" + 
          		"    </tr>\r\n" + 
          		"  </tbody>\r\n" + 
          		"</table>\r\n" + 
          		"\r\n" + 
          		"  </div>\r\n" + 
          		"  </div>\r\n" + 
          		"</div>\r\n" + 
          		"\r\n" + 
          		"    </div>\r\n" + 
          		"  </div>\r\n" + 
          		"</div>\r\n" + 
          		"\r\n" + 
          		"<div class=\"u-row-container\" style=\"padding: 0px;background-image: url('%20');background-repeat: no-repeat;background-position: center top;background-color: transparent\">\r\n" + 
          		"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 550px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #169179;\">\r\n" + 
          		"    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\r\n" + 
          		"      \r\n" + 
          		"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 550px;display: table-cell;vertical-align: top;\">\r\n" + 
          		"  <div style=\"height: 100%;width: 100% !important;\">\r\n" + 
          		" <div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\r\n" + 
          		"  \r\n" + 
          		"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
          		"  <tbody>\r\n" + 
          		"    <tr>\r\n" + 
          		"      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:0px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n" + 
          		"        \r\n" + 
          		"<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n" + 
          		"  <tr>\r\n" + 
          		"    <td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\r\n" + 
          		"      \r\n" + 
          		"      <img align=\"center\" border=\"0\" src='cid:LicitaiLogoCrop' alt=\"Calendar\" title=\"Calendar\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 100%;max-width: 550px;\" width=\"550\"/>\r\n" + 
          		"      \r\n" + 
          		"    </td>\r\n" + 
          		"  </tr>\r\n" + 
          		"</table>\r\n" + 
          		"\r\n" + 
          		"      </td>\r\n" + 
          		"    </tr>\r\n" + 
          		"  </tbody>\r\n" + 
          		"</table>\r\n" + 
          		"\r\n" + 
          		"  </div>\r\n" + 
          		"  </div>\r\n" + 
          		"</div>\r\n" + 
          		"    </div>\r\n" + 
          		"  </div>\r\n" + 
          		"</div>\r\n" + 
          		"\r\n" + 
          		"<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\r\n" + 
          		"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 550px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\r\n" + 
          		"    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\r\n" + 
          		"\r\n" + 
          		"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 550px;display: table-cell;vertical-align: top;\">\r\n" + 
          		"  <div style=\"height: 100%;width: 100% !important;\">\r\n" + 
          		"  <div style=\"padding: 0px 20px 20px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\r\n" + 
          		"  \r\n" + 
          		"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
          		"  <tbody>\r\n" + 
          		"    <tr>\r\n" + 
          		"      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:0px 10px 10px 15px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n" + 
          		"        \r\n" + 
          		"  <h3 style=\"margin: 0px; color: #293c4b; line-height: 140%; text-align: left; word-wrap: break-word; font-weight: normal; font-family: 'Montserrat',sans-serif; font-size: 18px;\">\r\n" + 
          		"    <strong>Ola " + email.getNomeDestinatario() + ",</strong>\r\n" + 
          		"  </h3>\r\n" + 
          		"\r\n" + 
          		"      </td>\r\n" + 
          		"    </tr>\r\n" + 
          		"  </tbody>\r\n" + 
          		"</table>\r\n" + 
          		"\r\n" + 
          		"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
          		"  <tbody>\r\n" + 
          		"    <tr>\r\n" + 
          		"      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n" + 
          		"        \r\n" + 
          		"  <div style=\"color: #656e72; line-height: 140%; text-align: left; word-wrap: break-word;\">\r\n" + 
          		"    <p style=\"font-size: 14px; line-height: 140%;\">Acabamos de indentivicar uma opotunidade de licitação na sua area de atuação! </p>\r\n" + 
          		"  </div>\r\n" + 
          		"\r\n" + 
          		"      </td>\r\n" + 
          		"    </tr>\r\n" + 
          		"  </tbody>\r\n" + 
          		"</table>\r\n" + 
          		"\r\n" + 
          		"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
          		"  <tbody>\r\n" + 
          		"    <tr>\r\n" + 
          		"      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n" + 
          		"        \r\n" + 
          		"  <div style=\"color: #656e72; line-height: 140%; text-align: left; word-wrap: break-word;\">\r\n" + 
          		"    <p style=\"font-size: 14px; line-height: 140%;\">"+ email.getText() + "</p>\r\n" + 
          		"  </div>\r\n" + 
          		"\r\n" + 
          		"      </td>\r\n" + 
          		"    </tr>\r\n" + 
          		"  </tbody>\r\n" + 
          		"</table>\r\n" + 
          		"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
          		"  <tbody>\r\n" + 
          		"    <tr>\r\n" + 
          		"      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n" + 
          		"        \r\n" + 
          		"  <div style=\"color: #293c4b; line-height: 140%; text-align: center; word-wrap: break-word;\">\r\n" + 
          		"    <p style=\"font-size: 14px; line-height: 140%;\">" + "*INFORMAÇÕES DO ORGÃO PUBLICO RESPONSAVEL*" + "</p>\r\n" + 
          		"  </div>\r\n" + 
          		"      </td>\r\n" + 
          		"    </tr>\r\n" + 
          		"  </tbody>\r\n" + 
          		"</table>\r\n" + 
          		"</div>\r\n" + 
          		"  </div>\r\n" + 
          		"</div>\r\n" + 
          		"    </div>\r\n" + 
          		"  </div>\r\n" + 
          		"</div>\r\n" + 
          		"<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\r\n" + 
          		"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 550px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ecf0f1;\">\r\n" + 
          		"    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\r\n" + 
          		"\r\n" + 
          		"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 550px;display: table-cell;vertical-align: top;\">\r\n" + 
          		"  <div style=\"height: 100%;width: 100% !important;\">\r\n" + 
          		"<div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\r\n" + 
          		"  \r\n" + 
          		"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
          		"  <tbody>\r\n" + 
          		"    <tr>\r\n" + 
          		"      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:25px 10px 10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n" + 
          		"        \r\n" + 
          		"  <div style=\"color: #169179; line-height: 140%; text-align: center; word-wrap: break-word;\">\r\n" + 
          		"    <p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 16px; line-height: 22.4px;\"><strong><span style=\"line-height: 22.4px; font-family: Lato, sans-serif; font-size: 16px;\">Clique no botão abaixo para mais informações</span></strong></span></p>\r\n" + 
          		"  </div>\r\n" + 
          		"\r\n" + 
          		"      </td>\r\n" + 
          		"    </tr>\r\n" + 
          		"  </tbody>\r\n" + 
          		"</table>\r\n" + 
          		"\r\n" + 
          		"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
          		"  <tbody>\r\n" + 
          		"    <tr>\r\n" + 
          		"      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 30px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n" + 
          		"        \r\n" + 
          		"<div align=\"center\">\r\n" + 
          		"\r\n" + 
          		"    <a href=\"https://unlayer.com\" target=\"_blank\" style=\"box-sizing: border-box;display: inline-block;font-family:arial,helvetica,sans-serif;text-decoration: none;-webkit-text-size-adjust: none;text-align: center;color: #FFFFFF; background-color: #169179; border-radius: 4px;-webkit-border-radius: 4px; -moz-border-radius: 4px; width:auto; max-width:100%; overflow-wrap: break-word; word-break: break-word; word-wrap:break-word; mso-border-alt: none;\">\r\n" + 
          		"      <span style=\"display:block;padding:10px 50px;line-height:120%;\"><span style=\"font-size: 16px; line-height: 19.2px;\">Ver Anuncio</span></span>\r\n" + 
          		"    </a>\r\n" + 
          		"\r\n" + 
          		"</div>\r\n" + 
          		"\r\n" + 
          		"      </td>\r\n" + 
          		"    </tr>\r\n" + 
          		"  </tbody>\r\n" + 
          		"</table>\r\n" + 
          		"\r\n" + 
          		" </div>\r\n" + 
          		"  </div>\r\n" + 
          		"</div>\r\n" + 
          		"\r\n" + 
          		"    </div>\r\n" + 
          		"  </div>\r\n" + 
          		"</div>\r\n" + 
          		"\r\n" + 
          		"<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\r\n" + 
          		"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 550px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\r\n" + 
          		"    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\r\n" + 
          		"\r\n" + 
          		"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 550px;display: table-cell;vertical-align: top;\">\r\n" + 
          		"  <div style=\"height: 100%;width: 100% !important;\">\r\n" + 
          		"<div style=\"padding: 20px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\r\n" + 
          		"  \r\n" + 
          		"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
          		"  <tbody>\r\n" + 
          		"    <tr>\r\n" + 
          		"      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n" + 
          		"        \r\n" + 
          		"<div align=\"center\">\r\n" + 
          		"  <div style=\"display: table; max-width:125px;\">\r\n" + 
          		"\r\n" + 
          		"    <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"width: 32px !important;height: 32px !important;display: inline-block;border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 10px\">\r\n" + 
          		"      <tbody><tr style=\"vertical-align: top\"><td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\r\n" + 
          		"        <a href=\"https://twitter.com/\" title=\"Twitter\" target=\"_blank\">\r\n" + 
          		"          <img src='cid:iconeTwitter' alt=\"Twitter\" title=\"Twitter\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\r\n" + 
          		"        </a>\r\n" + 
          		"      </td></tr>\r\n" + 
          		"    </tbody></table>\r\n" + 
          		"\r\n" + 
          		"    <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"width: 32px !important;height: 32px !important;display: inline-block;border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 10px\">\r\n" + 
          		"      <tbody><tr style=\"vertical-align: top\"><td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\r\n" + 
          		"        <a href=\"https://linkedin.com/\" title=\"LinkedIn\" target=\"_blank\">\r\n" + 
          		"          <img src='cid:iconeLinkedin' alt=\"LinkedIn\" title=\"LinkedIn\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\r\n" + 
          		"        </a>\r\n" + 
          		"      </td></tr>\r\n" + 
          		"    </tbody></table>\r\n" + 
          		"\r\n" + 
          		"    <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"width: 32px !important;height: 32px !important;display: inline-block;border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 0px\">\r\n" + 
          		"      <tbody><tr style=\"vertical-align: top\"><td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\r\n" + 
          		"        <a href=\"https://instagram.com/\" title=\"Instagram\" target=\"_blank\">\r\n" + 
          		"          <img src='cid:iconeInstagram' alt=\"Instagram\" title=\"Instagram\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\r\n" + 
          		"        </a>\r\n" + 
          		"      </td></tr>\r\n" + 
          		"    </tbody></table>\r\n" + 
          		"\r\n" + 
          		"  </div>\r\n" + 
          		"</div>\r\n" + 
          		"\r\n" + 
          		"      </td>\r\n" + 
          		"    </tr>\r\n" + 
          		"  </tbody>\r\n" + 
          		"</table>\r\n" + 
          		"\r\n" + 
          		"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
          		"  <tbody>\r\n" + 
          		"    <tr>\r\n" + 
          		"      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 0px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n" + 
          		"        \r\n" + 
          		"  <div style=\"color: #ecf0f1; line-height: 140%; text-align: center; word-wrap: break-word;\">\r\n" + 
          		"    <p style=\"font-size: 14px; line-height: 140%;\">Em caso de duvidas, envie uma mensagem para LicitaiService@gmail.com. Todos os direitos reservados.<br />58500-000<br />Monteiro - PB. Brasil<br />Termos de uso | Politica de Privacidade</p>\r\n" + 
          		"  </div>\r\n" + 
          		"\r\n" + 
          		"      </td>\r\n" + 
          		"    </tr>\r\n" + 
          		"  </tbody>\r\n" + 
          		"</table>\r\n" + 
          		"\r\n" + 
          		"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
          		"  <tbody>\r\n" + 
          		"    <tr>\r\n" + 
          		"      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 0px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n" + 
          		"        \r\n" + 
          		"  <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 1px solid #5c5a5a;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\r\n" + 
          		"    <tbody>\r\n" + 
          		"      <tr style=\"vertical-align: top\">\r\n" + 
          		"        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\r\n" + 
          		"          <span>&#160;</span>\r\n" + 
          		"        </td>\r\n" + 
          		"      </tr>\r\n" + 
          		"    </tbody>\r\n" + 
          		"  </table>\r\n" + 
          		"\r\n" + 
          		"      </td>\r\n" + 
          		"    </tr>\r\n" + 
          		"  </tbody>\r\n" + 
          		"</table>\r\n" + 
          		"\r\n" + 
          		"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" + 
          		"  <tbody>\r\n" + 
          		"    <tr>\r\n" + 
          		"      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:0px 10px 10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\r\n" + 
          		"        \r\n" + 
          		"  <div style=\"color: #7e8c8d; line-height: 140%; text-align: center; word-wrap: break-word;\">\r\n" + 
          		"    <p style=\"font-size: 14px; line-height: 140%;\">© 2022 LicitAí. Todos os direitos reservados.</p>\r\n" + 
          		"  </div>\r\n" + 
          		"\r\n" + 
          		"      </td>\r\n" + 
          		"    </tr>\r\n" + 
          		"  </tbody>\r\n" + 
          		"</table>\r\n" + 
          		"\r\n" + 
          		" </div>\r\n" + 
          		"  </div>\r\n" + 
          		"</div>\r\n" + 
          		"\r\n" + 
          		"    </div>\r\n" + 
          		"  </div>\r\n" + 
          		"</div>\r\n" + 
          		"\r\n" + 
          		"    </td>\r\n" + 
          		"  </tr>\r\n" + 
          		"  </tbody>\r\n" + 
          		"  </table>\r\n" + 
          		"\r\n" + 
          		"</body>\r\n" + 
          		"\r\n" + 
          		"</html>\r\n" + 
          		"";
    }
}
