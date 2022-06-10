package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dao.RoomDAO;
import com.hqt.happyhostel.dto.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HostelDetailServlet", value = "/HostelDetailServlet")
public class HostelDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "ListHostelPage";
        Account acc = new Account();

        try {
            HttpSession session = request.getSession();
            acc = (Account) session.getAttribute("USER");
            int accountId = acc.getAccId();

            int hostelId = Integer.parseInt(request.getParameter("hostelID"));

            Hostel hostel = new HostelDAO().getHostelByIdWithConstraint(hostelId, accountId);
            if (hostel == null) {
                url = "list-hostels";
            } else {
                ArrayList<Room> rooms = RoomDAO.getListRoomByHostelID(hostelId);
                int numberRoom = RoomDAO.getNumberRoomSpecificHostel(hostelId);

                ArrayList<Integer> quantityMembers = new ArrayList<>();
                for (Room roomItem : rooms) {
                    int quantityMember = RoomDAO.getQuantityMember(roomItem.getRoomId());
                    quantityMembers.add(quantityMember);
                }

                ArrayList<ServiceInfo> serviceList = RoomDAO.getServicesOfHostel(hostelId);
                url = "HostelDetailPage";
                request.setAttribute("hostel", hostel);
                session.setAttribute("hostel", hostel);
                request.setAttribute("roomList", rooms);
                request.setAttribute("roomQuantity", numberRoom);
                request.setAttribute("serviceInfo", serviceList);
                request.setAttribute("quantityMembers", quantityMembers);
            }

        } catch (Exception e) {
            log("Error at HostelDetailServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
