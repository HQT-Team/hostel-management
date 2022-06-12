package com.hqt.happyhostel.servlet.InfrastructureServlet;

import com.hqt.happyhostel.dao.InfrastructureDAO;
import com.hqt.happyhostel.dao.RoomDAO;
import lombok.extern.java.Log;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "UpdateInfrastructureStatusServlet", value = "/UpdateInfrastructureStatusServlet")
public class UpdateInfrastructureStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean updateWrong = false;
        String url = "list-hostels";
        try {
            List<String> statusList = Arrays.asList(request.getParameterValues("status"));
            List<String> idListInfrastructureRoom = Arrays.asList(request.getParameterValues("infrastructureId"));
            int roomID = Integer.parseInt(request.getParameter("roomID"));
            int count = 0;
            for (String id: idListInfrastructureRoom) {
                int idInfrastructure = Integer.parseInt(id);
                int status = Integer.parseInt(statusList.get(count));
                Boolean update = InfrastructureDAO.updateInfrastructureStatus(idInfrastructure, status);
                if (!update) {
                    url = "list-hostels";
                    request.setAttribute("roomID", roomID);
                    request.setAttribute("updateSuccess", false);
                    break;
                } else {
                    request.setAttribute("roomID", roomID);
                    url = "roomDetail";
                    request.setAttribute("updateSuccess", true);
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
