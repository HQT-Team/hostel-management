package com.hqt.happyhostel.servlet.RenterServlets;

import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dao.ReportCategoryDAO;
import com.hqt.happyhostel.dao.ReportDAO;
import com.hqt.happyhostel.dto.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddReportServlet", value = "/AddReportServlet")
public class AddReportServlet extends HttpServlet {
    public static final String ERROR = "Renter-report-page";
    public static final String SUCCESS = "Renter-report-page";
//    public static final String SUCCESS = "Renter-view-report";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = ERROR;

        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String sendDate = dateObj.format(formatter);
        Account acc = new Account();


        try {
            req.setCharacterEncoding("UTF-8");
            HttpSession session = req.getSession();
            acc = (Account) session.getAttribute("USER");

            int accountId = acc.getAccId();
            int cateID = Integer.parseInt(req.getParameter("cateID"));
            Hostel hostel = new HostelDAO().getHostelByRenterId(accountId);
            int ownerID = hostel.getHostelOwnerAccountID();
            String content = req.getParameter("content-report");
            Report report = Report.builder()
                    .sendDate(sendDate)
                    .content(content)
                    .sendAccountID(accountId)
                    .status(0)
                    .replyAccountID(ownerID)
                    .cateID(cateID)
                    .build();
            //Add report
            boolean checkInsert = new ReportDAO().addReport(report);
            if (checkInsert){
                req.setAttribute("SUCCESS", "Bạn đã gửi đi báo cáo thành công");
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at AddReportServlet: " + e.toString());
        } finally {
            req.getRequestDispatcher(url).forward(req, resp);
        }
    }
}
