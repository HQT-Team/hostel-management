package com.hqt.happyhostel.servlet.RenterServlet;


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

@WebServlet(name = "GetReportCategoryListServlet", value = "/GetReportCategoryListServlet")
public class GetReportCategoryListServlet extends HttpServlet {
    private static final String SUCCESS = "Renter-report-page";
    private static final String ERROR = "Renter-report-page";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = ERROR;
        List<ReportCategory> reportCategories = new ArrayList<>();
        try {
            ReportCategoryDAO reportCategoryDAO = new ReportCategoryDAO();
            reportCategories = reportCategoryDAO.getReportCategory();
            if (reportCategories.size()>0){
                req.setAttribute("REPORT_CATE", reportCategories);
                url = SUCCESS;
            }
        }catch (Exception e){
            log("Error at GetReportCategoryListServlet: " + e.toString());
        }finally {
            req.getRequestDispatcher(url).forward(req,resp);
        }
    }
}
