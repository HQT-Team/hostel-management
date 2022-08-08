package com.hqt.happyhostel.servlets.NotificationServlets;

import com.hqt.happyhostel.dao.NotificationDAO;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.dto.Notification;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

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
            String action = request.getParameter("action");
            if(session != null){

                int notiId =(request.getAttribute("NOTIFICATION_ID") != null) ?
                        (int) request.getAttribute("NOTIFICATION_ID") : Integer.parseInt(request.getParameter("notification_id"));
                if(notiId > 0) {
                    Notification notification = new NotificationDAO().getNotificationById(notiId);
                    request.setAttribute("HOSTEL_ID",  request.getAttribute("HOSTEL_ID"));
                    request.setAttribute("NOTIFICATION", notification);
                    if(!"view".equals(action)){
                        handlerStatus = HandlerStatus.builder().status(true).content("Gửi thông báo thành công").build();
                    }
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
