package com.hqt.happyhostel.servlets.RenterServlets;


import com.hqt.happyhostel.dao.ReportCategoryDAO;
import com.hqt.happyhostel.dao.ReportDAO;
import com.hqt.happyhostel.dto.Report;
import com.hqt.happyhostel.dto.ReportCategory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetReportServlet", value = "/GetReportServlet")
public class GetReportServlet extends HttpServlet {
    private static final String SUCCESS = "Renter-view-report";
    private static final String ERROR = "Renter-view-report";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = ERROR;
        List<ReportCategory> reportCategories = new ArrayList<>();
        List<Report> reports = new ArrayList<>();
        try {
            ReportCategoryDAO reportCategoryDAO = new ReportCategoryDAO();
            ReportDAO reportDAO = new ReportDAO();
            reportCategories = reportCategoryDAO.getReportCategory();
            req.setAttribute("uri", req.getRequestURI());
            if (reportCategories.size()>0){
                req.setAttribute("REPORT_CATE", reportCategories);
                url = SUCCESS;
                if (req.getParameter("id")!=null) {
                    req.setAttribute("id", req.getParameter("id"));
                    url = "Report-detail";
                }
            }

            reports = reportDAO.getReports();
            if(reports.size()>0){
                req.setAttribute("REPORT_LIST", reports);
                url = SUCCESS;
                if (req.getParameter("id")!=null) {
                    req.setAttribute("id", req.getParameter("id"));
                    url = "Report-detail";
                }
            }

        }catch (Exception e){
            log("Error at GetReportServlet: " + e.toString());
        }finally {
            req.getRequestDispatcher(url).forward(req,resp);
        }
    }
}
