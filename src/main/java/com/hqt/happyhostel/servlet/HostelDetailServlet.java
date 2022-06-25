package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dao.RoomDAO;
import com.hqt.happyhostel.dao.ServiceInfoDAO;
import com.hqt.happyhostel.dao.ServicesDAO;
import com.hqt.happyhostel.dto.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HostelDetailServlet", value = "/HostelDetailServlet")
public class HostelDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "list-hostels";
        Account acc;

        try {
            HttpSession session = request.getSession();
            acc = (Account) session.getAttribute("USER");
            int accountId = acc.getAccId();

            int hostelId = Integer.parseInt(request.getParameter("hostelID"));

            Hostel hostel = new HostelDAO().getHostelByIdWithConstraint(hostelId, accountId);

            RoomDAO roomDao = new RoomDAO();

            if (hostel == null) {
                url = "list-hostels";
            } else {
                List<Room> rooms = roomDao.getListRoomsByHostelId(hostelId);
                int numberRoom = roomDao.getNumberRoomSpecificHostel(hostelId);

                List<ServiceInfo> serviceList = new ServiceInfoDAO().getServicesOfHostel(hostelId);

                List<Services> servicesNotInHostel = new ServicesDAO().getListServicesNotInHostel(hostelId);

                url = "HostelDetailPage";
                request.setAttribute("hostel", hostel);
                session.setAttribute("hostel", hostel);
                request.setAttribute("roomList", rooms);
                request.setAttribute("roomQuantity", numberRoom);
                request.setAttribute("serviceInfo", serviceList);
                request.setAttribute("services", servicesNotInHostel);
            }

        } catch (Exception e) {
            log("Error at HostelDetailServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
