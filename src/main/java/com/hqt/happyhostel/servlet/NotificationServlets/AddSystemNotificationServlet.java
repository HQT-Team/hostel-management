package com.hqt.happyhostel.servlet.NotificationServlets;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dao.HostelOwnerDAO;
import com.hqt.happyhostel.dao.NotificationDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.utils.MailUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AddSystemNotificationServlet", value = "/AddSystemNotificationServlet")
public class AddSystemNotificationServlet extends HttpServlet {

    private final String SUCCESS = "detailHostel?hostelID=";
    private final String FAIL = "detailHostel?hostelID=";
    private final String ERROR = "error-page";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ownerId = -1;
        int hostelId = -1;
        String title = null;
        String content = null;
        HandlerStatus handlerStatus = null;
        String url = ERROR;
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Account owner = (Account) session.getAttribute("USER");
                if (owner != null) {
                    url = FAIL;
                    ownerId = owner.getAccId();
                    hostelId = (int) request.getAttribute("noti-hostel-id");
                    title = (String) request.getAttribute("noti-title");
                    content = (String) request.getAttribute("noti-content");
                    handlerStatus = (HandlerStatus) request.getAttribute("RESPONSE_MSG");
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
                                new MailUtils().SendMailNotice(accMailList, domain);
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
                url+=hostelId;
            }
        } catch (Exception e) {
            log("Error at AddNotificationServlet: " + e);
        }finally {
            if (ERROR.equalsIgnoreCase(url)) response.sendRedirect(url);
            else request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
