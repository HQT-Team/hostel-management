package com.hqt.happyhostel.servlet.InfrastructureServlets;

import com.hqt.happyhostel.dao.InfrastructureDAO;
import com.hqt.happyhostel.dto.HandlerStatus;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddNewInfrastructureServlet", value = "/AddNewInfrastructureServlet")
public class AddNewInfrastructureServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "list-hostels";

        InfrastructureDAO infrastructureDAO = new InfrastructureDAO();

        try {
            int idNewInfrastructure = Integer.parseInt(request.getParameter("idNewInfrastructure"));
            int statusNewInfrastructure = Integer.parseInt(request.getParameter("statusNewInfrastructure"));
            int roomID = Integer.parseInt(request.getParameter("roomID"));
            boolean insertStatus = infrastructureDAO.addNewInfrastructure(roomID, 1, statusNewInfrastructure, idNewInfrastructure);
            if (insertStatus) {
                request.setAttribute("roomID", roomID);
                request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                        .status(true)
                        .content("Thêm cơ sở vật chất thành công!").build());
                url = "roomDetail";
            } else {
                request.setAttribute("roomID", roomID);
                request.setAttribute("RESPONSE_MSG", HandlerStatus.builder()
                        .status(false)
                        .content("Đã có lỗi xảy ra! Thêm cơ sở vật chất thất bại!").build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
