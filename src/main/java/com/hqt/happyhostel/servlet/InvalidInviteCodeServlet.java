package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.RoomDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "InvalidInviteCodeServlet", value = "/InvalidInviteCodeServlet")
public class InvalidInviteCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomId = request.getParameter("room_id");
        int roomID = Integer.parseInt(roomId);
        RoomDAO.updateRoomInviteCode(roomID, null, null, null);
        response.sendRedirect();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
