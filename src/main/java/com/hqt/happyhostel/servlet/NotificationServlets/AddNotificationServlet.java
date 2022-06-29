package com.hqt.happyhostel.servlet.NotificationServlets;

import com.hqt.happyhostel.dao.HostelOwnerDAO;
import com.hqt.happyhostel.dao.NotificationDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.HandlerStatus;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddNotificationServlet", value = "/AddNotificationServlet")
public class AddNotificationServlet extends HttpServlet {
    private final String SUCCESS = "review-notification";
    private final String FAIL = "add-notification-page";
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
            if (session != null){
                Account owner = (Account) session.getAttribute("USER");
                if (owner != null){
                    url = FAIL;
                    ownerId = owner.getAccId();
                    hostelId = Integer.parseInt(request.getParameter("hostel_id"));
                    title = request.getParameter("title");
                    content = request.getParameter("content");

                    if(new HostelOwnerDAO().checkOwnerHostel(ownerId)){
                        int notiId = new NotificationDAO().creatNotification(ownerId, hostelId, title, content);

                        if (notiId > 0){
                            request.setAttribute("NOTIFICATION_ID", notiId);
                            url = SUCCESS;
                        }else {
                            handlerStatus = HandlerStatus.builder().status(false).content("Gửi thông báo không thành công").build();
                        }

                    }else {
                        handlerStatus = HandlerStatus.builder().status(false).content("Bạn không thể gửi thông báo cho khu trọ này").build();
                    }
                    request.setAttribute("RESPONSE_MSG", handlerStatus);
                }
            }

        }catch (Exception e){
            log("Error at AddNotificationServlet: " + e);
        }finally {
            if (ERROR.equalsIgnoreCase(url)) response.sendRedirect(url);
            else request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
