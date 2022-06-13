package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.*;
import com.hqt.happyhostel.dto.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetRoomDetailServlet", value = "/GetRoomDetailServlet")
public class GetRoomDetailServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "RoomDetailPage";

        try {
            int roomId = Integer.parseInt(request.getParameter("roomID"));

            Room room = RoomDAO.getRoomInformationByRoomID(roomId);
            request.setAttribute("roomInformation", room);

            Contract contract = ContractDAO.getContract(roomId);
            request.setAttribute("contractRoom", contract);

            Consume consume = RoomDAO.getNearestConsume(roomId);
            request.setAttribute("consumeRoom", consume);

            Bill bill = RoomDAO.getLastBill(roomId);
            request.setAttribute("billRoom", bill);

            String username = AccountDAO.getUsernameRoomCurrently(roomId);
            request.setAttribute("userNameRenterRoom", username);

            int accountId = AccountDAO.getAccountIdByUserName(username);
            request.setAttribute("renterAccountId", accountId);

            List<RoommateInfo> listRoommatesInfo = RoommateInfoDAO.getListRoommatesOfAnAccount(accountId);
            request.setAttribute("listRoommatesInfo", listRoommatesInfo);

            ArrayList<Infrastructures> infrastructures = RoomDAO.getInfrastructures(roomId);
            request.setAttribute("infrastructures", infrastructures);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
