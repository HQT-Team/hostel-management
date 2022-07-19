package com.hqt.happyhostel.servlet.OwnerServlet;

import com.google.gson.Gson;
import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dao.RoomDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.Hostel;
import com.hqt.happyhostel.dto.Room;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GetRoomListServlet", value = "/GetRoomListServlet")
public class GetRoomListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "RoomList";
        Account acc = null;

        try {
            HttpSession session = request.getSession();
            acc = (Account) session.getAttribute("USER");
            int accountId = acc.getAccId();
            RoomDAO roomDAO = new RoomDAO();
            List<Room> roomList = roomDAO.getListRoomsByHostelOwnerId(accountId);
            List<String> hostelListName = new ArrayList<>();
            for (Room room: roomList) {
                int hostelID = room.getHostelId();
                hostelListName.add(new HostelDAO().getHostelById(hostelID).getHostelName());
            }
            session.setAttribute("ROOM_LIST", roomList);
            session.setAttribute("HOSTEL_LIST_NAME", hostelListName);
            session.setAttribute("CURRENT_PAGE", "room");
            request.setAttribute("HOSTEL_LIST", new HostelDAO().getHostelByOwnerId(accountId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        try {
            HttpSession session = request.getSession();
            HostelDAO hostelDAO = new HostelDAO();
            RoomDAO roomDAO = new RoomDAO();
            Map<Integer, Hostel> hostelOfRooms = new HashMap<>();
            Account owner = (Account) session.getAttribute("USER");
            int ownerId = owner.getAccId();
            List<Room> roomList;

            String hostelIdStr = request.getParameter("hostelId");

            if (hostelIdStr.equals("")) {
                roomList = roomDAO.getListRoomsByHostelOwnerId(ownerId);
            } else {
                int hostelId = Integer.parseInt(hostelIdStr);
                roomList = roomDAO.getListRoomsByHostelId(hostelId);
            }

            for (Room room: roomList) {
                hostelOfRooms.put(room.getRoomId(), hostelDAO.getHostelByRoomId(room.getRoomId()));
            }

            List<Object> list = new ArrayList<>();
            list.add(roomList);
            list.add(hostelOfRooms);

            Gson gson = new Gson();
            response.getWriter().println(gson.toJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
