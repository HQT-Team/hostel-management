package com.hqt.happyhostel.servlets.AdminServlets;

import com.hqt.happyhostel.dao.ServicesDAO;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.dto.Services;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminUpdateServiceServlet", value = "/AdminUpdateServiceServlet")
public class AdminUpdateServiceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("denied");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HandlerStatus handlerStatus;
            int serviceId = Integer.parseInt(request.getParameter("serviceId"));
            String serviceName = request.getParameter("serviceName").trim();
            String serviceUnit = request.getParameter("serviceUnit").trim();

            Services service = new ServicesDAO().getServiceByName(serviceName);
            if (service == null) {
                boolean updateResult = new ServicesDAO().Update(Services.builder().serviceID(serviceId).serviceName(serviceName).unit(serviceUnit).build());
                if (updateResult) {
                    handlerStatus = HandlerStatus.builder()
                            .status(true)
                            .content("Bạn đã cập nhật thông tin dịch vụ thành công!").build();
                } else {
                    handlerStatus = HandlerStatus.builder()
                            .status(false)
                            .content("Đã có lỗi xảy ra! Cập nhận thông tin dịch vụ thất bại!").build();
                }
            } else {
                handlerStatus = HandlerStatus.builder()
                        .status(false)
                        .content("Tên dịch vụ bị trùng lặp! Vui lòng thử lại tên khác!").build();
            }
            request.setAttribute("RESPONSE_MSG", handlerStatus);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher("manage-service").forward(request, response);
        }
    }
}
