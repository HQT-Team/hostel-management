package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.AccountInfo;
import com.hqt.happyhostel.dto.Information;
import com.hqt.happyhostel.utils.SecurityUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String url = "registerPage";

        HttpSession session = req.getSession();

        try {
            String registerType = req.getParameter("registertype");

            if (registerType.equals("owner")) {
                String fullname = req.getParameter("fullname");
                String username = req.getParameter("username");
                String password = SecurityUtils.hashMd5(req.getParameter("password"));
                String cccd = req.getParameter("cccd");

                if (!AccountDAO.isExistUsername(username)) {
                    Information information = Information.builder()
                            .fullname(fullname)
                            .cccd(cccd).build();

                    AccountInfo accountInfo = new AccountInfo(information);

                    Account registerAccount = Account.builder()
                            .accId(0)
                            .username(username)
                            .password(password)
                            .status(0)
                            .role(1)
                            .accountInfo(accountInfo).build();

                    session.setAttribute("REGISTER_ACCOUNT", registerAccount);
                    resp.sendRedirect("choose-type-register-page");
                } else {
                    req.setAttribute("ERROR", "Tài khoản đã tồn tại trong hệ thống");
                    req.setAttribute("fullname", fullname);
                    req.setAttribute("username", username);
                    req.setAttribute("cccd", cccd);
                    req.getRequestDispatcher(url).forward(req, resp);
                }
            } else if (registerType.equals("renter")) {
                System.out.println("Renter register");
            } else {
                resp.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            log("Error at RegisterServlet: " + e.toString());
        }
    }
}
