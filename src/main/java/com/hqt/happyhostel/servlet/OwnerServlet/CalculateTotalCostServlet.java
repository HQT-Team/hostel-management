package com.hqt.happyhostel.servlet.OwnerServlet;

import com.hqt.happyhostel.dao.*;
import com.hqt.happyhostel.dto.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CalculateTotalCostServlet", value = "/CalculateTotalCostServlet")
public class CalculateTotalCostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "CalculateTotalCostRoomPage";
        try {
            HttpSession session = request.getSession();
            int hostelID = ((Hostel) session.getAttribute("hostel")).getHostelID();
            int accID = ((Account) session.getAttribute("USER")).getAccId();

            Room room = (Room) session.getAttribute("room");
            int roomId = room.getRoomId();

            RoomDAO roomDAO = new RoomDAO();
            AccountDAO accountDAO = new AccountDAO();

            Contract contract = new ContractDAO().getContract(roomId);
            request.setAttribute("contractRoom", contract);

            ArrayList<Consume> consumeThisMonth = roomDAO.getConsumeThisMonth(roomId);
            request.setAttribute("consumeListThisMonth", consumeThisMonth);

            ArrayList<ServiceInfo> serviceInfo = roomDAO.getServicesOfHostel(hostelID);
            request.setAttribute("serviceInfo", serviceInfo);

            String username = accountDAO.getUsernameRoomCurrently(roomId);
            request.setAttribute("userNameRenterRoom", username);

            int accountId = accountDAO.getAccountIdByUserName(username);
            request.setAttribute("renterAccountId", accountId);

            AccountInfo accountRenter = accountDAO.getAccountInformationById(accountId);
            request.setAttribute("renterName", accountRenter.getInformation().getFullname());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "CalculateTotalCostRoomPage";
        try {
            HttpSession session = request.getSession();
            int hostelID = ((Hostel) session.getAttribute("hostel")).getHostelID();
            int accHostelOwnerID = ((Account) session.getAttribute("USER")).getAccId();

            Room room = (Room) session.getAttribute("room");
            int roomId = room.getRoomId();

            RoomDAO roomDAO = new RoomDAO();
            AccountDAO accountDAO = new AccountDAO();

            Contract contract = new ContractDAO().getContract(roomId);
            request.setAttribute("contractRoom", contract);

            ArrayList<Consume> consumeThisMonth = roomDAO.getConsumeThisMonth(roomId);
            request.setAttribute("consumeListThisMonth", consumeThisMonth);

            ArrayList<ServiceInfo> serviceInfo = roomDAO.getServicesOfHostel(hostelID);

            String username = accountDAO.getUsernameRoomCurrently(roomId);
            request.setAttribute("userNameRenterRoom", username);

            int accountRenterId = accountDAO.getAccountIdByUserName(username);

            String expiredDateBill = request.getParameter("expiredDate");

            double totalCostBill = Double.parseDouble(request.getParameter("totalCost"));
            int totalCost = (int) totalCostBill;
            int consumeIDStart = consumeThisMonth.get(0).getConsumeID();
            int consumeIDEnd = consumeThisMonth.get(consumeThisMonth.size() - 1).getConsumeID();
            ArrayList<Integer> listHostelServiceID = new ArrayList<>();
            for (ServiceInfo service : serviceInfo) {
                listHostelServiceID.add(service.getHostelService());
            }
            int numberLastElectric = consumeThisMonth.get(0).getNumberElectric();
            int numberLastWater = consumeThisMonth.get(0).getNumberWater();

            boolean isInserted = new BillDAO().InsertANewBill(totalCost, expiredDateBill, roomId,
                    consumeIDStart, consumeIDEnd, accHostelOwnerID, accountRenterId, numberLastElectric, numberLastWater, listHostelServiceID);

            if (isInserted) {
                url = "roomDetail";
                request.setAttribute("roomID", roomId);
//                request.setAttribute("IS_SUCCESS", HandlerStatus.builder().status(true));
            } else {
                url = "list-hostels";
//                request.setAttribute("IS_SUCCESS", HandlerStatus.builder().status(false));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
