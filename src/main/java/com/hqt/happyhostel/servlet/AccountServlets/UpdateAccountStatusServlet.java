package com.hqt.happyhostel.servlet.AccountServlets;

import com.hqt.happyhostel.dao.AccountDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateAccountStatusServlet", value = "/UpdateAccountStatusServlet")
public class UpdateAccountStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "list-owner-account";

        AccountDAO accountDAO = new AccountDAO();

        try {

            int id = Integer.parseInt(request.getParameter("owner_id"));
            int status = Integer.parseInt(request.getParameter("status"));
            int i = status == 0 ? accountDAO.updateAccountStatus(id, 1) : accountDAO.updateAccountStatus(id, 0);
            if (i < 0) {
                request.setAttribute("ERROR", "UPDATE FAILED");
            } else {
                request.setAttribute("SUCCESS", "UPDATED SUCCESSFULLY");
            }
        }catch (Exception e){
            log("Error at DashboardServlet: " + e.toString());
        }finally {
            request.getRequestDispatcher(url).forward(request,response);
        }
    }
}
