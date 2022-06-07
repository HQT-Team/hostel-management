package com.hqt.happyhostel.servlet.AccountServlets;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dto.Account;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ShowListOwnerAccountServlet", value = "/ShowListOwnerAccountServlet")
public class ShowListOwnerAccountServlet extends HttpServlet {
    private static final String url = "show-list-account";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ArrayList<Account> list = AccountDAO.GetAllByRole(1);
            request.setAttribute("OWNER_LIST", list);
        }catch (Exception e){
            log("Error at DashboardServlet: " + e.toString());
        }finally {
            request.getRequestDispatcher(url).forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ArrayList<Account> list = AccountDAO.GetAllByRole(1);
            request.setAttribute("OWNER_LIST", list);
        }catch (Exception e){
            log("Error at DashboardServlet: " + e.toString());
        }finally {
            request.getRequestDispatcher(url).forward(request,response);
        }
    }
}
