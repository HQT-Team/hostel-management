package com.hqt.happyhostel.servlet.AccountServlets;


import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.utils.RandomStringGenerator;
import com.hqt.happyhostel.utils.SecurityUtils;


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
        AccountDAO accountDAO = new AccountDAO();
        String username = request.getParameter("txtemail");
        String password = SecurityUtils.hashMd5(request.getParameter("txtpassword"));
        String save = request.getParameter("savelogin");
        Account account = null;
        try {
            account = accountDAO.getAccountByUsernameAndPassword(username, password);
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
                        accountDAO.updateTokenByUserName(token, username);
                    }
                }
                session.setAttribute("CURRENT_PAGE", "dashboard");
            } else if (account != null && account.getStatus() == 0) {
                request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                                                        .status(false)
                                                        .content("Tài khoản của bạn đã bị khóa hoặc chưa được kích hoạt!").build());
            }
            else {
                request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                                                        .status(false)
                                                        .content("Sai tài khoản hoặc mật khẩu. Vui lòng kiểm tra lại!").build());
            }
        } catch (Exception e) {
            log("Error at LoginServlet: " + e.toString());
        } finally {
            if (account != null && account.getStatus() == 1) {
                response.sendRedirect(url);
            } else {
                request.getRequestDispatcher(url).forward(request, response);
            }
        }
    }
}
