package com.hqt.happyhostel.servlet.RenterRegisterServlets;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.HandlerStatus;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HandleCheckContractPageServlet", value = "/HandleCheckContractPageServlet")
public class HandleCheckContractPageServlet extends HttpServlet {
    private final String SUCCESS = "input-account-information-page";
    private final String FAIL = "confirm-room-info-page";
    private final String BACK = "invite-page";
    private final String ERROR = "error-page";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HandlerStatus handlerStatus = null;
        String url = ERROR;
        try {
            if ("continue".equalsIgnoreCase(action)) {
                url = SUCCESS;
                HttpSession session = request.getSession(false);
                if(session != null){
                    session.setAttribute("confirm-continue", "OK");
                }
            } else  url = BACK;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ERROR.equalsIgnoreCase(url)) response.sendRedirect(url);
            else request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
