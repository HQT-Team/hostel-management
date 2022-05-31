package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.RoomDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddRoomServlet", value = "/AddRoomServlet")
public class AddRoomServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "AddRoomPage";
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
//        int quantityRoom = Integer.parseInt(request.getParameter("txtQuantityRoom"));
//        int capacity = Integer.parseInt(request.getParameter("txtCapacity"));
//        int attic = Integer.parseInt(request.getParameter("txtAttic"));
//        double roomArea = Double.parseDouble(request.getParameter("txtRoomArea"));
//        int restrooms = Integer.parseInt(request.getParameter("txtNumberRestrooms"));
//        int windows = Integer.parseInt(request.getParameter("txtNumberWindows"));
//        int airConditions = Integer.parseInt(request.getParameter("txtNumberAirConditions"));

        try {
            if (quantityRoom > 1) {
                for (int i = 0; i < quantityRoom; i++) {
                    Boolean isSuccess = RoomDAO.addNewManyRooms(1, capacity, roomArea, attic,
                            "RestRoom", restrooms, 1,
                            "Windows", windows, 1,
                            "AirCondition", airConditions, 1);
                    url = "success";
                    if (isSuccess) {
                        request.setAttribute("isSuccess", "true");
                    } else {
                        request.setAttribute("isSuccess", "false");
                        break;
                    }
                }
            } else {
                int roomNumber = Integer.parseInt(request.getParameter("room-name"));

                Boolean isSuccess = RoomDAO.addNewRoom(1, roomNumber, capacity, roomArea, 1,
                        "RestRoom", restrooms, 1,
                        "Windows", windows, 1,
                        "AirCondition", airConditions, 1);
                url = "success";
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
