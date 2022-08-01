package com.hqt.happyhostel.servlets.ReportServlets;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dao.ReportDetailDAO;
import com.hqt.happyhostel.dto.ReportDetail;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GetReportDetailServlet", value = "/GetReportDetailServlet")
public class GetReportDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "report-detail-page";
        try {
            int reportId = Integer.parseInt(request.getParameter("reportId"));

            ReportDetail reportDetail = new ReportDetailDAO().getReportDetailById(reportId);
            request.setAttribute("reportDetail", reportDetail);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
