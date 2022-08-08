package com.hqt.happyhostel.servlets.NotificationServlets;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dao.HostelOwnerDAO;
import com.hqt.happyhostel.dao.NotificationDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.utils.MailUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AddNotificationServlet", value = "/AddNotificationServlet")
public class AddNotificationServlet extends HttpServlet {
    private final String SUCCESS = "owner-review-notification";
    private final String FAIL = "notification-page";
    private final String ERROR = "error-page";


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
                                String domain = "http://localhost:8080/HappyHostel/RenterNotificationPage";
                                if (new MailUtils().SendMailNotice(accMailList, domain)) {
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
