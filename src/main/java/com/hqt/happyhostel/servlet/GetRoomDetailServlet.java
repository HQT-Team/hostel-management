package com.hqt.happyhostel.servlet;

import com.hqt.happyhostel.dao.*;
import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dao.ConsumeDAO;
import com.hqt.happyhostel.dao.InfrastructureDAO;
import com.hqt.happyhostel.dao.RoomDAO;
import com.hqt.happyhostel.dto.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetRoomDetailServlet", value = "/GetRoomDetailServlet")
public class GetRoomDetailServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "RoomDetailPage";

        try {
            HttpSession session = request.getSession();
            int hostelID = ((Hostel) session.getAttribute("hostel")).getHostelID();
            int accID = ((Account) session.getAttribute("USER")).getAccId();

            int roomId = Integer.parseInt(request.getParameter("roomID"));

            RoomDAO roomDAO = new RoomDAO();
            AccountDAO accountDAO = new AccountDAO();
            InfrastructureDAO infrastructureDAO = new InfrastructureDAO();

            Room room = roomDAO.getRoomInformationByRoomID(roomId, hostelID, accID);
            session.setAttribute("room", room);

            Contract contract = new ContractDAO().getContract(roomId);
            request.setAttribute("contractRoom", contract);

            List<Consume> consumeList = roomDAO.getConsumeHistory(roomId);
            request.setAttribute("consumeList", consumeList);

            Consume consumeNumber = new ConsumeDAO().getNearestConsume(roomId);
            request.setAttribute("consumeNumber", consumeNumber);

            List<InfrastructureItem> infrastructureItemList = infrastructureDAO.getAllInfrastructure();
            request.setAttribute("infrastructureList", infrastructureItemList);

            Bill bill = roomDAO.getLastBill(roomId);
            request.setAttribute("billRoom", bill);

            if (contract != null) {
                Account renterAccount = accountDAO.getAccountById(contract.getRenterId());
                request.setAttribute("renterAccount", renterAccount);

                List<RoommateInfo> listRoommatesInfo = new RoommateInfoDAO().getListRoommatesOfAnAccount(contract.getRenterId());
                request.setAttribute("listRoommatesInfo", listRoommatesInfo);
            }

            ArrayList<Infrastructures> infrastructures = infrastructureDAO.getRoomInfrastructures(roomId);
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
