package com.journaldev.spring.utils;

import org.springframework.beans.factory.annotation.Value;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.*;


public class EmailUtils {

    private static String from;
    private static String password;
    private static String host;
    private static String port;
    private static Boolean isAuth;
    private static Boolean isEnable;


    private static final Properties PROPS = new Properties();



    private static Session getSession() {
        PROPS.put("mail.smtp.host", host);//"smtp.gmail.com"
        PROPS.put("mail.smtp.port", port);//587
        PROPS.put("mail.smtp.auth", isAuth);//true
        PROPS.put("mail.smtp.starttls.enable",isEnable); //true
        return Session.getInstance(PROPS, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
    }

    public static void send(final String to, final String subject, final String msg) {
        send(to, subject, msg, null);
    }

    public static void send(final String to, final String subject, final String msg, final String attachmentPathname) {
        HashSet<String> toSet = new HashSet<>();
        toSet.add(to);
        send(toSet, null, null, subject, msg, attachmentPathname);
    }


    public static void send(final Set<String> to, final Set<String> cc, final Set<String> bcc,
                            final String subject, final String msg, final String attachmentPathname) {
        try {
            Message message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(from));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(String.join(", ", to))
            );
            if (cc != null && cc.size() > 0)
                message.setRecipients(
                        Message.RecipientType.CC,
                        InternetAddress.parse(String.join(", ", cc))
                );
            if (bcc != null && bcc.size() > 0)
                message.setRecipients(
                        Message.RecipientType.BCC,
                        InternetAddress.parse(String.join(", ", bcc))
                );

            message.setSubject(subject);
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(msg, "text/html");

            MimeBodyPart attachmentPart = new MimeBodyPart();
            if (attachmentPathname != null)
                attachmentPart.attachFile(new File(attachmentPathname));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            if (attachmentPathname != null)
                multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFrom(String from) {
        EmailUtils.from = from;
    }

    public void setPassword(String password) {
        EmailUtils.password = password;
    }

    public void setHost(String host) {
        EmailUtils.host = host;
    }

    public void setPort(String port) {
        EmailUtils.port = port;
    }

    public void setIsAuth(Boolean isAuth) {
        EmailUtils.isAuth = isAuth;
    }

    public void setIsEnable(Boolean isEnable) {
        EmailUtils.isEnable = isEnable;
    }


//    public static void main(String[] args) {
//        EmailUtils.send(FROM, "Hi there", "Server Date :" + new Date());
//    }
}
