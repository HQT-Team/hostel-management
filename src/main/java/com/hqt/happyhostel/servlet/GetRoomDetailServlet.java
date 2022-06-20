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

            Bill bill = new BillDAO().getLastBill(roomId);
            request.setAttribute("billRoom", bill);

            ArrayList<Payment> payments = new PaymentDAO().getPaymentList();
            request.setAttribute("paymentList", payments);

            if (bill != null) {
                int billID = bill.getBillID();
                BillDetail billDetail = new BillDAO().getBillDetail(billID);
                int consumeIDStart = billDetail.getConsumeIDStart();
                int consumeIDEnd = billDetail.getConsumeIDEnd();

                if (bill.getStatus() == 0 || bill.getPayment() != null) {
                    String paymentName = new BillDAO().getPaymentName(bill.getPayment().getPaymentID());
                    request.setAttribute("paymentName", paymentName);
                }

                Consume consumeStart = new ConsumeDAO().getConsumeByID(consumeIDStart);
                Consume consumeEnd = new ConsumeDAO().getConsumeByID(consumeIDEnd);

                request.setAttribute("consumeStart", consumeStart);
                request.setAttribute("consumeEnd", consumeEnd);

                int billDetailID = billDetail.getBillDetailID();
                ArrayList<ServiceInfo> serviceInfos = new ServicesDAO().getServiceOfBill(billDetailID, hostelID);
                request.setAttribute("serviceInfo", serviceInfos);

                int accountHOID = billDetail.getAccountHostelOwnerID();
                int accountRenterID = billDetail.getAccountRenterID();
                AccountInfo accountHOInfo = accountDAO.getAccountInformationById(accountHOID);
                AccountInfo accountRenterInfo = accountDAO.getAccountInformationById(accountRenterID);

                request.setAttribute("billMakerFullName", accountHOInfo.getInformation().getFullname());
                request.setAttribute("billPaymenterFullName", accountRenterInfo.getInformation().getFullname());
            }

            String username = accountDAO.getUsernameRoomCurrently(roomId);
            request.setAttribute("userNameRenterRoom", username);

            int accountId = accountDAO.getAccountIdByUserName(username);
            request.setAttribute("renterAccountId", accountId);

            List<RoommateInfo> listRoommatesInfo = new RoommateInfoDAO().getListRoommatesOfAnAccount(accountId);
            request.setAttribute("listRoommatesInfo", listRoommatesInfo);

            ArrayList<Infrastructures> infrastructures = infrastructureDAO.getInfrastructures(roomId);
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
        String url = "RoomDetailPage";

        try {
            HttpSession session = request.getSession();
            int hostelID = ((Hostel) session.getAttribute("hostel")).getHostelID();
            int accID = ((Account) session.getAttribute("USER")).getAccId();
            int roomId = ((Room) session.getAttribute("room")).getRoomId();

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

            Bill bill = new BillDAO().getLastBill(roomId);
            request.setAttribute("billRoom", bill);

            ArrayList<Payment> payments = new PaymentDAO().getPaymentList();
            request.setAttribute("paymentList", payments);

            if (bill != null) {
                int billID = bill.getBillID();
                BillDetail billDetail = new BillDAO().getBillDetail(billID);
                int consumeIDStart = billDetail.getConsumeIDStart();
                int consumeIDEnd = billDetail.getConsumeIDEnd();

                if (bill.getStatus() == 0 || bill.getPayment() != null) {
                    String paymentName = new BillDAO().getPaymentName(bill.getPayment().getPaymentID());
                    request.setAttribute("paymentName", paymentName);
                }

                Consume consumeStart = new ConsumeDAO().getConsumeByID(consumeIDStart);
                Consume consumeEnd = new ConsumeDAO().getConsumeByID(consumeIDEnd);

                request.setAttribute("consumeStart", consumeStart);
                request.setAttribute("consumeEnd", consumeEnd);

                int billDetailID = billDetail.getBillDetailID();
                ArrayList<ServiceInfo> serviceInfos = new ServicesDAO().getServiceOfBill(billDetailID, hostelID);
                request.setAttribute("serviceInfo", serviceInfos);

                int accountHOID = billDetail.getAccountHostelOwnerID();
                int accountRenterID = billDetail.getAccountRenterID();
                AccountInfo accountHOInfo = accountDAO.getAccountInformationById(accountHOID);
                AccountInfo accountRenterInfo = accountDAO.getAccountInformationById(accountRenterID);

                request.setAttribute("billMakerFullName", accountHOInfo.getInformation().getFullname());
                request.setAttribute("billPaymenterFullName", accountRenterInfo.getInformation().getFullname());
            }

            String username = accountDAO.getUsernameRoomCurrently(roomId);
            request.setAttribute("userNameRenterRoom", username);

            int accountId = accountDAO.getAccountIdByUserName(username);
            request.setAttribute("renterAccountId", accountId);

            List<RoommateInfo> listRoommatesInfo = new RoommateInfoDAO().getListRoommatesOfAnAccount(accountId);
            request.setAttribute("listRoommatesInfo", listRoommatesInfo);

            ArrayList<Infrastructures> infrastructures = infrastructureDAO.getInfrastructures(roomId);
            request.setAttribute("infrastructures", infrastructures);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
