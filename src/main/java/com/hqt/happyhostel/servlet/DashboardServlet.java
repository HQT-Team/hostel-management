package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dto.Account;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DashboardServlet", value = "/DashboardServlet")
public class DashboardServlet extends HttpServlet {

    private static String url = "loginPage";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            Account account = (Account) session.getAttribute("USER");

            if (account != null) {
                switch (account.getRole()) {
                    case 0:
                        url = "AdminPage";
                        break;
                    case 1:
                        url = "HostelOwnerPage";
                        break;
                    case 2:
                        url = "HostelRenterPage";
                        break;
                }
            }

            session.setAttribute("CURRENT_PAGE", "dashboard");
        } catch (Exception e) {
            log("Error at DashboardServlet: " + e.toString());
        } finally {
            req.getRequestDispatcher(url).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
