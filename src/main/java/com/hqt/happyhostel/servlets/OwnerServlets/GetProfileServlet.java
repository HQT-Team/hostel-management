package com.hqt.happyhostel.servlets.OwnerServlets;

import com.hqt.happyhostel.dao.InformationDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.AccountInfo;
import com.hqt.happyhostel.dto.Information;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GetProfileServlet", value = "/GetProfileServlet")
public class GetProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "profile-page";
        int type = request.getParameter("type") == null ? 0 : Integer.parseInt(request.getParameter("type"));
        try {
            HttpSession session = request.getSession();
            Account ownerAccount = (Account) session.getAttribute("USER");
            Information information = new InformationDAO().getAccountInformationById(ownerAccount.getAccId());
            ownerAccount.setAccountInfo(AccountInfo.builder().information(information).build());
            session.setAttribute("CURRENT_PAGE", "account");
            session.setAttribute("USER", ownerAccount);
            request.setAttribute("TYPE", type);
        } catch (Exception e) {
            log("Error at EndRentalServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
