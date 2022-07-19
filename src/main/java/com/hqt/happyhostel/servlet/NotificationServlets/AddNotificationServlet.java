package com.hqt.happyhostel.servlet.NotificationServlets;

import com.google.gson.Gson;
import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dao.HostelOwnerDAO;
import com.hqt.happyhostel.dao.NotificationDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.utils.MailUtils;
import com.hqt.happyhostel.utils.RandomStringGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@WebServlet(name = "AddNotificationServlet", value = "/AddNotificationServlet")
public class AddNotificationServlet extends HttpServlet {
    private final String SUCCESS = "owner-review-notification";
    private final String FAIL = "notification-page";
    private final String ERROR = "error-page";

    private String otp = RandomStringGenerator.randomOTP(5);
    private String mailObject = "Bạn vừa nhận được thông báo mới từ chủ trọ.";
    private String mailBody =
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ownerId = -1;
        int hostelId = -1;
        String title = null;
        String content = null;
        String url = ERROR;
        HandlerStatus handlerStatus = null;
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Account owner = (Account) session.getAttribute("USER");
                if (owner != null) {
                    url = FAIL;
                    ownerId = owner.getAccId();
                    hostelId = Integer.parseInt(request.getParameter("noti-hostel-id"));
                    title = request.getParameter("noti-title");
                    content = request.getParameter("noti-content");

                    if (new HostelOwnerDAO().checkOwnerHostel(ownerId)) {
                        int notiId = new NotificationDAO().creatNotification(ownerId, hostelId, title, content);

                        if (notiId > 0) {
                            request.setAttribute("NOTIFICATION_ID", notiId);
                            request.setAttribute("HOSTEL_ID", hostelId);

                            ArrayList<String> accMailList = new ArrayList<>();
                            String mail = null;
                            ArrayList<Integer> renterList = new HostelDAO().getListRenterIdByHostelId(hostelId);
                            for (int id : renterList) {
                                mail = new AccountDAO().getAccountInformationById(id).getInformation().getEmail();
                                if (mail != null) {
                                    accMailList.add(mail);
                                }
                            }

                            if (accMailList != null && accMailList.size() > 0) {
                                if (MailUtils.sendBCCEmail(accMailList, mailObject, mailBody)) {
                                    handlerStatus = HandlerStatus.builder().status(true).content("Mail đã được gửi thành công. Vui lòng kiểm tra Email của bạn.").build();
                                } else {
                                    handlerStatus = HandlerStatus.builder().status(false).content("Không thể gửi Mail. Vui lòng kiểm tra lại các thông tin.").build();
                                }
                                request.setAttribute("RESPONSE_MSG", handlerStatus);
                            }

                            url = SUCCESS;

                        } else {
                            handlerStatus = HandlerStatus.builder().status(false).content("Gửi thông báo không thành công").build();
                        }

                    } else {
                        handlerStatus = HandlerStatus.builder().status(false).content("Bạn không thể gửi thông báo cho khu trọ này").build();
                    }
                    request.setAttribute("RESPONSE_MSG", handlerStatus);
                }
            }

        } catch (Exception e) {
            log("Error at AddNotificationServlet: " + e);
        } finally {
            if (ERROR.equalsIgnoreCase(url)) response.sendRedirect(url);
            else request.getRequestDispatcher(url).forward(request, response);

        }
    }
}
