package com.hqt.happyhostel.servlet.NotificationServlets;

import com.hqt.happyhostel.dao.NotificationDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.dto.Notification;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetNotificationListServlet", value = "/GetNotificationListServlet")
public class GetNotificationListServlet extends HttpServlet {
    private final String SUCCESS = "notification-page";
    private final String FAIL = "notification-page";
    private final String ERROR = "error-page";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;
        HandlerStatus handlerStatus = null;
        try {
            HttpSession session = request.getSession(false);
            if(session != null){
                Account owner = (Account) session.getAttribute("USER");
                if (owner != null){
                    url = FAIL;
                    int ownerId = owner.getAccId();
                    List<Notification> notificationList = new NotificationDAO().getNotificationByOwnerId(ownerId);
                    if (notificationList != null && !notificationList.isEmpty()) {
                        request.setAttribute("NOTIFICATION_LIST", notificationList);
                        url = SUCCESS;
                    }
                    else {
                        handlerStatus = HandlerStatus.builder().status(false).content("Hiện tại chưa có thông báo nào").build();
                    }
                    request.setAttribute("RESPONE_MSG", handlerStatus);
                }
            }
        }catch (Exception e){
            log("Error at GetNotificationListServlet: " + e);
        }finally {
            if (ERROR.equalsIgnoreCase(url)) response.sendRedirect(url);
            else request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
