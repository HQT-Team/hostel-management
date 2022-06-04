package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dto.Account;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "VerificationRegisterServlet", value = "/VerificationRegisterServlet")
public class VerificationRegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        try {
            Account registerAccount = (Account)session.getAttribute("REGISTER_ACCOUNT");

            if (registerAccount != null) {
                String verifyType = req.getParameter("verifyType");
                switch (verifyType) {
                    case "admin-verify":
                        req.setAttribute("verifyType", verifyType);
                        req.getRequestDispatcher("verification-register-page").forward(req, resp);
                        break;
                    default:
                        resp.sendRedirect("error.jsp");
                }
            } else {
                resp.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            log("Error at RegisterServlet: " + e.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        try {
            Account registerAccount = (Account)session.getAttribute("REGISTER_ACCOUNT");

            if (registerAccount != null) {
                String verifyType = req.getParameter("verifyType");
                switch (verifyType) {
                    case "admin-verify":
                        String email = req.getParameter("email");
                        registerAccount.getAccountInfo().getInformation().setEmail(email);
                        boolean check = AccountDAO.addAnAccount(registerAccount);
                        if (check) {
                            session.removeAttribute("REGISTER_ACCOUNT");
                            resp.sendRedirect("HomePage");
                        } else {
                            resp.sendRedirect("error.jsp");
                        }
                        break;
                    default:
                        resp.sendRedirect("error.jsp");
                }
            } else {
                resp.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            log("Error at RegisterServlet: " + e.toString());
        }
    }
}
