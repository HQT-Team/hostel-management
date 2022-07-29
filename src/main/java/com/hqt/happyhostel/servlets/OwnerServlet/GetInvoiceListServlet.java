package com.hqt.happyhostel.servlets.OwnerServlet;

import com.google.gson.Gson;
import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dao.InvoiceDAO;
import com.hqt.happyhostel.dao.RoomDAO;
import com.hqt.happyhostel.dto.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

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
            for (Bill bill: invoicePayment) {
                int roomID = bill.getRoomID();
                int roomNumber = new RoomDAO().getRoomById(roomID).getRoomNumber();
                roomNumberPayment.add(String.valueOf(roomNumber));
                hostelNamePayment.add(new HostelDAO().getHostelByRoomId(roomID).getHostelName());
                hostelIDPayment.add(new HostelDAO().getHostelByRoomId(roomID).getHostelID());
            }
            session.setAttribute("INVOICE_PAYMENT_LIST", invoicePayment);
            session.setAttribute("INVOICE_PAYMENT_LIST_ROOM_NUMBER", roomNumberPayment);
            session.setAttribute("INVOICE_PAYMENT_LIST_HOSTEL_NAME", hostelNamePayment);
            session.setAttribute("INVOICE_PAYMENT_LIST_HOSTEL_ID", hostelIDPayment);


            List<Bill> invoiceNotPayment = invoiceDAO.getInvoiceListByOwnerAccountID(accountId, 0);
            List<String> roomNumberNotPayment = new ArrayList<>();
            List<String> hostelNameNotPayment = new ArrayList<>();
            List<Integer> hostelIDNotPayment = new ArrayList<>();
            for (Bill bill: invoiceNotPayment) {
                int roomID = bill.getRoomID();
                int roomNumber = new RoomDAO().getRoomById(roomID).getRoomNumber();
                roomNumberNotPayment.add(String.valueOf(roomNumber));
                hostelNameNotPayment.add(new HostelDAO().getHostelByRoomId(roomID).getHostelName());
                hostelIDNotPayment.add(new HostelDAO().getHostelByRoomId(roomID).getHostelID());
            }
            session.setAttribute("INVOICE_NOT_PAYMENT_LIST", invoiceNotPayment);
            session.setAttribute("INVOICE_NOT_PAYMENT_LIST_HOSTEL_NAME", hostelNameNotPayment);
            session.setAttribute("INVOICE_NOT_PAYMENT_LIST_HOSTEL_ID", hostelIDNotPayment);
            session.setAttribute("INVOICE_NOT_PAYMENT_LIST_ROOM_NUMBER", roomNumberNotPayment);
            request.setAttribute("LIST_HOSTELS", new HostelDAO().getHostelByOwnerId(accountId));

            session.setAttribute("CURRENT_PAGE", "invoice");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Gson gson = new Gson();
        Account acc;
        RoomDAO roomDAO = new RoomDAO();
        HostelDAO hostelDAO = new HostelDAO();
        try {
            acc = (Account) session.getAttribute("USER");
            int accountId = acc.getAccId();

            String hostelId = request.getParameter("hostelId");
            String roomId = request.getParameter("roomId");
            int type = request.getParameter("type") == null ? 0 : Integer.parseInt(request.getParameter("type"));

            List<Object> list = new ArrayList<>();
            Map<Integer, Room> RoomsOfBills = new HashMap<>();
            Map<Integer, Hostel> HostelOfBills = new HashMap<>();
            if (type == 0) {
                List<Bill> invoices = new InvoiceDAO().getInvoiceListByOwnerAccountID(accountId, 0);
                if (hostelId.equals("")) {
                    for (Bill bill: invoices) {
                        Room room = roomDAO.getRoomById(bill.getRoomID());
                        Hostel hostel = hostelDAO.getHostelByRoomId(room.getRoomId());
                        RoomsOfBills.put(bill.getBillID(), room);
                        HostelOfBills.put(bill.getBillID(), hostel);
                    }
                    list.add(invoices);
                    list.add(RoomsOfBills);
                    list.add(HostelOfBills);
                    list.add(-1);
                    list.add(-1);
                    response.getWriter().println(gson.toJson(list));
                } else {
                    List<Bill> invoicesById = new ArrayList<>();
                    List<Room> roomList = new RoomDAO().getListRoomsByHostelId(Integer.parseInt(hostelId));
                    if (roomId == null || roomId.equals("")) {
                        for (Bill bill : invoices) {
                            int hostelIdOfBill = hostelDAO.getHostelByRoomId(bill.getRoomID()).getHostelID();
                            if (hostelIdOfBill == Integer.parseInt(hostelId)) {
                                invoicesById.add(bill);
                                Room room = roomDAO.getRoomById(bill.getRoomID());
                                Hostel hostel = hostelDAO.getHostelByRoomId(room.getRoomId());
                                RoomsOfBills.put(bill.getBillID(), room);
                                HostelOfBills.put(bill.getBillID(), hostel);
                            }
                        }
                    } else {
                        for (Bill bill : invoices) {
                            int hostelIdOfBill = hostelDAO.getHostelByRoomId(bill.getRoomID()).getHostelID();
                            if (hostelIdOfBill == Integer.parseInt(hostelId) && bill.getRoomID() == Integer.parseInt(roomId)) {
                                invoicesById.add(bill);
                                Room room = roomDAO.getRoomById(bill.getRoomID());
                                Hostel hostel = hostelDAO.getHostelByRoomId(room.getRoomId());
                                RoomsOfBills.put(bill.getBillID(), room);
                                HostelOfBills.put(bill.getBillID(), hostel);
                            }
                        }
                    }
                    list.add(invoicesById);
                    list.add(RoomsOfBills);
                    list.add(HostelOfBills);
                    list.add(roomList);
                    list.add(roomId);
                    response.getWriter().println(gson.toJson(list));
                }
            } else if (type == 1) {
                List<Bill> invoices = new InvoiceDAO().getInvoiceListByOwnerAccountID(accountId, 1);
                if (hostelId.equals("")) {
                    for (Bill bill: invoices) {
                        Room room = roomDAO.getRoomById(bill.getRoomID());
                        Hostel hostel = hostelDAO.getHostelByRoomId(room.getRoomId());
                        RoomsOfBills.put(bill.getBillID(), room);
                        HostelOfBills.put(bill.getBillID(), hostel);
                    }
                    list.add(invoices);
                    list.add(RoomsOfBills);
                    list.add(HostelOfBills);
                    list.add(-1);
                    list.add(-1);
                    response.getWriter().println(gson.toJson(list));
                } else {
                    List<Bill> invoicesById = new ArrayList<>();
                    List<Room> roomList = new RoomDAO().getListRoomsByHostelId(Integer.parseInt(hostelId));
                    if (roomId == null || roomId.equals("")) {
                        for (Bill bill : invoices) {
                            int hostelIdOfBill = hostelDAO.getHostelByRoomId(bill.getRoomID()).getHostelID();
                            if (hostelIdOfBill == Integer.parseInt(hostelId)) {
                                invoicesById.add(bill);
                                Room room = roomDAO.getRoomById(bill.getRoomID());
                                Hostel hostel = hostelDAO.getHostelByRoomId(room.getRoomId());
                                RoomsOfBills.put(bill.getBillID(), room);
                                HostelOfBills.put(bill.getBillID(), hostel);
                            }
                        }
                    } else {
                        for (Bill bill : invoices) {
                            int hostelIdOfBill = hostelDAO.getHostelByRoomId(bill.getRoomID()).getHostelID();
                            if (hostelIdOfBill == Integer.parseInt(hostelId) && bill.getRoomID() == Integer.parseInt(roomId)) {
                                invoicesById.add(bill);
                                Room room = roomDAO.getRoomById(bill.getRoomID());
                                Hostel hostel = hostelDAO.getHostelByRoomId(room.getRoomId());
                                RoomsOfBills.put(bill.getBillID(), room);
                                HostelOfBills.put(bill.getBillID(), hostel);
                            }
                        }
                    }
                    list.add(invoicesById);
                    list.add(RoomsOfBills);
                    list.add(HostelOfBills);
                    list.add(roomList);
                    list.add(roomId);
                    response.getWriter().println(gson.toJson(list));
                }
            }
            session.setAttribute("CURRENT_PAGE", "invoice");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
