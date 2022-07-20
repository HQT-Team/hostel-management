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

    public boolean SendMailConfirmActiveOwnerAccount(String receiveMail, String domain){
        String mailObject = "Tài khoản đã được xác thực";
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
                        "\t\t\t\tTài khoản của bạn đã được xác nhận.\n" +
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

    public boolean sendRecoverMail(String receiveMail, String domain, String encodeString){
        String mailObject = "Hướng dẫn khôi phục mật khẩu";
        String mailBody = "Sau đây là url để bạn khôi phục lại mật khẩu: " +
                "<a href='" + domain + encodeString + "' target='_blank'>Link here</a>";
        return sendMail(receiveMail, mailObject, mailBody);
    }
}