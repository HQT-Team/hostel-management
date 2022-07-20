package com.hqt.happyhostel.servlet.RecoverPasswordServlets;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dao.InformationDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.utils.EncodeBase64Utils;
import com.hqt.happyhostel.utils.MailUtils;
import com.hqt.happyhostel.utils.RandomStringGenerator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet(name = "RecoverPasswordServlet", value = "/RecoverPasswordServlet")
public class RecoverPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("USER");

            if (account == null) {
                request.getRequestDispatcher("recover-password-page").forward(request, response);
            } else {
                request.getRequestDispatcher("dashboard").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "recover-password-page";
        try {
            HandlerStatus handlerStatus;
            String email = request.getParameter("email");
            AccountDAO accountDao = new AccountDAO();
            Account account = accountDao.getAccountByEmail(email);
            if (account == null) {
                handlerStatus = HandlerStatus.builder()
                        .status(false)
                        .content("Email bạn vừa nhập không ứng với bất kì tài khoản nào trong hệ thống! Vui lòng kiểm tra lại!").build();
            } else {
                if (account.getRole() == -1) {
                    handlerStatus = HandlerStatus.builder()
                            .status(false)
                            .content("Tài khoản liên kết với email này đã bị khóa nên không thể phục hồi mật khẩu! Vui lòng liên hệ với quản trị viên!").build();
                } else {
                    String userEmail = accountDao.getAccountInformationById(account.getAccId()).getInformation().getEmail();
                    String requestRecoverPasswordCode = RandomStringGenerator.randomOTP(5);

                    String domain = "http://localhost:8080/HappyHostel/recover-password-reset?data=";
                    String recoverPasswordUrl = "accountId=" + account.getAccId() + "&recoverCode=" + requestRecoverPasswordCode;
                    String encodeString = EncodeBase64Utils.encodeStringBase64(recoverPasswordUrl);

                    if (new MailUtils().sendRecoverMail(userEmail, domain, encodeString)) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        Calendar startTime = Calendar.getInstance();
                        long timeInSecs = startTime.getTimeInMillis();
                        Timestamp endTime = new Timestamp(timeInSecs + (5 * 60 * 1000));

                        boolean insertResult = accountDao.addKeyAndExpirationTimeForPasswordRecoveryRequest(requestRecoverPasswordCode, sdf.format(endTime), account.getAccId());
                        if (insertResult) {
                            handlerStatus = HandlerStatus.builder()
                                    .status(true)
                                    .content("Một email hướng dẫn khôi phục mật khẩu đã được gửi đến email của bạn thành công!").build();
                            url = "recover-password-result-page";
                        } else {
                            handlerStatus = HandlerStatus.builder()
                                    .status(false)
                                    .content("Đã có lỗi xảy ra! Vui lòng kiểm tra lại hoặc thử lại sau!").build();
                        }
                    } else {
                        handlerStatus = HandlerStatus.builder()
                                .status(false)
                                .content("Đã có lỗi xảy ra! Vui lòng kiểm tra lại hoặc thử lại sau!").build();
                    }
                }
            }
            request.setAttribute("RESPONSE_MSG", handlerStatus);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private String returnBodyEmail(String url) {
        return "<!doctype html>\n" +
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
    }

}

