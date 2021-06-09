package com.test.assessment.service;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

@Service("emailService")
public class EmailService {

    @Value("${assessment.test.for.message.from:noreply@user.com}")
    String messageFrom;

    @Value("${assessment.test.for.email.host:mail.user.ng}")
    private String mailHostName;

    @Value("${assessment.test.for.email.port:465}")
    private String mailPort;

    @Value("${assessment.test.for.email.username:user@user.ng}")
    private String mailUserName;

    @Value("${assessment.test.for.email.password:provided}")
    private String mailPassword;

    @Value("${assessment.test.for.email.debug:true}")
    private String mailDebugInfo;

    @Value("${assessment.test.for.socketFactoryPort:465}")
    private String mailSocketFactoryPort;



    @Async
    public void sendEmail(String to, String subject, String body, String senderName) {
        try {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", mailHostName);
            props.put("mail.smtp.port", mailPort);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", mailDebugInfo);
            props.put("mail.smtp.socketFactory.port", mailSocketFactoryPort);
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            Authenticator auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailUserName, mailPassword);
                }
            };
            MimeMessage mimeMessage = new MimeMessage(Session.getDefaultInstance(props, auth));

            mimeMessage.addHeader("Content-type", "text/HTML; charset=UTF-8");

            mimeMessage.addHeader("format", "flowed");
            mimeMessage.addHeader("Content-Transfer-Encoding", "8bit");
            mimeMessage.setFrom(new InternetAddress(messageFrom, senderName));
            mimeMessage.setSubject(subject, "UTF-8");
            mimeMessage.setText(body, "UTF-8");
            mimeMessage.setSentDate(new Date());
            mimeMessage.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(to, false));
            System.out.println("Sending message");
            Transport.send(mimeMessage);

        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}



