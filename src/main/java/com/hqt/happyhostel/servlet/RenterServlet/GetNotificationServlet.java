package com.hqt.happyhostel.servlet.RenterServlet;

import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dao.NotificationDAO;
import com.hqt.happyhostel.dto.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "GetNotificationServlet", value = "/GetNotificationServlet")


public class GetNotificationServlet extends HttpServlet {
    public static final String ERROR = "Renter-notification";
    public static final String SUCCESS = "Renter-notification";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = ERROR;
        Account acc = new Account();
        List<Notification> notifications = new ArrayList<>();
        try {
            HttpSession session = req.getSession();
            acc = (Account)session.getAttribute("USER");
            int accId = acc.getAccId();
            HostelDAO hostelDAO = new HostelDAO();

            notifications = NotificationDAO.getNotificationById(accId);
            if (notifications.size()>0){
                req.setAttribute("NOTIFY", notifications);
                url = SUCCESS;
            }

            session.setAttribute("CURRENT_PAGE", "hostel-renter-page");
        }catch (Exception e){
            log("Error at GetHostelInforServlet: " + e.toString());
        }finally {
            req.getRequestDispatcher(url).forward(req,resp);
        }
    }
}

