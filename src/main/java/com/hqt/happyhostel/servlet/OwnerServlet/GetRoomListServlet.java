package com.hqt.happyhostel.servlet.OwnerServlet;

import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dao.InvoiceDAO;
import com.hqt.happyhostel.dao.RoomDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.Bill;
import com.hqt.happyhostel.dto.Room;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            Set<String> hostelListNameDropDown = new HashSet<>();
            for (Room room: roomList) {
                int hostelID = room.getHostelId();
                hostelListNameDropDown.add(new HostelDAO().getHostelById(hostelID).getHostelName());
                hostelListName.add(new HostelDAO().getHostelById(hostelID).getHostelName());
            }
            session.setAttribute("ROOM_LIST", roomList);
            session.setAttribute("HOSTEL_DROP_DOWN_NAME", hostelListNameDropDown);
            session.setAttribute("HOSTEL_LIST_NAME", hostelListName);
            session.setAttribute("CURRENT_PAGE", "room");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
