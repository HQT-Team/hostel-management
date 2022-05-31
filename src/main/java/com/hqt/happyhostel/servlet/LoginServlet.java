package com.hqt.happyhostel.servlet;


import com.hqt.happyhostel.dao.AccountDAO;
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
        Account account = null;
        try {
            account = AccountDAO.getAccountByUsernameAndPassword(username, password);
            if (account != null && account.getStatus() == 1) {
                url = "success";
                HttpSession session = request.getSession(true);
                if (session != null) {
                    session.setAttribute("USER", account);
                    if (save != null) {
                        String token = RandomStringGenerator.randomToken(25, username);
                        //DAO add cookie
                        Cookie cookie = new Cookie("selector", token);
                        cookie.setMaxAge(60 * 60 * 24 * 2);
                        response.addCookie(cookie);
                        AccountDAO.updateTokenByUserName(token, username);
                    }
                }
                session.setAttribute("CURRENT_PAGE", "dashboard");
            } else request.setAttribute("WARNING", "Tài khoản của bạn đã bị khóa hoặc chưa được kích hoạt!");
            if (account == null)
                request.setAttribute("WARNING", "Sai tài khoản hoặc mật khẩu. Vui lòng kiểm tra lại!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (account != null && account.getStatus() == 1) {
                response.sendRedirect(url);
            } else {
                request.getRequestDispatcher(url).forward(request, response);
            }
        }
    }
}
