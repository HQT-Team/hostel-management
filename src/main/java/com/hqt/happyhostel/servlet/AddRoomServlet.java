package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dao.RoomDAO;
import com.hqt.happyhostel.dto.Hostel;
import com.hqt.happyhostel.dto.Room;
import com.hqt.happyhostel.dto.ServiceInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AddRoomServlet", value = "/AddRoomServlet")
public class AddRoomServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "ListHostelPage";
        try {
            int hostelId = Integer.parseInt(request.getParameter("hostelID"));
            Hostel hostel = new HostelDAO().getHostelById(hostelId);
            if (hostel != null) {
                url = "AddRoomPage";
                request.setAttribute("hostel", hostel);
            }
        } catch (Exception e) {
            log("Error at AddRoomPageServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "login.jsp";
        int quantityRoom = Integer.parseInt(request.getParameter("room-quantity"));
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
            int hostelId = Integer.parseInt(request.getParameter("hostelID"));
            Hostel hostel = new HostelDAO().getHostelById(hostelId);
            request.setAttribute("hostel", hostel);
            if (quantityRoom > 1) {
                for (int i = 0; i < quantityRoom; i++) {
                    boolean isSuccess = RoomDAO.addNewManyRooms(hostelId, capacity, roomArea, attic, 1,
                            restrooms, restroomStatus,
                            windows, windowsStatus,
                            roomDoors, roomDoorsStatus,
                            airConditions, airConditionsStatus);
                    url = "AddRoomPage";
                    if (isSuccess) {
                        request.setAttribute("isSuccess", "true");
                    } else {
                        request.setAttribute("isSuccess", "false");
                        break;
                    }
                }
            } else {
                int roomNumber = Integer.parseInt(request.getParameter("room-name"));

                boolean isSuccess = RoomDAO.addNewRoom(hostelId, roomNumber, capacity, roomArea, attic, 1,
                        restrooms, restroomStatus,
                        windows, windowsStatus,
                        roomDoors, roomDoorsStatus,
                        airConditions, airConditionsStatus);
                url = "AddRoomPage";
                if (isSuccess) {
                    request.setAttribute("isSuccess", "true");
                } else {
                    request.setAttribute("isSuccess", "false");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {


            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
