package com.hqt.happyhostel.servlet.OwnerServlet;

import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dao.InvoiceDAO;
import com.hqt.happyhostel.dao.RoomDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.Bill;
import com.hqt.happyhostel.dto.Hostel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name = "GetInvoiceListServlet", value = "/GetInvoiceListServlet")
public class GetInvoiceListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "InvoiceList";
        Account acc = null;

        try {
            HttpSession session = request.getSession();
            acc = (Account) session.getAttribute("USER");
            int accountId = acc.getAccId();
            InvoiceDAO invoiceDAO = new InvoiceDAO();
            List<Bill> invoicePayment = invoiceDAO.getInvoiceListByOwnerAccountID(accountId, 1);
            List<String> roomNumberPayment = new ArrayList<>();
            List<String> hostelNamePayment = new ArrayList<>();
            List<Integer> hostelIDPayment = new ArrayList<>();
            Set<String> roomNumberDropDownListPayment = new HashSet<>();
            Set<String> hostelNameDropDownListPayment = new HashSet<>();
            for (Bill bill: invoicePayment) {
                int roomID = bill.getRoomID();
                int roomNumber = new RoomDAO().getRoomById(roomID).getRoomNumber();
                roomNumberPayment.add(String.valueOf(roomNumber));
                hostelNamePayment.add(new HostelDAO().getHostelByRoomId(roomID).getHostelName());
                hostelIDPayment.add(new HostelDAO().getHostelByRoomId(roomID).getHostelID());

                roomNumberDropDownListPayment.add(String.valueOf(roomNumber));
                hostelNameDropDownListPayment.add(new HostelDAO().getHostelByRoomId(roomID).getHostelName());
            }
            session.setAttribute("INVOICE_PAYMENT_LIST", invoicePayment);
            session.setAttribute("INVOICE_PAYMENT_LIST_ROOM_NUMBER", roomNumberPayment);
            session.setAttribute("INVOICE_PAYMENT_LIST_HOSTEL_NAME", hostelNamePayment);
            session.setAttribute("INVOICE_PAYMENT_LIST_HOSTEL_ID", hostelIDPayment);
            session.setAttribute("INVOICE_PAYMENT_LIST_DROP_DOWN_ROOM_NUMBER", roomNumberDropDownListPayment);
            session.setAttribute("INVOICE_PAYMENT_LIST_DROP_DOWN_HOSTEL_NAME", hostelNameDropDownListPayment);


            List<Bill> invoiceNotPayment = invoiceDAO.getInvoiceListByOwnerAccountID(accountId, 0);
            List<String> roomNumberNotPayment = new ArrayList<>();
            List<String> hostelNameNotPayment = new ArrayList<>();
            List<Integer> hostelIDNotPayment = new ArrayList<>();
            Set<String> roomNumberDropDownListNotPayment = new HashSet<>();
            Set<String> hostelNameDropDownListNotPayment = new HashSet<>();
            for (Bill bill: invoiceNotPayment) {
                int roomID = bill.getRoomID();
                int roomNumber = new RoomDAO().getRoomById(roomID).getRoomNumber();
                roomNumberNotPayment.add(String.valueOf(roomNumber));
                hostelNameNotPayment.add(new HostelDAO().getHostelByRoomId(roomID).getHostelName());
                hostelIDNotPayment.add(new HostelDAO().getHostelByRoomId(roomID).getHostelID());

                roomNumberDropDownListNotPayment.add(String.valueOf(roomNumber));
                hostelNameDropDownListNotPayment.add(new HostelDAO().getHostelByRoomId(roomID).getHostelName());
            }
            session.setAttribute("INVOICE_NOT_PAYMENT_LIST", invoiceNotPayment);
            session.setAttribute("INVOICE_NOT_PAYMENT_LIST_HOSTEL_NAME", hostelNameNotPayment);
            session.setAttribute("INVOICE_NOT_PAYMENT_LIST_HOSTEL_ID", hostelIDNotPayment);
            session.setAttribute("INVOICE_NOT_PAYMENT_LIST_ROOM_NUMBER", roomNumberNotPayment);
            session.setAttribute("INVOICE_NOT_PAYMENT_LIST_DROP_DOWN_ROOM_NUMBER", roomNumberDropDownListNotPayment);
            session.setAttribute("INVOICE_NOT_PAYMENT_LIST_DROP_DOWN_HOSTEL_NAME", hostelNameDropDownListNotPayment);

            session.setAttribute("CURRENT_PAGE", "invoice");
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
