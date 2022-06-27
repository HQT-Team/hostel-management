package com.hqt.happyhostel.servlet.RenterRegisterServlets;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.utils.MailUtils;
import com.hqt.happyhostel.utils.RandomStringGenerator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet(name = "SendOTPServlet", value = "/SendOTPServlet")
public class SendOTPServlet extends HttpServlet {
    private final String SUCCESS = "verify-renter-page";
    private final String FAIL = "verify-renter-page";
    private final String ERROR = "error-page";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accId = (request.getAttribute("ACCOUNT_ID") != null) ?
                (int) request.getAttribute("ACCOUNT_ID") : Integer.parseInt(request.getParameter("account_id"));
        String url = ERROR;
        String userEmail = null;
        HandlerStatus handlerStatus = null;

        try {
            userEmail = new AccountDAO().getAccountInformationById(accId).getInformation().getEmail();
            if (userEmail != null) {
                String otp = RandomStringGenerator.randomOTP(5);
                String mailObject = "Mã xác nhận tài khoản";
                String mailBody = "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                        "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                        "    <link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap\" rel=\"stylesheet\">\n" +
                        "</head>\n" +
                        "\n" +
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
                if (MailUtils.sendOTPMail(userEmail, mailObject, mailBody)) {
                    url = SUCCESS;

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Calendar startTime = Calendar.getInstance();
                    long timeInSecs = startTime.getTimeInMillis();
                    Timestamp endTime = new Timestamp(timeInSecs + (2 * 60 * 1000));

                    new AccountDAO().updateAccountOTP(accId, otp, sdf.format(endTime));
                    handlerStatus = HandlerStatus.builder().status(true).content("Mã OTP đã được gửi. Vui lòng kiểm tra Email của bạn.").build();
                } else {
                    url = FAIL;
                    handlerStatus = HandlerStatus.builder().status(false).content("Không thể gửi mã OTP. Vui lòng kiểm tra lại các thông tin.").build();
                }
                request.setAttribute("RESPONSE_MSG", handlerStatus);
                request.setAttribute("ACCOUNT_ID", accId);
            }
        } catch (Exception e) {
            log("Error at SendOTPServlet: " + e.toString());
        } finally {
            if (ERROR.equalsIgnoreCase(url)) response.sendRedirect(url);
            else request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
