package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dao.RoomDAO;
import com.hqt.happyhostel.dto.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UpdateRoomServlet", value = "/UpdateRoomServlet")
public class UpdateRoomServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roomID = Integer.parseInt(request.getParameter("idRoom"));
        String url = "UpdateRoomPage";
        try {
            Room room = RoomDAO.getRoomInformationByRoomID(roomID);
            request.setAttribute("roomInformation", room);
            ArrayList<Infrastructures> infrastructures = RoomDAO.getInfrastructures(roomID);
            request.setAttribute("infrastructures", infrastructures);
            request.setAttribute("idRoom", roomID);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "UpdateRoomPage";
        int roomID = Integer.parseInt(request.getParameter("idRoom"));
        int roomNumber = Integer.parseInt(request.getParameter("room-name"));
        int capacity = Integer.parseInt(request.getParameter("room-capacity"));
        double roomArea = Double.parseDouble(request.getParameter("room-area"));
        int attic = Integer.parseInt(request.getParameter("room-floor"));
        int restrooms = Integer.parseInt(request.getParameter("room-toilet"));
        int restroomStatus = Integer.parseInt(request.getParameter("room-toilet-status"));
        int windows = Integer.parseInt(request.getParameter("room-window"));
        int windowsStatus = Integer.parseInt(request.getParameter("room-window-status")); 
        int airConditions = Integer.parseInt(request.getParameter("room-air-conditioner"));
        int airConditionsStatus = Integer.parseInt(request.getParameter("room-air-conditioner-status"));
        int roomDoors = Integer.parseInt(request.getParameter("room-door"));
        int roomDoorsStatus = Integer.parseInt(request.getParameter("room-door-status"));
        try {
            Boolean isSuccessUpdate = RoomDAO.updateRoom(roomID, roomNumber, capacity, roomArea, attic,
                    restrooms, restroomStatus,
                    windows, windowsStatus,
                    roomDoors, roomDoorsStatus,
                    airConditions, airConditionsStatus);

            if (isSuccessUpdate) {
                request.setAttribute("updateSuccess", true);
            } else {
                request.setAttribute("updateSuccess", false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
