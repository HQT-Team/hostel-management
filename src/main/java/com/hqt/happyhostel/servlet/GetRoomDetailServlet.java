package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dao.RoomDAO;
import com.hqt.happyhostel.dto.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GetRoomDetailServlet", value = "/GetRoomDetailServlet")
public class GetRoomDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roomId = Integer.parseInt(request.getParameter("roomID"));
        String url = "RoomDetailPage";
        try {
            Room room = RoomDAO.getRoomInformationByRoomID(roomId);
            request.setAttribute("roomInformation", room);
            Contract contract = RoomDAO.getContract(roomId);
            request.setAttribute("contractRoom", contract);
            Consume consume = RoomDAO.getNearestConsume(roomId);
            request.setAttribute("consumeRoom", consume);
            Bill bill = RoomDAO.getLastBill(roomId);
            request.setAttribute("billRoom", bill);
            ArrayList<RoommateInfo> roommateInfo = RoomDAO.getRoommateInformation(roomId);
            request.setAttribute("roommateInfo", roommateInfo);
            String username = AccountDAO.getUsernameRoomCurrently(roomId);
            request.setAttribute("userNameRenterRoom", username);
            ArrayList<Infrastructures> infrastructures = RoomDAO.getInfrastructures(roomId);
            request.setAttribute("infrastructures", infrastructures);
            int quantityMember = RoomDAO.getQuantityMember(roomId);
            request.setAttribute("quantityMember", quantityMember);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int roomId = Integer.parseInt(request.getParameter("roomID"));
        int roomId = (Integer) request.getAttribute("roomID");
        String url = "RoomDetailPage";
        try {
            Room room = RoomDAO.getRoomInformationByRoomID(roomId);
            request.setAttribute("roomInformation", room);
            Contract contract = RoomDAO.getContract(roomId);
            request.setAttribute("contractRoom", contract);
            Consume consume = RoomDAO.getNearestConsume(roomId);
            request.setAttribute("consumeRoom", consume);
            Bill bill = RoomDAO.getLastBill(roomId);
            request.setAttribute("billRoom", bill);
            ArrayList<RoommateInfo> roommateInfo = RoomDAO.getRoommateInformation(roomId);
            request.setAttribute("roommateInfo", roommateInfo);
            String username = AccountDAO.getUsernameRoomCurrently(roomId);
            request.setAttribute("userNameRenterRoom", username);
            ArrayList<Infrastructures> infrastructures = RoomDAO.getInfrastructures(roomId);
            request.setAttribute("infrastructures", infrastructures);
            int quantityMember = RoomDAO.getQuantityMember(roomId);
            request.setAttribute("quantityMember", quantityMember);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
