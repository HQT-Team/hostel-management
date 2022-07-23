package com.hqt.happyhostel.servlets.RenterServlets;

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
import java.util.List;

@WebServlet(name = "AddReportServlet", value = "/AddReportServlet")
public class AddReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<ReportCategory> reportCategories = new ReportCategoryDAO().getReportCategory();
            req.setAttribute("REPORT_CATE", reportCategories);
        } catch (Exception e) {
            log("Error at GetReportCategoryListServlet: " + e.toString());
        } finally {
            req.getRequestDispatcher("Renter-report-page").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String sendDate = dateObj.format(formatter);
        HandlerStatus handlerStatus;

        try {
            HttpSession session = req.getSession();
            Account acc = (Account) session.getAttribute("USER");

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
            int reportId = new ReportDAO().addReport(report);

            if (reportId > 0){
                req.setAttribute("SUCCESS", "Bạn đã gửi đi báo cáo thành công");
                req.setAttribute("HOSTEL_OWNER_ID", ownerID);
                req.setAttribute("REPORT_ID", reportId);
                handlerStatus = HandlerStatus.builder().status(true).content("Bạn đã gửi đi báo cáo thành công!").build();
            } else {
                handlerStatus = HandlerStatus.builder().status(false).content("Đã có lỗi xảy ra! Gửi báo cáo thất bại!").build();
            }

            List<ReportCategory> reportCategories = new ReportCategoryDAO().getReportCategory();
            req.setAttribute("REPORT_CATE", reportCategories);
            req.setAttribute("RESPONSE_MSG", handlerStatus);
        } catch (Exception e) {
            req.setAttribute("RESPONSE_MSG", HandlerStatus.builder().status(false).content("Đã có lỗi xảy ra! Gửi báo cáo thất bại!").build());
            log("Error at AddReportServlet: " + e.toString());
        } finally {
            req.getRequestDispatcher("Renter-report-page").forward(req, resp);
        }
    }
}
