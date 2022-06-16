package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.RoomDAO;
import com.hqt.happyhostel.dto.Infrastructures;
import com.hqt.happyhostel.dto.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UpdateRoomServlet", value = "/UpdateRoomServlet")
public class UpdateRoomServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "roomDetail";
        int roomID = Integer.parseInt(request.getParameter("roomID"));
        int roomNumber = Integer.parseInt(request.getParameter("room-number"));
        int capacity = Integer.parseInt(request.getParameter("room-capacity"));
        double roomArea = Double.parseDouble(request.getParameter("room-area"));
        int attic = Integer.parseInt(request.getParameter("room-attic"));
        try {
            boolean isSuccessUpdate = new RoomDAO().updateRoom(roomID, roomNumber, capacity, roomArea, attic);

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
