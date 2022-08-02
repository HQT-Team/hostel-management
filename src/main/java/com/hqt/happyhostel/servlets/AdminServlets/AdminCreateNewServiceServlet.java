package com.hqt.happyhostel.servlets.AdminServlets;

import com.hqt.happyhostel.dao.ServicesDAO;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.dto.Services;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminCreateNewServiceServlet", value = "/AdminCreateNewServiceServlet")
public class AdminCreateNewServiceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("denied");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HandlerStatus handlerStatus;
            String serviceName = request.getParameter("serviceName").trim();
            String serviceUnit = request.getParameter("serviceUnit").trim();

            Services service = new ServicesDAO().getServiceByName(serviceName);
            if (service == null) {
                boolean insertResult = new ServicesDAO().createNewService(Services.builder().serviceName(serviceName).unit(serviceUnit).build());
                if (insertResult) {
                    handlerStatus = HandlerStatus.builder()
                            .status(true)
                            .content("Tạo mới dịch vụ thành công!").build();
                } else {
                    handlerStatus = HandlerStatus.builder()
                            .status(false)
                            .content("Đã có lỗi xảy ra! Tạo mới dịch vụ thất bại!").build();
                }
            } else {
                handlerStatus = HandlerStatus.builder()
                        .status(false)
                        .content("Tạo mới thất bại! Tên dịch vụ đã tồn tại! Vui lòng thử lại tên khác!").build();
            }
            request.setAttribute("RESPONSE_MSG", handlerStatus);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher("manage-service").forward(request, response);
        }
    }
}
