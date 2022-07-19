package com.hqt.happyhostel.utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class MailUtils {
    //    public static void main(String[] args) throws MessagingException, UnsupportedEncodingException {
    private static final String MAIL_SERVER = "smtp";

    public static boolean sendOTPMail(String toEmail, String subject, String body) {
        boolean result = false;
        Transport transport = null;
        try {
            final String fromEmail = "hqthostel@gmail.com";
            // Mat khai email cua ban
            final String password = "jynlfoueiwkrmrbp";
            // dia chi email nguoi nhan

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

            Authenticator auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };
            Session session = Session.getInstance(props, auth);

            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(fromEmail, "NoReply-HQT"));

            msg.setReplyTo(InternetAddress.parse(fromEmail, false));

            msg.setSubject(subject, "UTF-8");

            msg.setContent(body, "text/HTML;charset=UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            transport = session.getTransport(MAIL_SERVER);
            transport.connect("smtp.gmail.com", 587, fromEmail, password);
            transport.sendMessage(msg, msg.getAllRecipients());

            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            try {
                transport.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static boolean sendBCCEmail(ArrayList<String> bcc, String subject, String body) {
        boolean result = false;
        Transport transport = null;

        final String fromEmail = "hqthostel@gmail.com";
        // Mat khai email cua ban
        final String password = "jynlfoueiwkrmrbp";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        properties.put("mail.smtp.port", "587"); //TLS Port
        properties.put("mail.smtp.auth", "true"); //enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //---------------------------------------------STEP 2---------------------------------------------


        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        Session session = Session.getInstance(properties, auth);

        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(session);

        try {

            //---------------------------------------------

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(fromEmail, "NoReply-HQT"));

            message.setReplyTo(InternetAddress.parse(fromEmail, false));
            //---------------------------------------------

            InternetAddress[] bccAddress = new InternetAddress[bcc.size()];

            // To get the array of bccaddresses
            for (int i = 0; i < bcc.size(); i++) {
                bccAddress[i] = new InternetAddress(bcc.get(i));
            }

            // Set bcc: header field of the header.
            for (int i = 0; i < bccAddress.length; i++) {
                message.addRecipient(Message.RecipientType.BCC, bccAddress[i]);
            }

            // Set Subject: header field
            message.setSubject(subject, "UTF-8");
            message.setContent(body, "text/HTML;charset=UTF-8");
            message.setSentDate(new Date());

            //---------------------------------------------STEP 3---------------------------------------------
            // Send message
            transport = session.getTransport(MAIL_SERVER);
            transport.connect("smtp.gmail.com", 587, fromEmail, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            try {
                transport.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return result;

        //---------------------------------------------------------------------------------------------------

    }
}