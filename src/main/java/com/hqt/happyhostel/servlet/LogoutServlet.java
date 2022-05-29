package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dto.Account;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", value = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "success";
        try {
            HttpSession session = request.getSession(true);
            Account acc = (Account) session.getAttribute("USER");
            String username = acc.getUsername();
            session.invalidate();
            Cookie[] cookie = request.getCookies();
            for (Cookie c : cookie) {
                if (c.getName().equals("selector")) {
                    c.setMaxAge(0);
                    c.setValue(null);
                    response.addCookie(c);
                    AccountDAO.updateTokenByUserName(null, username);
                }
            }
            url = "loginPage";
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            response.sendRedirect(url);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
