package com.hqt.happyhostel.servlet.NotificationServlets;

import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dao.NotificationDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.dto.Hostel;
import com.hqt.happyhostel.dto.Notification;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReviewNotificationServlet", value = "/ReviewNotificationServlet")
public class ReviewNotificationServlet extends HttpServlet {
    private final String SUCCESS = "review-notification-page";
    private final String FAIL = "add-notification-page";
    private final String ERROR = "error-page";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;
        HandlerStatus handlerStatus = null;
        try {
            HttpSession session = request.getSession(false);
            if(session != null){

                int notiId =(request.getAttribute("NOTIFICATION_ID") != null) ?
                        (int) request.getAttribute("NOTIFICATION_ID") : Integer.parseInt(request.getParameter("notification_id"));
                if(notiId > 0) {
                    Notification notification = new NotificationDAO().getNotificationById(notiId);
                    handlerStatus = HandlerStatus.builder().status(true).content("Gửi thông báo thành công").build();
                    request.setAttribute("NOTIFICATION", notification);
                    url = SUCCESS;
                }else {
                    handlerStatus = HandlerStatus.builder().status(false).content("Có lỗi xảy ra. Vui lòng thử lại").build();
                }
                request.setAttribute("RESPONSE_MSG", handlerStatus);
            }

        }catch (Exception e){
            log("Error at ReviewNotificationServlet: " + e);
        }finally {
            if (ERROR.equalsIgnoreCase(url)) response.sendRedirect(url);
            else request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
