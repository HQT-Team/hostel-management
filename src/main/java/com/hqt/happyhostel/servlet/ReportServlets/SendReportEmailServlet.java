package com.hqt.happyhostel.servlet.ReportServlets;

import com.google.gson.Gson;
import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dao.ReportDAO;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.dto.Report;
import com.hqt.happyhostel.utils.MailUtils;
import com.hqt.happyhostel.utils.RandomStringGenerator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet(name = "SendReportEmailServlet", value = "/SendReportEmailServlet")
public class SendReportEmailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accId = (request.getAttribute("ACCOUNT_ID") != null) ?
                (int) request.getAttribute("ACCOUNT_ID") : Integer.parseInt(request.getParameter("account_id"));
        int reportID = Integer.parseInt(request.getParameter("report_id"));
        String userEmail = null;
        HandlerStatus handlerStatus = null;
        try {
            Report report = new ReportDAO().getReportById(reportID);
            userEmail = new AccountDAO().getAccountInformationById(accId).getInformation().getEmail();
            if (userEmail != null) {
                String domain = "http://localhost:8080/HappyHostel/report-detail?reportId="+reportID;
                if (new MailUtils().SendMailReport(userEmail, domain)) {
                    handlerStatus = HandlerStatus.builder().status(true).content("Mail đã được gửi thành công. Vui lòng kiểm tra Email của bạn.").build();
                } else {
                    handlerStatus = HandlerStatus.builder().status(false).content("Không thể gửi Mail. Vui lòng kiểm tra lại các thông tin.").build();
                }
                request.setAttribute("RESPONSE_MSG", handlerStatus);

                Gson gson = new Gson();
                String json = gson.toJson(handlerStatus);
                response.setCharacterEncoding("UTF8");
                response.setContentType("application/json");
                response.getWriter().println(json);
            }
        } catch (Exception e) {
            log("Error at SendOTPServlet: " + e.toString());
        }
    }
}
