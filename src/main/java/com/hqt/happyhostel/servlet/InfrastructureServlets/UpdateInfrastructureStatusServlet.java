package com.hqt.happyhostel.servlet.InfrastructureServlets;

import com.hqt.happyhostel.dao.InfrastructureDAO;
import com.hqt.happyhostel.dto.HandlerStatus;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateInfrastructureStatusServlet", value = "/UpdateInfrastructureStatusServlet")
public class UpdateInfrastructureStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "roomDetail";

        InfrastructureDAO infrastructureDAO = new InfrastructureDAO();

        try {
            String[] statusList = request.getParameterValues("status");
            String[] idListInfrastructureRoom = request.getParameterValues("infrastructureId");
            int roomID = Integer.parseInt(request.getParameter("roomID"));
            int count = 0;
            for (String id : idListInfrastructureRoom) {
                int idInfrastructure = Integer.parseInt(id);
                int status = Integer.parseInt(statusList[count]);
                boolean update = infrastructureDAO.updateInfrastructureStatus(idInfrastructure, status);
                if (!update) {
                    request.setAttribute("roomID", roomID);
                    request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                            .status(false)
                            .content("Đã có lỗi xảy ra! Cập nhật cơ sở vật chất thất bại!").build());
                    break;
                } else {
                    request.setAttribute("roomID", roomID);
                    request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                            .status(true)
                            .content("Cập nhật cơ sở vật chất thành công!").build());
                    count++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }
}
