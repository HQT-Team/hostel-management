package com.hqt.happyhostel.servlet.ReportServlets;

import com.hqt.happyhostel.dao.ReportDAO;
import com.hqt.happyhostel.dto.HandlerStatus;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateReportServlet", value = "/UpdateReportServlet")
public class UpdateReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "report-detail?reportId=";
        try {
            String action = request.getParameter("action");
            int reportId = Integer.parseInt(request.getParameter("reportId"));
            if (action.equals("reply")) {
                String responseMsg = request.getParameter("response");
                boolean updateResult = new ReportDAO().updateReportToProcess(reportId, responseMsg);
                if (updateResult) {
                    request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                            .status(true)
                            .content("Xác nhận và đưa báo cáo vào trạng thái đang xử lý thành công!").build());
                } else {
                    request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                            .status(false)
                            .content("Đã có lỗi xảy ra! Vui lòng thử lại sau!").build());
                }
            } else if (action.equals("finished")) {
                boolean updateResult = new ReportDAO().updateReportToFinished(reportId);
                if (updateResult) {
                    request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                            .status(true)
                            .content("Xác nhận và hoàn thành báo cáo thành công!").build());
                } else {
                    request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                            .status(false)
                            .content("Đã có lỗi xảy ra! Vui lòng thử lại sau!").build());
                }
            } else {
                request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                        .status(false)
                        .content("Đã có lỗi xảy ra! Vui lòng thử lại sau!").build());
            }
            url += reportId;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}