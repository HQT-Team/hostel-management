package com.hqt.happyhostel.servlet.RenterRegisterServlets;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.utils.MailUtils;
import com.hqt.happyhostel.utils.RandomStringGenerator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet(name = "SendOTPServlet", value = "/SendOTPServlet")
public class SendOTPServlet extends HttpServlet {
    private final String SUCCESS = "verify-renter-page";
    private final String FAIL = "verify-renter-page";
    private final String ERROR = "error-page";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accId = ( request.getAttribute("ACCOUNT_ID") != null ) ?
                (int) request.getAttribute("ACCOUNT_ID") : Integer.parseInt(request.getParameter("account_id"));
        String url = ERROR;
        String userEmail = null;
        HandlerStatus handlerStatus = null;

        try {
            userEmail = new AccountDAO().getAccountInformationById(accId).getInformation().getEmail();
            if (userEmail != null){
                String otp = RandomStringGenerator.randomOTP(5);
                String mailObject = "Mã xác nhận tài khoản";
                String mailBody = otp;
                if(MailUtils.sendOTPMail(userEmail, mailObject, mailBody)){
                    url = SUCCESS;

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Calendar startTime = Calendar.getInstance();
                    long timeInSecs = startTime.getTimeInMillis();
                    Timestamp endTime = new Timestamp(timeInSecs + (2 * 60 * 1000));

                    new AccountDAO().updateAccountOTP(accId, otp, sdf.format(endTime));
                    handlerStatus = HandlerStatus.builder().status(true).content("Mã OTP đã được gửi. Vui lòng kiểm tra Email của bạn.").build();
                }
                else {
                    url = FAIL;
                    handlerStatus = HandlerStatus.builder().status(false).content("Không thể gửi mã OTP. Vui lòng kiểm tra lại các thông tin.").build();
                }
                request.setAttribute("RESPONSE_MSG", handlerStatus);
                request.setAttribute("ACCOUNT_ID", accId);
            }
        }catch (Exception e){
            log("Error at SendOTPServlet: " + e.toString());
        }finally {
            if(ERROR.equalsIgnoreCase(url)) response.sendRedirect(url);
            else request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
