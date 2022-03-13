package com.bezkoder.springjwt.helper.email;

import org.springframework.stereotype.Component;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Component
public class EmailSender {

    public static boolean sendEmail(String email ,String content) throws MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ishumessi2@gmail.com", "code@#Air");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("ishumessi2@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.trim()));
        msg.setSubject("websiteName.com");

       // msg.setContent(htmlText, "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(content, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
//        MimeBodyPart attachPart = new MimeBodyPart();
//
//        attachPart.attachFile("/var/tmp/image19.png");
//        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
        return true;
    }


}
