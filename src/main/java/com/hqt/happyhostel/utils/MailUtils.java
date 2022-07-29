package com.hqt.happyhostel.utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class MailUtils {
    private static final String MAIL_SERVER = "smtp";

    public boolean sendMail(String toEmail, String subject, String body) {
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

    public boolean sendBCCEmail(ArrayList<String> bcc, String subject, String body) {
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
    }


    //-----------------------------------------------------------MAIL-TEMPLATE-----------------------------------------------------------//
    public boolean SendMailConfirmPayment(String receiveMail, int roomNum, String billTitle){
        String mailBody =
                "<head>\n" +
                        "\t<meta charset=\"UTF-8\">\n" +
                        "\t<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                        "\t<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                        "\t<link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap\" rel=\"stylesheet\">\n" +
                        "</head>\n" +
                        "\n" +
                        "<body>\n" +
                        "\t<div class=\"mail\" style=\"max-width: 600px;\n" +
                        "                                width: 100%;\n" +
                        "                                margin: 0 auto;\n" +
                        "                                background-color: #fff;\n" +
                        "                                font-family: 'Roboto', sans-serif;\n" +
                        "                                text-align: center;\n" +
                        "                                padding-bottom: 24px;\n" +
                        "                                box-shadow: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;\">\n" +
                        "\t\t<div class=\"header\" style=\"background-color: #1fdf7f;\n" +
                        "                                    align-items: center;\n" +
                        "                                    font-size: 20px;\n" +
                        "                                    font-weight: bold;\n" +
                        "                                    color: #fff;\n" +
                        "                                    padding: 24px 0;\">\n" +
                        "\t\t\tHQT Hostel Management\n" +
                        "\t\t</div>\n" +
                        "\t\t<div class=\"content\">\n" +
                        "\t\t\t<div class=\"content__title\" style=\"font-size: 20px;\n" +
                        "                                                padding: 22px 0;\n" +
                        "                                                font-weight: 500;\">\n" +
                        "\t\t\t\tNgười thuê phòng " + roomNum + "vừa thanh toán hóa đơn "+ billTitle +".\n" +
                        "\t\t\t</div>\n" +
                        "\t\t\t<div class=\"content__sub-title\" style=\"color: #1fdf7f;\n" +
                        "                                                    font-size: 20px;\n" +
                        "                                                    font-weight: 500;\n" +
                        "                                                    text-shadow: 1px 1px rgba(100, 94, 94, 0.4);\">\n" +
                        "\t\t\t\tVui lòng kiểm tra lại thông tin. </br> Xin cảm ơn.\n" +
                        "\t\t\t</div>\n" +
                        "\t\t</div>\n" +
                        "\t</div>\n" +
                        "</body>";
        String mailObject = "Thông báo người thuê vừa thanh toán hóa đơn";
        return sendMail(receiveMail, mailObject, mailBody);
    }

    public boolean SendMailUnblockOwnerAccount(String receiveMail, String domain){
        String mailObject = "Tài khoản của bạn đã được mở khóa";
        String mailBody =
                "<head>\n" +
                        "\t<meta charset=\"UTF-8\">\n" +
                        "\t<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                        "\t<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                        "\t<link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap\" rel=\"stylesheet\">\n" +
                        "</head>\n" +
                        "\n" +
                        "<body>\n" +
                        "\t<div class=\"mail\" style=\"max-width: 600px;\n" +
                        "                                width: 100%;\n" +
                        "                                margin: 0 auto;\n" +
                        "                                background-color: #fff;\n" +
                        "                                font-family: 'Roboto', sans-serif;\n" +
                        "                                text-align: center;\n" +
                        "                                padding-bottom: 24px;\n" +
                        "                                box-shadow: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;\">\n" +
                        "\t\t<div class=\"header\" style=\"background-color: #1fdf7f;\n" +
                        "                                    align-items: center;\n" +
                        "                                    font-size: 20px;\n" +
                        "                                    font-weight: bold;\n" +
                        "                                    color: #fff;\n" +
                        "                                    padding: 24px 0;\">\n" +
                        "\t\t\tHQT Hostel Management\n" +
                        "\t\t</div>\n" +
                        "\t\t<div class=\"content\">\n" +
                        "\t\t\t<div class=\"content__title\" style=\"font-size: 20px;\n" +
                        "                                                padding: 22px 0;\n" +
                        "                                                font-weight: 500;\">\n" +
                        "\t\t\t\tTài khoản của bạn đã được mở khóa.\n" +
                        "\t\t\t</div>\n" +
                        "\t\t\t<div class=\"content__sub-title\" style=\"color: #1fdf7f;\n" +
                        "                                                    font-size: 20px;\n" +
                        "                                                    font-weight: 500;\n" +
                        "                                                    text-shadow: 1px 1px rgba(100, 94, 94, 0.4);\">\n" +
                        "\t\t\t\tTừ giờ bạn đã có thể sử dụng dịch vụ của chúng tôi </br> Xin cảm ơn.\n" +
                        "\t\t\t</div>\n" +
                        "\t\t\t<div id=\"hqt-content__otp\" class=\"content__otp\" style=\"width: 360px;\n" +
                        "                                                                    font-size: 28px;\n" +
                        "                                                                    font-weight: 700;\n" +
                        "                                                                    color: #fff;\n" +
                        "                                                                    background: #1dcc70;\n" +
                        "                                                                    border-radius: 16px;\n" +
                        "                                                                    margin: 24px auto;\n" +
                        "                                                                    padding: 18px 12px;\n" +
                        "                                                                    position: relative;\">\n" +
                        "\t\t\t\t<span id=\"hqt-content__otp-code\"><a style=\"text-decoration: none;\" href=\""+domain+"\">Click vào để vào trang đăng nhập</a></span>\n" +
                        "\t\t\t</div>\n" +
                        "\t\t</div>\n" +
                        "\t</div>\n" +
                        "</body>";
        return sendMail(receiveMail, mailObject, mailBody);
    }

    public boolean SendMailBlockOwnerAccount(String receiveMail, String domain){
        String mailObject = "Tài khoản của bạn đã bị khóa";
        String mailBody =
                "<head>\n" +
                        "\t<meta charset=\"UTF-8\">\n" +
                        "\t<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                        "\t<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                        "\t<link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap\" rel=\"stylesheet\">\n" +
                        "</head>\n" +
                        "\n" +
                        "<body>\n" +
                        "\t<div class=\"mail\" style=\"max-width: 600px;\n" +
                        "                                width: 100%;\n" +
                        "                                margin: 0 auto;\n" +
                        "                                background-color: #fff;\n" +
                        "                                font-family: 'Roboto', sans-serif;\n" +
                        "                                text-align: center;\n" +
                        "                                padding-bottom: 24px;\n" +
                        "                                box-shadow: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;\">\n" +
                        "\t\t<div class=\"header\" style=\"background-color: #1fdf7f;\n" +
                        "                                    align-items: center;\n" +
                        "                                    font-size: 20px;\n" +
                        "                                    font-weight: bold;\n" +
                        "                                    color: #fff;\n" +
                        "                                    padding: 24px 0;\">\n" +
                        "\t\t\tHQT Hostel Management\n" +
                        "\t\t</div>\n" +
                        "\t\t<div class=\"content\">\n" +
                        "\t\t\t<div class=\"content__title\" style=\"font-size: 20px;\n" +
                        "                                                padding: 22px 0;\n" +
                        "                                                font-weight: 500;\">\n" +
                        "\t\t\t\tTài khoản của bạn đã bị khóa.\n" +
                        "\t\t\t</div>\n" +
                        "\t\t\t<div class=\"content__sub-title\" style=\"color: #1fdf7f;\n" +
                        "                                                    font-size: 20px;\n" +
                        "                                                    font-weight: 500;\n" +
                        "                                                    text-shadow: 1px 1px rgba(100, 94, 94, 0.4);\">\n" +
                        "\t\t\t\tTừ giờ bạn không thể sử dụng dịch vụ của chúng tôi </br> Xin vui lòng liên hệ thông qua email hqthostel@gmail.com để được hỗ trợ.\n" +
                        "\t\t\t</div>\n" +
                        "\t\t</div>\n" +
                        "\t</div>\n" +
                        "</body>";
        return sendMail(receiveMail, mailObject, mailBody);
    }

    public boolean SendMailNotice(ArrayList<String> accMailList, String domain){
        String mailObject = "Bạn vừa nhận được thông báo mới từ chủ trọ.";
        String mailBody =
                "<head>\n" +
                        "\t<meta charset=\"UTF-8\">\n" +
                        "\t<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                        "\t<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                        "\t<link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap\" rel=\"stylesheet\">\n" +
                        "</head>\n" +
                        "\n" +
                        "<body>\n" +
                        "\t<div class=\"mail\" style=\"max-width: 600px;\n" +
                        "                                width: 100%;\n" +
                        "                                margin: 0 auto;\n" +
                        "                                background-color: #fff;\n" +
                        "                                font-family: 'Roboto', sans-serif;\n" +
                        "                                text-align: center;\n" +
                        "                                padding-bottom: 24px;\n" +
                        "                                box-shadow: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;\">\n" +
                        "\t\t<div class=\"header\" style=\"background-color: #1fdf7f;\n" +
                        "                                    align-items: center;\n" +
                        "                                    font-size: 20px;\n" +
                        "                                    font-weight: bold;\n" +
                        "                                    color: #fff;\n" +
                        "                                    padding: 24px 0;\">\n" +
                        "\t\t\tHQT Hostel Management\n" +
                        "\t\t</div>\n" +
                        "\t\t<div class=\"content\">\n" +
                        "\t\t\t<div class=\"content__title\" style=\"font-size: 20px;\n" +
                        "                                                padding: 22px 0;\n" +
                        "                                                font-weight: 500;\">\n" +
                        "\t\t\t\tChủ trọ vừa gửi 1 thông báo mới\n" +
                        "\t\t\t</div>\n" +
                        "\t\t\t<div class=\"content__sub-title\" style=\"color: #1fdf7f;\n" +
                        "                                                    font-size: 20px;\n" +
                        "                                                    font-weight: 500;\n" +
                        "                                                    text-shadow: 1px 1px rgba(100, 94, 94, 0.4);\">\n" +
                        "\t\t\t\tVui lòng kiểm tra lại thông tin. </br> Xin cảm ơn.\n" +
                        "\t\t\t</div>\n" +
                        "\t\t\t<div id=\"hqt-content__otp\" class=\"content__otp\" style=\"width: 360px;\n" +
                        "                                                                    font-size: 28px;\n" +
                        "                                                                    font-weight: 700;\n" +
                        "                                                                    color: #fff;\n" +
                        "                                                                    background: #1dcc70;\n" +
                        "                                                                    border-radius: 16px;\n" +
                        "                                                                    margin: 24px auto;\n" +
                        "                                                                    padding: 18px 12px;\n" +
                        "                                                                    position: relative;\">\n" +
                        "\t\t\t\t<span id=\"hqt-content__otp-code\"><a style=\"text-decoration: none;\" href=\""+domain+"\">Click vào để xem</a></span>\n" +
                        "\t\t\t</div>\n" +
                        "\t\t</div>\n" +
                        "\t</div>\n" +
                        "</body>";

        return sendBCCEmail(accMailList, mailObject, mailBody);
    }

    public boolean SendMailReport(String receiveMail, String domain){
        String otp = RandomStringGenerator.randomOTP(5);
        String mailObject = "Bạn vừa nhận được báo cáo mới.";
        String mailBody =
                "<head>\n" +
                        "\t<meta charset=\"UTF-8\">\n" +
                        "\t<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                        "\t<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                        "\t<link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap\" rel=\"stylesheet\">\n" +
                        "</head>\n" +
                        "\n" +
                        "<body>\n" +
                        "\t<div class=\"mail\" style=\"max-width: 600px;\n" +
                        "                                width: 100%;\n" +
                        "                                margin: 0 auto;\n" +
                        "                                background-color: #fff;\n" +
                        "                                font-family: 'Roboto', sans-serif;\n" +
                        "                                text-align: center;\n" +
                        "                                padding-bottom: 24px;\n" +
                        "                                box-shadow: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;\">\n" +
                        "\t\t<div class=\"header\" style=\"background-color: #1fdf7f;\n" +
                        "                                    align-items: center;\n" +
                        "                                    font-size: 20px;\n" +
                        "                                    font-weight: bold;\n" +
                        "                                    color: #fff;\n" +
                        "                                    padding: 24px 0;\">\n" +
                        "\t\t\tHQT Hostel Management\n" +
                        "\t\t</div>\n" +
                        "\t\t<div class=\"content\">\n" +
                        "\t\t\t<div class=\"content__title\" style=\"font-size: 20px;\n" +
                        "                                                padding: 22px 0;\n" +
                        "                                                font-weight: 500;\">\n" +
                        "\t\t\t\tNgười thuê vừa gửi 1 báo cáo mới\n" +
                        "\t\t\t</div>\n" +
                        "\t\t\t<div class=\"content__sub-title\" style=\"color: #1fdf7f;\n" +
                        "                                                    font-size: 20px;\n" +
                        "                                                    font-weight: 500;\n" +
                        "                                                    text-shadow: 1px 1px rgba(100, 94, 94, 0.4);\">\n" +
                        "\t\t\t\tVui lòng kiểm tra lại thông tin. </br> Xin cảm ơn.\n" +
                        "\t\t\t</div>\n" +
                        "\t\t\t<div id=\"hqt-content__otp\" class=\"content__otp\" style=\"width: 360px;\n" +
                        "                                                                    font-size: 28px;\n" +
                        "                                                                    font-weight: 700;\n" +
                        "                                                                    color: #fff;\n" +
                        "                                                                    background: #1dcc70;\n" +
                        "                                                                    border-radius: 16px;\n" +
                        "                                                                    margin: 24px auto;\n" +
                        "                                                                    padding: 18px 12px;\n" +
                        "                                                                    position: relative;\">\n" +
                        "\t\t\t\t<span id=\"hqt-content__otp-code\"><a style=\"text-decoration: none;\" href=\""+domain+"\">Click vào để xem</a></span>\n" +
                        "\t\t\t</div>\n" +
                        "\t\t</div>\n" +
                        "\t</div>\n" +
                        "</body>";
        return sendMail(receiveMail, mailObject, mailBody);
    }

    public boolean sendOTPMail(String receiveMail, String otp){
        String mailObject = "Mã xác nhận tài khoản";
        String mailBody =
                "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                        "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                        "    <link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap\" rel=\"stylesheet\">\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <div class=\"mail\" style=\"max-width: 600px;\n" +
                        "                                width: 100%;\n" +
                        "                                margin: 0 auto;\n" +
                        "                                background-color: #fff;\n" +
                        "                                font-family: 'Roboto', sans-serif;\n" +
                        "                                text-align: center;\n" +
                        "                                padding-bottom: 24px;\n" +
                        "                                box-shadow: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;\">\n" +
                        "        <div class=\"header\" style=\"background-color: #0067ff;\n" +
                        "                                    align-items: center;\n" +
                        "                                    font-size: 20px;\n" +
                        "                                    font-weight: bold;\n" +
                        "                                    color: #fff;\n" +
                        "                                    padding: 24px 0;\">\n" +
                        "            HQT Hostel Management\n" +
                        "        </div>\n" +
                        "        <div class=\"content\">\n" +
                        "            <div class=\"content__title\" style=\"font-size: 20px;\n" +
                        "                                                padding: 22px 0;\n" +
                        "                                                font-weight: 500;\">\n" +
                        "                Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi!\n" +
                        "            </div>\n" +
                        "            <div class=\"content__sub-title\" style=\"color: #1fdf7f;\n" +
                        "                                                    font-size: 20px;\n" +
                        "                                                    font-weight: 500;\n" +
                        "                                                    text-shadow: 1px 1px rgba(100, 94, 94, 0.4);\">\n" +
                        "                Đây là mã OTP của bạn\n" +
                        "            </div>\n" +
                        "            <div id=\"hqt-content__otp\" class=\"content__otp\" style=\"width: 360px;\n" +
                        "                                                                    font-size: 28px;\n" +
                        "                                                                    font-weight: 700;\n" +
                        "                                                                    color: #fff;\n" +
                        "                                                                    background: #1dcc70;\n" +
                        "                                                                    border-radius: 16px;\n" +
                        "                                                                    margin: 24px auto;\n" +
                        "                                                                    padding: 18px 12px;\n" +
                        "                                                                    position: relative;\">\n" +
                        "                <span id=\"hqt-content__otp-code\">" + otp + "</span>\n" +
                        "            </div>\n" +
                        "            <div class=\"content__warning\" style=\"color: #ff0000;\n" +
                        "                                                 font-weight: 500;\">\n" +
                        "                Mã OTP này có hiệu lực trong 2 phút! <br />\n" +
                        "                Vui lòng không cung cấp OTP cho bất kì ai!\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "</body>";
        return sendMail(receiveMail, mailObject, mailBody);
    }

    public boolean sendRecoverMail(String receiveMail, String url){
        String mailObject = "Hướng dẫn khôi phục mật khẩu";
        String mailBody = "<!doctype html>\n" +
                "<html lang=\"vi\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n" +
                "    <title>Reset Password</title>\n" +
                "    <meta name=\"description\" content=\"Reset Password Email Template.\">\n" +
                "    <style type=\"text/css\">\n" +
                "        a:hover {\n" +
                "            text-decoration: underline !important;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\n" +
                "    <!--100% body table-->\n" +
                "    <table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\"\n" +
                "        style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">\n" +
                "        <tr>\n" +
                "            <td>\n" +
                "                <table style=\"background-color: #f2f3f8; \n" +
                "                max-width: 670px;  \n" +
                "                margin: 0 auto;\" width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                    <tr>\n" +
                "                        <td style=\"height:80px;\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td style=\"text-align: center;\">\n" +
                "                            <a href=\"\" title=\"logo\" target=\"_blank\">\n" +
                "                                <img width=\"150\" src=\"https://i.ibb.co/XSs4xvj/hqt-logo.png\" title=\"logo\" alt=\"logo\">\n" +
                "                            </a>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td style=\"height: 20px;\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td>\n" +
                "                            <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width: 670px;\n" +
                "                                background:#fff; \n" +
                "                                border-radius: 3px;\n" +
                "                                text-align: center;\n" +
                "                                -webkit-box-shadow: 0 6px 18px 0 rgba(0, 0, 0, .06);\n" +
                "                                -moz-box-shadow: 0 6px 18px 0 rgba(0, 0, 0, .06);\n" +
                "                                box-shadow: 0 6px 18px 0 rgba(0, 0, 0, .06);\">\n" +
                "                                <tr>\n" +
                "                                    <td style=\"height: 40px;\">&nbsp;</td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td style=\"padding:0 35px;\">\n" +
                "                                        <h1 style=\"color: #1e1e2d; \n" +
                "                                            font-weight: 500; margin: 0;\n" +
                "                                            font-size: 32px;\n" +
                "                                            font-family:'Rubik', sans-serif;\">\n" +
                "                                            Bạn đã yêu cầu đặt lại mật khẩu của mình\n" +
                "                                        </h1>\n" +
                "                                        <span style=\"display: inline-block;\n" +
                "                                            vertical-align:middle;\n" +
                "                                            margin: 29px 0 26px; \n" +
                "                                            border-bottom: 1px solid #cecece;\n" +
                "                                            width: 100px;\">\n" +
                "                                        </span>\n" +
                "                                        <p style=\"color:#455056;\n" +
                "                                        font-size: 15px;\n" +
                "                                        line-height: 24px; \n" +
                "                                        margin: 0;\">\n" +
                "                                            Chúng tôi không thể chỉ đơn giản gửi cho bạn mật khẩu cũ. Một liên kết duy\n" +
                "                                            nhất để đặt lại mật khẩu của bạn đã được tạo. Để đặt lại mật khẩu\n" +
                "                                            của bạn, hãy nhấp vào liên kết sau và làm theo hướng dẫn.\n" +
                "                                        </p>\n" +
                "                                        <a href=\"" + url + "\" style=\"background: #20e277;\n" +
                "                                            text-decoration: none !important; \n" +
                "                                            font-weight: 600; \n" +
                "                                            margin-top: 35px; \n" +
                "                                            color: #fff;\n" +
                "                                            text-transform: uppercase;\n" +
                "                                            font-size: 14px;\n" +
                "                                            padding: 14px 24px;\n" +
                "                                            width: 200px;\n" +
                "                                            display: inline-block;\n" +
                "                                            border-radius:50px;\" onmouseout=\"this.style.background='#20e277'\"\n" +
                "                                            onmouseover=\"this.style.background='#0bb658'\">\n" +
                "                                            Đặt lại mật khẩu\n" +
                "                                        </a>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
                "                                </tr>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    <tr>\n" +
                "                        <td style=\"height:20px;\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td style=\"text-align:center;\">\n" +
                "                            <p\n" +
                "                                style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">\n" +
                "                                &copy; <strong>www.hqthostel.com</strong></p>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td style=\"height:80px;\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "    <!--/100% body table-->\n" +
                "</body>\n" +
                "\n" +
                "</html>";
        return sendMail(receiveMail, mailObject, mailBody);
    }

    public boolean sendMailNewBill(String receiveMail, String billTitle){
        String domain = "http://localhost:8080/HappyHostel/renter-invoice";
        String mailObject = "Bạn vừa nhận được 1 hóa đơn mới";
        String mailBody = "<!doctype html>\n" +
                "<html lang=\"vi\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n" +
                "    <title>Reset Password</title>\n" +
                "    <meta name=\"description\" content=\"Reset Password Email Template.\">\n" +
                "    <style type=\"text/css\">\n" +
                "        a:hover {\n" +
                "            text-decoration: underline !important;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\n" +
                "    <!--100% body table-->\n" +
                "    <table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\"\n" +
                "        style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">\n" +
                "        <tr>\n" +
                "            <td>\n" +
                "                <table style=\"background-color: #f2f3f8; \n" +
                "                max-width: 670px;  \n" +
                "                margin: 0 auto;\" width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                    <tr>\n" +
                "                        <td style=\"height:80px;\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td style=\"text-align: center;\">\n" +
                "                            <a href=\"\" title=\"logo\" target=\"_blank\">\n" +
                "                                <img width=\"150\" src=\"https://i.ibb.co/XSs4xvj/hqt-logo.png\" title=\"logo\" alt=\"logo\">\n" +
                "                            </a>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td style=\"height: 20px;\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td>\n" +
                "                            <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width: 670px;\n" +
                "                                background:#fff; \n" +
                "                                border-radius: 3px;\n" +
                "                                text-align: center;\n" +
                "                                -webkit-box-shadow: 0 6px 18px 0 rgba(0, 0, 0, .06);\n" +
                "                                -moz-box-shadow: 0 6px 18px 0 rgba(0, 0, 0, .06);\n" +
                "                                box-shadow: 0 6px 18px 0 rgba(0, 0, 0, .06);\">\n" +
                "                                <tr>\n" +
                "                                    <td style=\"height: 40px;\">&nbsp;</td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td style=\"padding:0 35px;\">\n" +
                "                                        <h1 style=\"color: #1e1e2d; \n" +
                "                                            font-weight: 500; margin: 0;\n" +
                "                                            font-size: 32px;\n" +
                "                                            font-family:'Rubik', sans-serif;\">\n" +
                "                                            Bạn đã nhận được hóa đơn phòng "+billTitle+" \n" +
                "                                        </h1>\n" +
                "                                        <span style=\"display: inline-block;\n" +
                "                                            vertical-align:middle;\n" +
                "                                            margin: 29px 0 26px; \n" +
                "                                            border-bottom: 1px solid #cecece;\n" +
                "                                            width: 100px;\">\n" +
                "                                        </span>\n" +
                "                                        <p style=\"color:#455056;\n" +
                "                                        font-size: 15px;\n" +
                "                                        line-height: 24px; \n" +
                "                                        margin: 0;\">\n" +
                "                                            Chủ trọ vừa gửi cho bạn 1 hóa đơn tháng này. Vui lòng thanh toán đúng hạn.</br>  Xin cảm ơn\n" +
                "                                        </p>\n" +
                "                                        <a href=\""+domain+"\" style=\"background: #20e277;\n" +
                "                                            text-decoration: none !important; \n" +
                "                                            font-weight: 600; \n" +
                "                                            margin-top: 35px; \n" +
                "                                            color: #fff;\n" +
                "                                            text-transform: uppercase;\n" +
                "                                            font-size: 14px;\n" +
                "                                            padding: 14px 24px;\n" +
                "                                            width: 200px;\n" +
                "                                            display: inline-block;\n" +
                "                                            border-radius:50px;\" onmouseout=\"this.style.background='#20e277'\"\n" +
                "                                            onmouseover=\"this.style.background='#0bb658'\">\n" +
                "                                            Xem hóa đơn\n" +
                "                                        </a>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
                "                                </tr>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    <tr>\n" +
                "                        <td style=\"height:20px;\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td style=\"text-align:center;\">\n" +
                "                            <p\n" +
                "                                style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">\n" +
                "                                &copy; <strong>www.hqthostel.com</strong></p>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td style=\"height:80px;\">&nbsp;</td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "    <!--/100% body table-->\n" +
                "</body>\n" +
                "\n" +
                "</html>";
        return sendMail(receiveMail, mailObject, mailBody);
    }
}