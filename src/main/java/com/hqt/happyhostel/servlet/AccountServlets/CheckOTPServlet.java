package com.hqt.happyhostel.servlet.AccountServlets;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dto.HandlerStatus;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CheckOTPServlet", value = "/CheckOTPServlet")
public class CheckOTPServlet extends HttpServlet {
    private final String SUCCESS = "get-contract-of-account";
    private final String FAIL = "otp-page";
    private final String ERROR = "error-page";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;
        HandlerStatus handlerStatus = null;
        String otp = request.getParameter("otp");
        String userEmail = request.getParameter("user_email");
        try {
            if (otp != null && !otp.isBlank() && userEmail != null) {
                int accId = new AccountDAO().checkAccountByOTP(userEmail, otp);
                if (accId > 0) {
                    url = SUCCESS;
                    request.setAttribute("ACC_ID", accId);
                    request.setAttribute("IS_REGISTER", "YES");
                } else {
                    url = FAIL;
                    handlerStatus = HandlerStatus.builder().status(false).content("Mã OTP không hợp lệ").build();
                    request.setAttribute("RESPONSE_MSG", handlerStatus);
                }
                request.setAttribute("USER_EMAIL", userEmail);
            }
        } catch (Exception e) {
            log("Error at CheckOTPServlet: " + e.toString());
        }finally {
            if(ERROR.equalsIgnoreCase(url)) response.sendRedirect(url);
            else request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
