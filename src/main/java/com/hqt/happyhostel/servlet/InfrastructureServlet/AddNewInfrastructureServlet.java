package com.hqt.happyhostel.servlet.InfrastructureServlet;

import com.hqt.happyhostel.dao.InfrastructureDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "AddNewInfrastructureServlet", value = "/AddNewInfrastructureServlet")
public class AddNewInfrastructureServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "list-hostels";
        try {
            int idNewInfrastructure = Integer.parseInt(request.getParameter("idNewInfrastructure"));
            int statusNewInfrastructure = Integer.parseInt(request.getParameter("statusNewInfrastructure"));
            int roomID = Integer.parseInt(request.getParameter("roomID"));
            Boolean insertStatus = InfrastructureDAO.addNewInfrastructure(roomID, 1, statusNewInfrastructure, idNewInfrastructure);
            if (insertStatus) {
                request.setAttribute("roomID", roomID);
                request.setAttribute("updateSuccess", true);
                url = "roomDetail";
            } else {
                request.setAttribute("roomID", roomID);
                request.setAttribute("updateSuccess", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
