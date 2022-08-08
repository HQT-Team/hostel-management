package com.hqt.happyhostel.servlets.AdminServlets;

import com.hqt.happyhostel.dao.InfrastructureItemDAO;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.dto.InfrastructureItem;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminCreateNewInfrastructureServlet", value = "/AdminCreateNewInfrastructureServlet")
public class AdminCreateNewInfrastructureServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("denied");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HandlerStatus handlerStatus;
            String infrastructureName = request.getParameter("infrastructureName").trim();
            InfrastructureItem infrastructureItem = new InfrastructureItemDAO().getInfrastructureByName(infrastructureName);
            if (infrastructureItem == null) {
                boolean createResult = new InfrastructureItemDAO().createNewInfrastructureItem(infrastructureName);
                if (createResult) {
                    handlerStatus = HandlerStatus.builder()
                            .status(true)
                            .content("Tạo mới cơ sở vật chất thành công!").build();
                } else {
                    handlerStatus = HandlerStatus.builder()
                            .status(false)
                            .content("Đã có lỗi xảy ra! Tạo mới cơ sở vật chất thất bại!").build();
                }
            } else {
                handlerStatus = HandlerStatus.builder()
                        .status(false)
                        .content("Loại cơ sở vật chất này đã tồn tại! Vui lòng thử lại với tên khác!").build();
            }
            request.setAttribute("RESPONSE_MSG", handlerStatus);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher("manage-infrastructure").forward(request, response);
        }
    }
}
