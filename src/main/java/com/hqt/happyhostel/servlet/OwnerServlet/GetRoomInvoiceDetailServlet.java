package com.hqt.happyhostel.servlet.OwnerServlet;

import com.hqt.happyhostel.dao.*;
import com.hqt.happyhostel.dto.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetRoomInvoiceDetailServlet", value = "/GetRoomInvoiceDetailServlet")
public class GetRoomInvoiceDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "RoomInvoiceDetail";
        try {
            HttpSession session = request.getSession();
            int hostelID = ((Hostel) session.getAttribute("hostel")).getHostelID();
            int accID = ((Account) session.getAttribute("USER")).getAccId();
            int roomId = (request.getParameter("roomID") != null) ? Integer.parseInt(request.getParameter("roomID")) : (int) session.getAttribute("current_room_id");

            int billDetailID = Integer.parseInt(request.getParameter("billID"));

            Bill bill = new BillDAO().getBillByID(billDetailID);
            request.setAttribute("billRoom", bill);

            Contract contract = new ContractDAO().getContract(roomId);
            request.setAttribute("contractRoom", contract);

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

                int billDetailIDs = billDetail.getBillDetailID();
                List<ServiceInfo> serviceInfos = new ServiceInfoDAO().getServiceOfBill(billDetailIDs, hostelID);
                request.setAttribute("serviceInfo", serviceInfos);

                int accountHOID = billDetail.getAccountHostelOwnerID();
                int accountRenterID = billDetail.getAccountRenterID();
                AccountInfo accountHOInfo = new AccountDAO().getAccountInformationById(accountHOID);
                AccountInfo accountRenterInfo = new AccountDAO().getAccountInformationById(accountRenterID);

                request.setAttribute("billMakerFullName", accountHOInfo.getInformation().getFullname());
                request.setAttribute("billPaymenterFullName", accountRenterInfo.getInformation().getFullname());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
