package com.hqt.happyhostel.servlet.OwnerServlets;

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

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAO();

        try {
            HttpSession session = request.getSession();

            ArrayList<Account> list = accountDAO.GetAllByRole(1);

            request.setAttribute("OWNER_LIST", list);
            session.setAttribute("CURRENT_PAGE", "account");
        } catch (Exception e){
            log("Error at ShowListOwnerAccountServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
