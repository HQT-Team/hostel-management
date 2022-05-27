package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.HostelOwnerDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.utils.RandomStringGenerator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "loginPage";
        String username = request.getParameter("txtemail");
        String password = request.getParameter("txtpassword");
        String save = request.getParameter("savelogin");

        try {
            Account owner = HostelOwnerDAO.getAccountByUsernameAndPassword(username, password);
            if (owner != null) {
                url = "success";
                HttpSession session = request.getSession(true);
                if (session != null) {
                    session.setAttribute("USER", owner);
                    if (save != null) {
                        String token = RandomStringGenerator.randomToken(25,username);
                        //DAO add cookie
                        Cookie cookie = new Cookie("selector", token);
                        cookie.setMaxAge(60*60*24*2);
                        response.addCookie(cookie);

                        HostelOwnerDAO.addTokenByUserName(token, username);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            response.sendRedirect(url);
        }
    }
}
