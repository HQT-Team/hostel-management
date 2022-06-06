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
        try {
            String url = "AdminPage";
            int id = Integer.parseInt(request.getParameter("owner_id"));
            int status = Integer.parseInt((request.getParameter("status")));
            int i = status == 0 ? AccountDAO.updateAccountStatus(id, 1) : AccountDAO.updateAccountStatus(id, 0);
            if(i > 0) {
                request.getRequestDispatcher(url).forward(request,response);
            }else {
                request.setAttribute("WARNING", "CAN NOT UPDATE");
            }
        }catch (Exception e){
            log("Error at DashboardServlet: " + e.toString());
        }
    }
}
