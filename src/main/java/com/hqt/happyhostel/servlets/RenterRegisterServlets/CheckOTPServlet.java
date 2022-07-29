package com.hqt.happyhostel.servlets.RenterRegisterServlets;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dto.HandlerStatus;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CheckOTPServlet", value = "/CheckOTPServlet")
public class CheckOTPServlet extends HttpServlet {
    private final String SUCCESS = "get-contract-of-account";
    private final String FAIL = "verify-renter-page";
    private final String ERROR = "error-page";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(ERROR);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;
        String otp = request.getParameter("otp");
        int accId = Integer.parseInt(request.getParameter("account_id"));
        HandlerStatus handlerStatus = null;
        String userEmail = null;

        try {
            userEmail = new AccountDAO().getAccountInformationById(accId).getInformation().getEmail();
            if (otp != null && !otp.isBlank() && userEmail != null) {
                int accID = new AccountDAO().checkAccountByOTP(accId, otp);
                if (accID > 0) {
                    request.setAttribute("ACCOUNT_ID", accID);
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        session.setAttribute("confirm-OTP", "OK");
                        new AccountDAO().updateAccountOTP(accId, null, null);
                        url = SUCCESS;
                    }

                } else {
                    url = FAIL;
                    handlerStatus = HandlerStatus.builder().status(false).content("Mã OTP không hợp lệ! Vui lòng kiểm tra lại email hoặc nhấn nút gửi lại để nhận mã mới!").build();
                    request.setAttribute("RESPONSE_MSG", handlerStatus);
                    request.setAttribute("ACCOUNT_ID", accId);
                }
            }
        } catch (Exception e) {
            log("Error at CheckOTPServlet: " + e.toString());
        } finally {
            if (ERROR.equalsIgnoreCase(url)) response.sendRedirect(url);
            else request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
