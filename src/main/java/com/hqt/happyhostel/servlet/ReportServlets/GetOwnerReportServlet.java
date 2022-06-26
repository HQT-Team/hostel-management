package com.hqt.happyhostel.servlet.ReportServlets;

import com.google.gson.Gson;
import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dao.ReportDetailDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.Hostel;
import com.hqt.happyhostel.dto.ReportDetail;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetOwnerReportServlet", value = "/GetOwnerReportServlet")
public class GetOwnerReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "owner-report-page";
        int type = request.getParameter("type") == null ? 0 : Integer.parseInt(request.getParameter("type"));
        try {
            HttpSession session = request.getSession();
            Account hostelAccount = (Account)session.getAttribute("USER");
            int hostelOwnerId = hostelAccount.getAccId();

            List<ReportDetail> reportDetailList = new ReportDetailDAO().getListReports(hostelOwnerId, "", "", "", "");

            List<Hostel> hostelList = new HostelDAO().getHostelByOwnerId(hostelOwnerId);

            session.setAttribute("CURRENT_PAGE", "report");
            request.setAttribute("TYPE", type);
            request.setAttribute("HOSTEL_LIST", hostelList);
            request.setAttribute("REPORT_DETAIL_LIST", reportDetailList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "owner-report-page";
        try {
            String hostelId = request.getParameter("hostelId");
            String roomId = request.getParameter("roomId");
            String status = request.getParameter("status");

            Gson gson = new Gson();
            String json = gson.toJson(hostelId + roomId + status);
            response.getWriter().println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
